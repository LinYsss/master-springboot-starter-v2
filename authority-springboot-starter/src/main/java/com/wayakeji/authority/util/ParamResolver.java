package com.wayakeji.authority.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

/**
 * @author
 * @date 2020/5/12
 * <p>
 * 系统参数配置解析器
 */
@UtilityClass
public class ParamResolver {

	/**
	 * 注册用户默认角色
	 */
	public static final String  USER_DEFAULT_ROLE = "GENERAL_USER";

	/**
	 * 根据key 查询value 配置
	 * @param key key
	 * @param defaultVal 默认值
	 * @return value
	 */
	public Long getLong(String key, Long... defaultVal) {
		return checkAndGet(key, Long.class, defaultVal);
	}

	/**
	 * 根据key 查询value 配置
	 * @param key key
	 * @param defaultVal 默认值
	 * @return value
	 */
	public String getStr(String key, String... defaultVal) {
		return checkAndGet(key, String.class, defaultVal);
	}

	private <T> T checkAndGet(String key, Class<T> clazz, T... defaultVal) {
		// 校验入参是否合法
		if (StrUtil.isBlank(key) || defaultVal.length > 1) {
			throw new IllegalArgumentException("参数不合法");
		}

		if (StrUtil.isNotBlank(USER_DEFAULT_ROLE)) {
			return Convert.convert(clazz, USER_DEFAULT_ROLE);
		}

		if (defaultVal.length == 1) {
			return Convert.convert(clazz, defaultVal.clone()[0]);

		}
		return null;
	}

}
