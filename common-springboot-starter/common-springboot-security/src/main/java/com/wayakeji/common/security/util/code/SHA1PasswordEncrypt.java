package com.wayakeji.common.security.util.code;


import com.wayakeji.common.core.util.code.SHA;
import org.springframework.stereotype.Component;

/**
 * 使用SHA1加密密码
 * <p>使用"."拼接密码与盐
 * @author hu trace
 */
@Component
public class SHA1PasswordEncrypt implements PasswordEncrypt {

	@Override
	public String encrypt(String password, String salt) throws Exception {
		return SHA.sha1(password + "." + salt);
	}

}
