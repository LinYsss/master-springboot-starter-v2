package com.wayakeji.common.security.login;


import com.wayakeji.common.api.entity.AuthorityUser;
import com.wayakeji.common.core.constant.ErrorConstants;
import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.common.security.Interceptor.AuthorityInterceptor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * 登录鉴权助手
 *
 * user_type 为 用户类型 同一个用户表 可以有多种用户类型 例如 pc,app deivce 为 设备类型 同一个用户类型 可以有 多种设备类型 例如 web,ios
 * 可以组成 用户类型与设备类型多对多的 权限灵活控制
 *
 * 多用户体系 针对 多种用户类型 但权限控制不一致 可以组成 多用户类型表与多设备类型 分别控制权限
 *@NoArgsConstructor ： 在类上使用，会生成一个无参构造器
 */
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {


	public static long clientId() throws HandyserveException {
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		String cid = (String) request.getAttribute(AuthorityInterceptor.getQsParamId());
		if (cid == null) {
			throw new HandyserveException(ErrorConstants.HEADER_ERROR, "你必须通过QueryString传入。" + AuthorityInterceptor.getQsParamId());
		}
		try {
			return Long.parseLong(cid);
		} catch (Exception e) {
			throw new HandyserveException(ErrorConstants.HEADER_ERROR, "格式错误，它应该是数字类型。" + cid);
		}
	}
}
