package com.wayakeji.common.core.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

/**
 * i18n 工具类
 *
 */
@Slf4j
@UtilityClass
public class MsgUtils {

	/**
	 * 通过code 获取错误信息（中文, 英文）
	 * @param code
	 * @return
	 */
	public String getMessage(String code) {
		MessageSource messageSource = SpringContextHolder.getBean("messageSource");
		Locale locale = Locale.CHINA;
		try {
			Optional<HttpServletRequest> request =
					Optional.ofNullable(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
			String language = request.get().getHeader("language");
			if("en".equals(language)) {
				locale = Locale.ENGLISH;
			}
			return messageSource.getMessage(code, null, locale);
		}catch (Exception e) {
			log.error("MsgUtils.getMessage()", e);
		}
		return code;
	}

	/**
	 * 通过code 和参数获取中文错误信息
	 * @param code
	 * @return
	 */
	public String getMessage(String code, Object... objects) {
		MessageSource messageSource = SpringContextHolder.getBean("messageSource");
		Locale locale = Locale.CHINA;
		try {
			Optional<HttpServletRequest> request =
					Optional.ofNullable(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
			String language = request.get().getHeader("language");
			if("en".equals(language)) {
				locale = Locale.ENGLISH;
			}
			return messageSource.getMessage(code, objects, locale);
		}catch (Exception e) {
			log.error("MsgUtils.getMessage()", e);
		}
		return code;
	}


	/**
	 * 通过code 获取错误信息（中文, 英文）
	 * @param code
	 * @param locale
	 * @return
	 */
	public String getMessage(String code, Locale locale) {
		MessageSource messageSource = SpringContextHolder.getBean("messageSource");
		try {
			return messageSource.getMessage(code, null, locale);
		}catch (Exception e) {
			return code;
		}
	}

}
