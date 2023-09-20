package com.wayakeji.common.security.util.code;


import com.wayakeji.common.core.util.code.TextCoding;
import com.wayakeji.common.core.util.code.Tracy62;
import org.springframework.stereotype.Component;

/**
 * 使用{@link Tracy62}来创建邀请码
 * @author hu trace
 */
@Component
public class Tracy62InviteCode implements InviteCode {
	
	private final char fill = 'A';
	
	@Override
	public String create() {
		String inviteNum = TextCoding.createNumberString(20);
		String str = fill(Tracy62.build(Long.parseLong(inviteNum.substring(0, 10))).toString());
		str += fill(Tracy62.build(Long.parseLong(inviteNum.substring(10))).toString());
		return str;
	}
	
	private String fill(String str) {
		// 每10位数生成的Tracy62字符大部分为6位数，偶尔会有肯是5位
		if(str.length() == 6) {
			return str;
		}
		// 否则为长度为5位
		return str += fill;
	}

}
