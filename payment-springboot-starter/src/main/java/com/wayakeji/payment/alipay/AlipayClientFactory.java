package com.wayakeji.payment.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>支付宝客户端调用接口工厂类
 *
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd tracy</a>
 * @version 1.0
 * @time 2019年3月5日
 * @since 1.8
 */
public class AlipayClientFactory {

	/**
	 * API调用客户端
	 */
	private static ConcurrentHashMap<String, AlipayClient> alipayClients = new ConcurrentHashMap<>();
	/**
	 * Configs
	 */
	private static ConcurrentHashMap<String, AlipayConfig> alipayConfigs = new ConcurrentHashMap<>();

	/**
	 * <p>创建或者获取{@link AlipayClient}对象
	 *
	 * @param config {@link AlipayConfig}
	 * @return {@link AlipayClient}
	 */
	public static AlipayClient build(AlipayConfig config) {
		String key = config.getAppId();
		synchronized(key) {
			if(null == alipayClients.get(key)) {
				alipayClients.put(key, createAlipayClient(config));
				alipayConfigs.put(key, config);
			}
		}
		
		return alipayClients.get(key);
	}

	/**
	 * <p>创建并返回{@link AlipayClient}对象
	 *
	 * @param config {@link AlipayConfig}
	 * @return {@link AlipayClient}
	 */
	private static AlipayClient createAlipayClient(AlipayConfig config) {
		return new DefaultAlipayClient(config.getGateway(), config.getAppId(),
				config.getPrivateKey(), config.getFormat(), config.getCharset(),
				config.getPublicKey(), config.getSignType());
	}

	/**
	 * <p>清除客户端
	 * <p>在修改了参数后进行调用
	 */
	public static void clear() {
		alipayClients = null;
	}

	public static AlipayConfig getConfig(String appId) {
		return alipayConfigs.get(appId);
	}

}
