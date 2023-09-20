package com.wayakeji.payment.common;

import com.alipay.api.AlipayClient;
import com.wayakeji.payment.alipay.AlipayClientFactory;
import com.wayakeji.payment.alipay.AlipayConfig;
import com.wayakeji.payment.wxpay.WxpayConfig;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>支付入口, 通过此类构建支付客户端
 *
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @version 1.0
 * @time 2019年7月24日
 * @see WxpayClient
 * @see AlipayClient
 * @since 1.8
 */
public class Payment {

	private static ConcurrentHashMap<String, WxpayClient> wxpayClients = new ConcurrentHashMap<>();

	/**
	 * <p>构建微信支付客户端, 正常情况下对一个appId的所有操作只需构建一次即可
	 * <p>但此方法进行了客户端缓存, 再次构建相同的配置时将直接返回缓存的客户端
	 * <p>所以多次调用不会有什么影响
	 *
	 * @param config {@link WxpayConfig}
	 * @return {@link WxpayClient}
	 */
	public static WxpayClient buildClient(WxpayConfig config) {
		String key = config.appId();
		WxpayClient client;
		synchronized(key) {
			client = wxpayClients.get(key);
			if(client == null) {
				client = new WxpayClient(config);
				wxpayClients.put(config.appId(), client);
			}
		}
		return client;
	}

	/**
	 * <p>构建支付宝支付客户端, 正常情况下对一个appId的所有操作只需构建一次即可
	 * <p>但此方法进行了客户端缓存, 再次构建相同的配置时将直接返回缓存的客户端
	 * <p>所以多次调用不会有什么影响
	 *
	 * @param config {@link AlipayConfig}
	 * @return {@link AlipayClient}
	 */
	public static AlipayClient buildClient(AlipayConfig config) {
		return AlipayClientFactory.build(config);
	}

	public static WxpayClient getClient(String appId) {
		return wxpayClients.get(appId);
	}

}
