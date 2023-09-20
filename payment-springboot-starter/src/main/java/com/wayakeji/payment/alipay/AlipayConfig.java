package com.wayakeji.payment.alipay;

/**
 * <p>支付宝参数配置类
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd tracy</a>
 * @since 1.8
 * @version 1.0
 * @time 2019年3月5日
 */
public class AlipayConfig {
	
	private String appId;
	private String privateKey;
	private String publicKey;
	
	public AlipayConfig(String appId, String privateKey, String publicKey) {
		this.appId = appId;
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}

	public String getAppId() {
		return appId;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public String getGateway() {
		return "https://openapi.alipay.com/gateway.do";
	}
	public String getCharset() {
		return "UTF-8";
	}
	public String getSignType() {
		return "RSA2";
	}
	public String getFormat() {
		return "json";
	}
	
}

