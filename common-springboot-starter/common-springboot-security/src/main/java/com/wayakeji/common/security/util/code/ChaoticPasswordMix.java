package com.wayakeji.common.security.util.code;


import com.wayakeji.common.core.util.code.Chaotic;
import org.springframework.stereotype.Component;

/**
 * 使用{@link PasswordMix}类进行密码混淆
 * @see PasswordMix
 * @author hu trace
 */
@Component
public class ChaoticPasswordMix implements PasswordMix {
	
	@Override
	public String mix(String password) {
		return Chaotic.encode(password);
	}

	@Override
	public String restore(String mix) {
		return Chaotic.decode(mix);
	}

}
