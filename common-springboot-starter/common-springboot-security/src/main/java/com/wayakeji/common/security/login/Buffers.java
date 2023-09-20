package com.wayakeji.common.security.login;


import com.wayakeji.common.core.constant.CacheConstants;
import com.wayakeji.common.core.constant.ErrorConstants;
import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.common.core.exception.SelectException;
import com.wayakeji.common.core.util.Serialize;
import com.wayakeji.common.core.util.code.AES;
import com.wayakeji.common.core.util.code.ChaoticMix;
import com.wayakeji.common.core.util.code.TextCoding;
import com.wayakeji.common.core.util.code.Tracy62;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class Buffers implements Serializable, Ordered {

	private static final long serialVersionUID = 1L;
	public static final String ENCODE_SPLIT = ".";
	public static final int CHAOTIC_MIX_LEN = 1;

	private final CacheManager cacheManager;
	
	/**
	 * 获取BufferUser
	 * @param id
	 * @return {@link BufferUser}
	 */
	public BufferUser getUser(Long id) {
		try {
			Cache cache = cacheManager.getCache(AuthorityConstants.USERS_KEY);
			if (cache != null && cache.get(id) != null) {
				return (BufferUser) cache.get(id).get();
			}
			return null;
//			return Serialize.deserializeByFile(AuthorityConstants.USERS_KEY + id);
		}catch (Exception e) {
			throw  new HandyserveException(e);
		}
	}
	
	/**
	 * <p>删除系统buffer的user，用于踢掉线等操作
	 * @param id
	 */
	public void removeUser(Long id) {
		Objects.requireNonNull(cacheManager.getCache(AuthorityConstants.USERS_KEY)).evict(id);
//		Serialize.remove(AuthorityConstants.USERS_KEY+ id);
	}
	
	/**
	 * 缓存Buffer信息
	 * @param id
	 * @param value {@link BufferUser}
	 * @throws IOException 
	 */
	public void setUser(Long id, BufferUser value) throws IOException {
//		Serialize.toFile(value, AuthorityConstants.USERS_KEY + id);
		Cache cache = cacheManager.getCache(AuthorityConstants.USERS_KEY);
		if (cache != null) {
			cache.put(id, value);
		}
	}
	
	/**
	 * 获取BufferAuthority
	 * @param id
	 * @return {@link BufferAuthority}
	 */
	public BufferAuthority getAuthority(Long id) {
		try {
//			return Serialize.deserializeByFile(AuthorityConstants.AUTHORITY_KEY + id);
			Cache cache = cacheManager.getCache(AuthorityConstants.AUTHORITY_KEY);
			if (cache != null && cache.get(id) != null) {
				return (BufferAuthority) cache.get(id).get();
			}
			return null;
		}catch (Exception e) {
			throw  new HandyserveException(e);
		}
	}
	
	/**
	 * <p>删除系统buffer的权限
	 * @param id
	 */
	public void removeAuthority(Long id) {
//		Serialize.remove(AuthorityConstants.AUTHORITY_KEY + id);
		Objects.requireNonNull(cacheManager.getCache(AuthorityConstants.AUTHORITY_KEY)).evict(id);
	}
	
	/**
	 * 缓存BufferAuthority信息
	 * @param id
	 * @param value {@link BufferAuthority}
	 * @throws IOException 
	 */
	public void setAuthority(Long id, BufferAuthority value) throws IOException {
//		Serialize.toFile(value, AuthorityConstants.AUTHORITY_KEY + id);
		Cache cache = cacheManager.getCache(AuthorityConstants.AUTHORITY_KEY);
		if (cache != null) {
			cache.put(id, value);
		}
	}
	
	/**
	 * <p>创建token
	 * <pre>
	 * 以secret作为密钥
	 * 以WyString(Tracy62(id) + "." + secret + "." + Tracy62(time))作为加密文本
	 * 进行AES对称加密
	 * @param id 用户固定唯一会话字符串
	 * @param value {@link BufferUser}
	 * @return 返回token字符串
	 * @throws Exception
	 */
	public String buildToken(Long id, String secret, BufferUser value) throws Exception {
		long timer = System.currentTimeMillis();
		value.setTimer(timer);
		Tracy62 idT62 = Tracy62.build(id);
		Tracy62 timerT62 = Tracy62.build(timer);
		String content = TextCoding.strSplice(
				idT62.toString(), ENCODE_SPLIT,
				secret, ENCODE_SPLIT,
				timerT62.toString(), ENCODE_SPLIT);
		setUser(id, value);
		return AES.encode(secret, ChaoticMix.build(content, CHAOTIC_MIX_LEN).toString());
	}
	
	/**
	 * <p>验证Token
	 * @param id {@link Long} 会话字符串
	 * @param token {@link String} token
	 * @return secret密码
	 * @throws SelectException 
	 */
	public BufferUser checkToken(long id, String token) throws SelectException {
		BufferUser value = getUser(id);
		if(null == value) {
			// token不存在
			throw new SelectException(ErrorConstants.TOKEN_NOTFOUND, "token不存在");
		}
		String[] contents;
		String secret = ChaoticMix.build(value.getSignKey()).string();
		try {
			contents = ChaoticMix.build(AES.dncode(secret, token), CHAOTIC_MIX_LEN).string().split("\\.");
		}catch (Exception e) {
			throw new SelectException(ErrorConstants.TOKEN_DECODE_ERROR, "token错误", e);
		}
		if(null == contents || contents.length != 3) {
			// token错误
			throw new SelectException(ErrorConstants.TOKEN_ERROR, "token错误");
		}
		long idT62 = Tracy62.build(contents[0]).toLong();
		if(idT62 != id) {
			// token错误
			throw new SelectException(ErrorConstants.TOKEN_ERROR, "token错误");
		}
		// 根据目前的规则，下面注释的这段代码是多余的
		if(!Objects.equals(contents[1], secret)) {
			// token错误
			throw new SelectException(ErrorConstants.SECRET_ERROR, "token错误");
		}
		long timerT62 = Tracy62.build(contents[2]).toLong();
		if(System.currentTimeMillis() - timerT62 >= AuthorityConstants.TOKEN_EFFECTIVE_TIME) {
			// token已过期
			throw new SelectException(ErrorConstants.TOKEN_EXPIRED, "token已过期");
		}
		return value;
	}

	@Override
	public int getOrder() {
		return 0;
	}
}

