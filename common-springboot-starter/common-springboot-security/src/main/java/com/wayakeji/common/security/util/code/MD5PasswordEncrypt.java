package com.wayakeji.common.security.util.code;


import com.wayakeji.common.core.util.code.MD5;
import org.springframework.stereotype.Component;

/**
 * 使用MD5加密密码
 * <p>使用"."拼接密码与盐
 * @author hu trace
 */
@Component
public class MD5PasswordEncrypt implements PasswordEncrypt {

	@Override
	public String encrypt(String password, String salt) throws Exception {
		return MD5.lowerCase(password + "." + salt);
	}

}
