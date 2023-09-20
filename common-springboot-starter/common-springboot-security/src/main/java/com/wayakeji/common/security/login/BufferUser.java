package com.wayakeji.common.security.login;

import com.wayakeji.common.api.entity.AuthorityUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * 缓存Token验证的信息Value实体类
 * @author hu trace
 */
@Data
@NoArgsConstructor
public class BufferUser implements Serializable {
	
	private static final long serialVersionUID = 8624037694938430435L;

	/**
	 * 用户ID
	 */
	private Long clientId;

	/**
	 * 用户类型
	 */
	private String userType;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	private String secret;

	private String signKey;

	private long timer;

	private Map<String, Object> custom;

	
}
