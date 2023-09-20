package com.wayakeji.payment.common;

/**
 *微信支付交易类型
 *
 * @version 1.0
 * @auther bojan
 * @date 2021/9/28 11:10
 */
public enum WeChatTradeType {
	/**
	 * JSAPI 支付， 小程序支付
	 */
	JSAPI(),
	/**
	 *Native支付
	 */
	NATIVE(),
	/**
	 *app支付
	 */
	APP(),
	/**
	 *付款码支付
	 */
	MICROPAY(),
	/**
	 *H5支付
	 */
	MWEB();
}
