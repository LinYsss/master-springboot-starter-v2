package com.wayakeji.common.security.util.code;


import com.wayakeji.common.core.util.Charset;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Component
public class Base64PasswordMix implements PasswordMix {

	@Override
	public String mix(String password) {
		try {
			return new String(Base64.getEncoder().encode(password.getBytes(Charset.UTF_8)), Charset.UTF_8);
		}catch (UnsupportedEncodingException e) {
			return new String(Base64.getEncoder().encode(password.getBytes()));
		}
	}

	@Override
	public String restore(String mix) {
		try {
			return new String(Base64.getDecoder().decode(mix.getBytes(Charset.UTF_8)), Charset.UTF_8);
		} catch (UnsupportedEncodingException e) {
			return new String(Base64.getDecoder().decode(mix.getBytes()));
		}
	}

}
