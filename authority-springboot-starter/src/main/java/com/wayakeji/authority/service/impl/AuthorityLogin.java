package com.wayakeji.authority.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wayakeji.authority.mapper.AuthorityLoginHistoryMapper;
import com.wayakeji.authority.mapper.AuthorityUserMapper;
import com.wayakeji.common.api.entity.AuthorityLoginHistory;
import com.wayakeji.common.api.entity.AuthorityUser;
import com.wayakeji.common.api.vo.AuthorityApiVO;
import com.wayakeji.common.core.constant.CacheConstants;
import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.common.core.exception.InsertException;
import com.wayakeji.common.core.exception.SelectException;
import com.wayakeji.common.core.exception.UpdateException;
import com.wayakeji.common.core.util.code.ChaoticMix;
import com.wayakeji.common.core.util.code.TextCoding;
import com.wayakeji.common.security.filter.HttpServerRequest;
import com.wayakeji.common.security.login.*;
import com.wayakeji.common.security.util.code.ChaoticPasswordMix;
import com.wayakeji.common.security.util.code.SHA256PasswordEncrypt;
import com.wayakeji.common.security.util.code.TextCodingSalt;
import com.wayakeji.common.security.util.code.Tracy62InviteCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthorityLogin {

	private final TextCodingSalt salt;

	private final SHA256PasswordEncrypt passwordEncrypt;

	private final ChaoticPasswordMix passwordMix;

	private final Tracy62InviteCode inviteCode;

	private final AuthorityUserMapper authorityUserMapper;

    private final AuthorityLoginHistoryMapper loginHistoryMapper;

	private final  Buffers buffers;

	/**
	 * 创建用户
	 * @param user
	 * @param buf
	 * @return
	 */
	public int create(AuthorityUser user, BufferParams buf) {
		user.setAddrid(buf.getBufRoleId());
		user.setAdduid(buf.getClientId());
		return create(user);
	}

	/**
	 * 创建用户
	 * @param user
	 * @return
	 * @throws InsertException
	 */
	public int create(AuthorityUser user){
		checkAndPushUserBean(user);
		try {
			return authorityUserMapper.insert(user);
		}catch (Throwable e) {
			String msg = getDuplicateKeyMsg(e.getCause());
			if(msg == null) {
				throw new InsertException(e.getMessage());
			}else {
				throw new InsertException(msg);
			}
		}
	}


	/**
	 * 修改密码
	 * @param id
	 * @param password 密码
	 * @param oldPassword 原始密码（为空则不验证原始密码）
	 * @return
	 */
    public int updatePassword(Long id, String password, String oldPassword){
		AuthorityUser user = selectUser(LoginType.ID, id);
		if(oldPassword != null && !oldPassword.isEmpty()) {
			try {
				if(!user.getPassword().equals(passwordEncrypt.encrypt(oldPassword, user.getSalt()))) {
					throw new SelectException(" 原始密码不匹配");
				}
			}catch (Exception e) {
				throw new UpdateException(e.getMessage());
			}
		}
		try {
			user.setMix(passwordMix.mix(password));
			user.setPassword(passwordEncrypt.encrypt(password, user.getSalt()));
		}catch (Exception e) {
			throw new UpdateException(e.getMessage());
		}
		try {
			return authorityUserMapper.updateById(user);
		}catch (Throwable e) {
			throw new UpdateException(e.getMessage());
		}
	}

	/**
	 * 解析异常是否是因为重复导致的异常
	 * <p>如果是重复导致的异常，则解析出字段和值
	 * <p>使用语言包抛出信息
	 * @param e
	 * @return
	 */
	private String getDuplicateKeyMsg(Throwable e) {
		if(e == null) {
			return null;
		}
		String msg = e.getMessage();
		String value = null;
		String key = null;
		int index = 0;
		if(msg.indexOf("Duplicate entry") > -1) {
			Pattern p = Pattern.compile("'(.*?)'");
			Matcher m = p.matcher(msg);
			while(m.find() && index < 2) {
				if(index == 0) {
					value = m.group();
				}else if(index == 1) {
					key = m.group().replace("'", "");
				}
				index ++;
			}
		}
		if(key == null) {
			return null;
		}
		if(key.equals("username")) {
			return "用户名重复，该用户名已存在："+ value;
		}else if(key.equals("phone")) {
			return "手机号重复，该手机号已存在 ："+ value;
		}else if(key.equals("email")) {
			return "邮箱重复，该邮箱已存在 ：" + value;
		}else {
			return null;
		}
	}

	/**
	 * 校验传入的user数据是否完善
	 * <p>生成user需要的其它系统数据
	 * @param user
	 * @throws InsertException
	 */
	private void checkAndPushUserBean(AuthorityUser user) {
		if(user.getUsername() == null || user.getPassword() == null
				|| user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			throw new InsertException("用户名或密码不能为空");
		}
		if(user.getNickname() == null || user.getNickname().isEmpty()) {
			user.setNickname(user.getUsername());
		}
		user.setCreatetime(LocalDateTime.now());
		user.setEnabled(1);
		user.setMix(passwordMix.mix(user.getPassword()));
		user.setSalt(salt.create());
		try {
			user.setPassword(passwordEncrypt.encrypt(user.getPassword(), user.getSalt()));
		}catch (Exception e) {
			throw new InsertException(e.getMessage());
		}
		user.setInvitecode(inviteCode.create());
		user.setSecret(TextCoding.createLowerRule32());
		if(ObjectUtils.isEmpty(user.getBelong())) {
			user.setBelong("back");
		}

	}

	/**
	 * 用户登陆方法
	 * <p>该方法通过LoginType判断根据什么值查询数据
	 * @param type 根据什么值查询数据
	 * @param arg0 (id/username/phone/email)的值
	 * @param arg1 (secret/password...)需要与arg0匹配登陆的值
	 * @return
	 */
	public Map<String, Object> login(HttpServerRequest request, LoginType type,String arg0, String arg1) {
        AuthorityUser user = selectUser(type, arg0);
        check(request, user, arg1);
        return buildRsp(user);
	}

	/**
	 * 验证登陆用户
	 * <pre>
	 *  包含
	 *    验证账号是否被临时锁的
	 *    验证账号参数是否匹配
	 *    验证账号是否被禁用
	 * </pre>
	 * <p>并对所有未通过的做登陆历史记录
	 * <p>未通过以异常的方式抛出
	 * @param request
	 * @param user 查询出的登陆账号
	 * @param arg1 调用登陆方法时的arg1参数
	 */
	public void check(HttpServerRequest request, AuthorityUser user, String arg1) {
		AuthorityLoginHistory history = new AuthorityLoginHistory();
		history.setIp(request.ip());
		history.setUserid(user.getId());
		try {
			checkLock(user);
			checkMatch(user, arg1);
			checkUserEnabled(user);
			history.setStatus(0);
			insertHostiry(history);
			updateUserLoginInfo(user.getId(), history.getIp());
		}catch (HandyserveException e) {
			history.setStatus(e.code());
			history.setExplain(e.getMessage());
			insertHostiry(history);
			throw new HandyserveException(e.getMessage());
		}
	}

	/**
	 * 校验账号是否因登陆错误次数过多而被锁定
	 */
	private void checkLock(AuthorityUser user) throws SelectException {
		Map<String, Object> params = new HashMap<>();
		params.put("time", AuthorityConstants.LOGIN_ERROR_LOCK_TIME);
		params.put("userId", user.getId());
		Object obj = authorityUserMapper.selectLoginErrorCount(params);
		int count = 0;
		if(obj != null) {
			count = Integer.parseInt(obj.toString());
		}
		if(count >= AuthorityConstants.LOGIN_ERROR_TIME) {
			throw new SelectException(3, "当前登陆错误超过 "+ AuthorityConstants.LOGIN_ERROR_TIME + "次，账号已被锁定" + (AuthorityConstants.LOGIN_ERROR_LOCK_TIME/60) +"分钟，请稍后再试。 ");
		}
	}

	/**
	 * 判断验证是否通过的方法
	 * <p>例如：通过用户名和密码登陆时，你可以使用user.getPassword和arg1加密后是否匹配做判断
	 * @param user 查询出来的用户数据，走到这一步时，user必定不为null
	 * @param arg1 调用登陆方法时的arg1参数
	 * 验证不通过时，根据情况抛出SelectException或者RollbackException<br/>
	 * 抛出异常时应当注意，需要指定异常的code为1(详见数据库authority_login_history表status字段)
	 */
	public void checkMatch(AuthorityUser user, String arg1) {
        try {
            if(!user.getPassword().equals(passwordEncrypt.encrypt(arg1, user.getSalt()))) {
                throw new SelectException(1, "用户名或密码不匹配");
            }
        }catch (SelectException e) {
            throw e;
        }catch (Exception e) {
            throw new SelectException(e);
        }
	}

	/**
	 * 校验用户是否被禁用
	 * @param user
	 * @throws SelectException
	 */
	private void checkUserEnabled(AuthorityUser user){
		if(user.getEnabled() == 0) {
			throw new SelectException(2, "当前登陆用户已被禁用，请联系管理解封。");
		}
	}

	/**
	 * 新增登陆历史记录
	 * @param history
	 */
	private void insertHostiry(AuthorityLoginHistory history) {
		history.setTime(LocalDateTime.now());
        loginHistoryMapper.insert(history);
	}

	/**
	 * 修改用户登陆信息
	 * @param id
	 * @param ip
	 */
	private void updateUserLoginInfo(Long id, String ip) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("loginIp", ip);
		params.put("loginTime", new Date());
		authorityUserMapper.updateUserLoginInfo(params);
	}

	/**
	 * 查询登陆用户数据
	 * @param type 登陆类型，通过什么登陆
	 * @param value 查询对应的值
	 * @return 登陆用户数据
	 */
	public AuthorityUser selectUser(LoginType type, Object value) {
		AuthorityUser user = authorityUserMapper.selectOne(Wrappers.<AuthorityUser>query().eq(type.value, value));
		if(user == null) {
			throw new SelectException("用户名或密码不匹配"); //"用户名不存在" + type.value
		}
		return user;
	}

	/**
	 * 构造响应数据
	 * @param user
	 * @return
	 */
	private Map<String, Object> buildRsp(AuthorityUser user){
		BufferUser bu = buildBufferUser(user);
		Map<String, Object> map = new HashMap<>();
		buildRspOther(user,bu, map);
		buildAndBufferAuthority(user);
		String token;
		try {
			token = buffers.buildToken(user.getId(), user.getSecret(), bu);
		}catch (Exception e) {
			buffers.removeUser(user.getId());
			throw new SelectException(e.getMessage());
		}
		user.setSecret(bu.getSecret());
		map.put("secret", bu.getSecret());
		map.put("token", token);
		map.put("signKey", bu.getSignKey());
		map.put("tokenEffectiveTime", AuthorityConstants.TOKEN_EFFECTIVE_TIME + bu.getTimer());
		return map;
	}

	/**
	 * 向响应数据中写值
	 * <p>参数rsp就是向客户端响应的数据
	 * <p>当前rsp中只有一个token值，你可以设置其它你需要的值
	 * @param user
	 * @param rsp
	 */
	protected void buildRspOther(AuthorityUser user,BufferUser bufferUser, Map<String, Object> rsp) {
		bufferUser.setClientId(user.getId());
		bufferUser.setUsername(user.getUsername());
		bufferUser.setPassword(user.getPassword());
		rsp.put("id", user.getId());
		rsp.put("username", user.getUsername());
		rsp.put("phone", user.getPhone());
		rsp.put("email", user.getEmail());
		rsp.put("nickname", user.getNickname());
		rsp.put("organization", user.getOrganization());
		rsp.put("loginTime", user.getLogintime());
		rsp.put("loginIp", user.getLoginip());
	}


	/**
	 * 缓存buffer登陆用户权限信息
	 * @param user
	 * @return
	 * @throws SelectException
	 */
	protected List<AuthorityApiVO> buildAndBufferAuthority(AuthorityUser user) throws SelectException {
		List<AuthorityApiVO> apis = authorityUserMapper.selectApisByUid(user.getId());
		BufferAuthority buf = new BufferAuthority();
		if(apis == null || apis.size() == 0) {
			buf.setModule("");
		}else {
			StringBuilder strb = new StringBuilder();
			for(int i = apis.size() - 1; i >= 0; i--) {
				strb.append(apis.get(i).getId());
				if(i != 0) {
					strb.append(",");
				}
			}
			buf.setModule(strb.toString());
		}
		try {
			buffers.setAuthority(user.getId(), buf);
		}catch (IOException e) {
			throw new SelectException(e);
		}
		return apis;
	}

	/**
	 * 构造用户登陆缓存
	 * <p>保证多用户可同时登陆，如果不需要多用户同时登陆，修改此方法判断，改为每次new BufferUser就可以了
	 * <p>当前规则，当本次登陆距离上次登陆超出了token有效期后，就会修改secret，否则每次取上次的secret写入。
	 * @param user
	 * @return
	 */
	private BufferUser buildBufferUser(AuthorityUser user) {
		BufferUser bv = buffers.getUser(user.getId());
		if(bv == null) {
			bv = new BufferUser();
			bv.setSignKey(ChaoticMix.build(user.getSecret()).toString());
			bv.setSecret(TextCoding.createLowerRule32());
		}else {
			if(System.currentTimeMillis() - bv.getTimer() >= AuthorityConstants.TOKEN_EFFECTIVE_TIME) {
				bv = new BufferUser();
				bv.setSignKey(ChaoticMix.build(user.getSecret()).toString());
				bv.setSecret(TextCoding.createLowerRule32());
			}
		}
		bv.setCustom(buildExtendBufferUser(user));
		return bv;
	}

	/**
	 * 编辑用户缓存的扩展值，你可以把它理解为写入session
	 * @param user
	 * @return map中写入值可以直接通过mapping方法上接口而取得
	 */
	protected Map<String, Object> buildExtendBufferUser(AuthorityUser user){
		// 包含bufRoleId和bufRolePid
		Map<String, Object> buffer = authorityUserMapper.selectRoleInfoByClientId(user.getId());
		return buffer;
	}

	/**
	 * 登陆类型枚举
	 * @author hu trace
	 */
	public static enum LoginType {

		USERNAME("username"),
		PHONE("phone"),
		EMAIL("email"),
		ID("id");

		private LoginType(String v) {
			value = v;
		}

		private String value;

	}

}

