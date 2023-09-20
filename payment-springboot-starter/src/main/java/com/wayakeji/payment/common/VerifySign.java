package com.wayakeji.payment.common;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.wayakeji.common.core.util.code.MD5;
import com.wayakeji.common.core.util.qs.QueryStringObject;
import com.wayakeji.payment.alipay.AlipayClientFactory;
import com.wayakeji.payment.alipay.AlipayConfig;
import com.wayakeji.payment.pojo.AlipayAppNotify;
import com.wayakeji.payment.pojo.AlipayPageNotify;
import com.wayakeji.payment.pojo.WxpayNotify;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>支付结果通知参数验证签名工具类
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see ParameterParse
 * @see AlipaySignature#rsaCheckContent(String, String, String, String)
 * @see ParameterParse
 * @since 1.8
 * @version 1.0
 * @time 2019年7月24日
 */
public class VerifySign {
	
	/**
	 * <p>验证支付宝接收的参数签名是否匹配
	 * @param body <b>注: 参数虽是Object, 但目前仅支持{@link AlipayAppNotify}、{@link AlipayPageNotify}</b>两种类型
	 * @return true表示匹配, false表示签名不匹配
	 * @throws AlipayApiException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean alipay(String body) throws AlipayApiException {
		JSONObject json = new JSONObject(new HashMap<String, Object>(QueryStringObject.parse(body).innerMap()));
		AlipayConfig config = AlipayClientFactory.getConfig(json.getString("app_id"));
		Map<String, String> map = new HashMap(json);
		return AlipaySignature.rsaCheckV1(map, config.getPublicKey(), config.getCharset(), "RSA2");
	}
	
	/**
	 * <p>验证微信接收的参数签名是否匹配
	 * @param param <b>注: 参数虽是Object, 但目前仅支持{@link WxpayNotify}</b>类型
	 * @return true表示匹配, false表示签名不匹配
	 * @throws Exception
	 */
	public static boolean wxpay(Object param) throws Exception {
		JSONObject json = (JSONObject) JSONObject.toJSON(param);
		Map<String, Object> map = json.getInnerMap();
		String content = ParameterParse.mapToSortQueryString(map, Payment.getClient(map.get("appid").toString()).getConfig().mchKey());
		String sign = MD5.upperCase(content);
		return map.get("sign").toString().equals(sign);
	}
	
}
