package com.wayakeji.common.security.util.code;


import com.wayakeji.common.core.util.code.TextCoding;
import org.springframework.stereotype.Component;

/**
 * 使用{@link TextCoding#createCode64()}创建盐
 * @see TextCoding
 * @author hu trace
 */
@Component
public class TextCodingSalt implements Salt {

	@Override
	public String create() {
		return TextCoding.createCode64();
	}

}
