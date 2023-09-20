package com.wayakeji.common.security.util.code;


import com.wayakeji.common.core.exception.ValidateRuntimeException;
import com.wayakeji.common.core.util.code.ChaoticMix;
import org.springframework.stereotype.Component;

/**
 * 使用{@link ChaoticMix}类进行密码混淆
 * <p>该密码混淆类不允许密码开头为数字
 * @see ChaoticMix
 * @author hu trace
 */
@Component
public class ChaoticMixPasswordMix implements PasswordMix {
	
	/**
	 * 混淆密码前面无效字符的长度
	 */
	private int mixLength = 5;
	
	public void setMixLength(int mixLength) {
		this.mixLength = mixLength;
	}

	@Override
	public String mix(String password) {
		try {
			Integer.parseInt(password.substring(0, 1));
			throw new ValidateRuntimeException("authority.chaotixmix.error");
		}catch (NumberFormatException e) {
			return ChaoticMix.build(password, mixLength).toString();
		}
	}

	@Override
	public String restore(String mix) {
		return ChaoticMix.build(mix, mixLength).string();
	}

}
