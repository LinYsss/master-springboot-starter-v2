package com.wayakeji.payment.common;

import com.alibaba.fastjson.JSONObject;
import com.wayakeji.payment.exception.ParameterParseException;
import com.wayakeji.payment.exception.PaymentRequestException;
import com.wayakeji.payment.exception.PaymentResponseException;
import com.wayakeji.payment.pojo.ContentType;
import com.wayakeji.payment.pojo.HttpMethod;
import com.wayakeji.payment.wxpay.WxpayConfig;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractModule<T extends CommonPojo> implements Module<T> {
	
	public AbstractModule(WxpayConfig config) {
		this.config = config;
		headers = new HashMap<>();
	}
	
	protected WxpayConfig config;
	protected boolean isSpare = false;
	protected String parameter;
	protected Map<String, Object> param;
	protected HashMap<String, String> headers;

	/**
	 * <p>请求路径
	 * @return
	 */
	public abstract String path();
	
	/**
	 * <p>请求方式
	 * @return
	 */
	public abstract HttpMethod method();
	
	/**
	 * <p>请求类型
	 * @return
	 */
	public abstract ContentType contentType();
	
	/**
	 * <p>将请求响应的结果字符串转换成{@link JSONObject}对象, 子类务必需要实现它
	 * @param str
	 * @return
	 * @throws PaymentResponseException
	 */
	public abstract JSONObject parseResponse(String str) throws PaymentRequestException, PaymentResponseException;
	
	@Override
	public Map<String, Object> parameter() {
		return param;
	}
	
	/**
	 * <p>将Map参数解析为适用的参数字符
	 * @param param
	 * @throws ParameterParseException
	 */
	public void parameter(Map<String, Object> param) throws ParameterParseException {
		this.param = param;
		switch(contentType()) {
			case XML:
				headers.put("Content-Type", "text/xml");
				parameter = ParameterParse.mapToSignXml(param, config.mchKey(), config.signType());
				break;
			case JSON:
				headers.put("Content-Type", "application/json");
				break;
			case QUERY_STRING:
				break;
			default:
				throw new ParameterParseException("参数解析错误...");
		}
	}

	@Override
	public JSONObject execute() throws PaymentRequestException, PaymentResponseException {
		CommonRequest request = new CommonRequest();
		String url = (isSpare ? config.spareDomain() : config.domain()) + path();
		String resp;
		switch(method()) {
			case GET:
				resp = request.get(url, parameter, headers);
				break;
			default:
				resp = request.post(url, parameter, headers, method().name());
				break;
		}
		// 如果状态为5xx
		if(request.getRespCode() >= 500) {
			// 如果开启了5xx错误切换域名重试
			if(config.serverErrorRetry()) {
				// 如果还没有重试
				if(!isSpare) {
					// 则重试
					return execute();
				}else {
					return null;
				}
			}else {
				return null;
			}
		}else {
			return parseResponse(resp);
		}
	}
	
	@Override
	public void parameter(CommonPojo param) throws ParameterParseException {
		Map<String, Object> map = param.toMap();
		map.put("appid", config.appId());
		map.put("mch_id", config.mchId());
		this.parameter(map);
	}
	
}
