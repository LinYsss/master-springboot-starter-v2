package com.wayakeji.payment.common;

import com.wayakeji.common.core.util.code.ChaoticMix;
import com.wayakeji.common.core.util.code.TextCoding;
import com.wayakeji.common.core.util.code.Tracy62;
import com.wayakeji.payment.annotation.PayField;
import com.wayakeji.payment.exception.ParameterParseException;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>公共POJO
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see SimpleDateFormat
 * @see ChaoticMix
 * @see TextCoding
 * @see Tracy62
 * @since 1.8
 * @version 1.0
 * @time 2019年7月23日
 */
public class CommonPojo {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	/**
	 * <p>生成随机字符串
	 * <p>通过{@link TextCoding#createNumberString(int)}生成30位字符.
	 * <p>再将字符分成两个15位的long数字进行{@link Tracy62}转码, 最后将转码后的字符拼接返回
	 * @return 随机数
	 */
	public String nonceStr() {
		String str = TextCoding.createNumberString(30);
		return Tracy62.build(Long.parseLong(str.substring(0, 15))).toString()
				+ Tracy62.build(Long.parseLong(str.substring(15))).toString();
	}
	
	/**
	 * <p>生成26位长度的订单号
	 * <p>使用{@link SimpleDateFormat}, 格式'yyyyMMddHHmmssSSS'将当前时间转换后再拼接9位长度的随机数字
	 * @return 订单号
	 */
	public String outTradeNo() {
		Date date = new Date();
		String str;
		synchronized(sdf) {
			str = sdf.format(date);
		}
		return str + ChaoticMix.randomNumber(9);
	}
	
	/**
	 * <p>生成26位长度的订单号
	 * <p>使用{@link TextCoding#createNumberString(int)}生成26位字符
	 * @return
	 */
	public String outRefundNo() {
		return TextCoding.createNumberString(26);
	}
	
	/**
	 * <p>将对象转换为map
	 * @return
	 * @throws ParameterParseException
	 */
	public Map<String, Object> toMap() throws ParameterParseException {
		Map<String, Object> map = new HashMap<>();
		Field[] fields = getAllFields(this.getClass());
		Field field;
		PayField pf;
		String key;
		Object val;
		for(int i = 0; i < fields.length; i++) {
			field = fields[i];
			field.setAccessible(true);
			pf = field.getAnnotation(PayField.class);
			if(pf == null) {
				continue;
			}
			key = pf.value();
			try {
				val = field.get(this);
			}catch (Exception e) {
				throw new ParameterParseException(e);
			}
			if(val == null || Objects.equals(val, 0) || Objects.equals(val, 0L)) {
				continue;
			}
			map.put(key, val);
		}
		return map;
	}

	/**
	 * 获取本类及其父类的属性的方法
	 * @param clazz 当前类对象
	 * @return 字段数组
	 */
	private static Field[] getAllFields(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<>();
		while (clazz != null){
			fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
			clazz = clazz.getSuperclass();
		}
		Field[] fields = new Field[fieldList.size()];
		return fieldList.toArray(fields);
	}
	
}
