package com.wayakeji.payment.common;

import com.alibaba.fastjson.JSONObject;
import com.wayakeji.payment.exception.ParameterParseException;
import com.wayakeji.payment.exception.PaymentRequestException;
import com.wayakeji.payment.exception.PaymentResponseException;

import java.util.Map;

/**
 * <p>支付模块, 支付顶级接口, 设置参数完毕后调用{@link #execute()}即可
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @since 1.8
 * @version 1.0
 * @time 2019年7月22日
 */
public interface Module<T> {
	
	/**
	 * <p>设置请求参数
	 * @param param 请求参数
	 * @throws ParameterParseException 当解析参数出错时会抛出异常
	 */
	void parameter(T param) throws ParameterParseException;
	
	/**
	 * <p>设置请求参数
	 * @param param 请求参数
	 * @throws ParameterParseException 当解析参数出错时会抛出异常
	 */
	void parameter(Map<String, Object> params) throws ParameterParseException;
	
	/**
	 * <p>获取请求参数
	 */
	Map<String, Object> parameter();
	
	/**
	 * <p>执行
	 * @return 响应数据{@link JSONObject}
	 * @throws PaymentResponseException 
	 * @throws PaymentRequestException
	 */
	JSONObject execute() throws PaymentRequestException, PaymentResponseException;
	
}
