package com.wayakeji.payment.common;

import com.wayakeji.common.core.util.code.MD5;
import com.wayakeji.common.core.util.xml.XMLSerializer;
import com.wayakeji.payment.exception.ParameterParseException;
import com.wayakeji.payment.pojo.SignType;
import org.dom4j.DocumentException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ParameterParse {

//	private static XMLSerializer xmlSerializer = new XMLSerializer();

	/**
	 * <p>map转Xml字符串并签名
	 *
	 * @param map
	 * @param key      微信支付商户密钥
	 * @param signType
	 * @return
	 * @throws Exception
	 */
	public static String mapToSignXml(Map<String, Object> map, String key, SignType signType) throws ParameterParseException {
//		map.put("sign_type", signType.name());
		String sortStr = mapToSortQueryString(map, key);
		if(signType == SignType.MD5) {
			map.put("sign", MD5.upperCase(sortStr));
		}else if(signType == SignType.HMACSHA256) {
			try {
				map.put("sign", HMACSHA256(sortStr, key));
			}catch (Exception e) {
				throw new ParameterParseException(e);
			}
		}
		return mapToXml(map);
	}

	public static String HMACSHA256(String data, String key) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for(byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * <p>简单的map转Xml字符串
	 *
	 * @param map
	 * @return
	 */
	public static String mapToXml(Map<String, Object> map) {
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>");
		Set<Entry<String, Object>> entrys = map.entrySet();
		for(Entry<String, Object> entry : entrys) {
			xml.append("<").append(entry.getKey()).append("><![CDATA[");
			xml.append(entry.getValue());
			xml.append("]]></").append(entry.getKey()).append(">");
		}
		xml.append("</xml>");
		return xml.toString();
	}

	public static String mapToSortQueryString(Map<String, Object> map, boolean nullValueAppend) {
		return mapToSortQueryString(map, null, nullValueAppend);
	}

	public static String mapToSortQueryString(Map<String, Object> map, String key) {
		return mapToSortQueryString(map, key, false);
	}

	public static String mapToSortQueryString(Map<String, Object> map) {
		return mapToSortQueryString(map, null, false);
	}

	/**
	 * <p>map转queryString并完成排序
	 *
	 * @param map
	 * @param key
	 * @return
	 */
	public static String mapToSortQueryString(Map<String, Object> map, String key, boolean nullValueAppend) {
		String[] keys = map.keySet().toArray(new String[map.size()]);
		Arrays.sort(keys);
		Object obj;
		String val;
		StringBuilder strb = new StringBuilder();
		for(int i = 0; i < keys.length; i++) {
			if(keys[i].equals("sign")) {
				continue;
			}
			obj = map.get(keys[i]);
			if(!nullValueAppend) {
				if(obj == null) {
					continue;
				}
				val = obj.toString().trim();
				if(val.length() == 0) {
					continue;
				}
				strb.append(keys[i]).append("=").append(val).append("&");
			}else {
				strb.append(keys[i]).append("=").append(obj).append("&");
			}
		}
		strb.deleteCharAt(strb.length() - 1);
		if(key != null) {
			strb.append("&key").append("=").append(key);
		}
		return strb.toString();
	}

	public static Map<String, Object> xmlToMap(String xml) throws DocumentException {
		return XMLSerializer.parse(xml);
	}

}
