package com.wayakeji.payment.wxpay;

import java.io.*;

/**
 * <p>默认的微信支付配置实现实体
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see AbstractWxpayConfig
 * @see WxpayConfig
 * @since 1.8
 * @version 1.0
 * @time 2019年7月23日
 */
public class DefaultWxpayConfig extends AbstractWxpayConfig {

	@Override
	public String appId() {
		return appId;
	}

	@Override
	public String mchId() {
		return mchId;
	}

	@Override
	public String mchKey() {
		return mchKey;
	}

	@Override
	public InputStream certStream() {
		return new ByteArrayInputStream(certBytes);
	}
	
	/**
	 * <p>构造方法
	 * @param appId 微信应用appId
	 * @param mchId 微信应用商户号
	 * @param mchKey 微信应用商户密钥
	 */
	public DefaultWxpayConfig(String appId, String mchId, String mchKey) {
		this.appId = appId;
		this.mchId = mchId;
		this.mchKey = mchKey;
	}
	
	/**
	 * <p>设置退款、取消等需要双向认证的授权文件
	 * @param path 文件路径
	 * @throws IOException
	 */
	public void setCertStream(String path) throws IOException {
		setCertStream(new FileInputStream(new File(path)));
	}
	
	/**
	 * <p>设置退款、取消等需要双向认证的授权文件
	 * @param is 文件输入流
	 * @throws IOException
	 */
	public void setCertStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[128];
		int i = 0;
		while((i = is.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		this.certBytes = baos.toByteArray();
	}
	
	/**
	 * <p>设置主备域名
	 * @param domain 主域名, 默认'https://api.mch.weixin.qq.com'
	 * @param spareDomain 备用域名, 默认'https://api2.mch.weixin.qq.com'
	 */
	public void setDomain(String domain, String spareDomain) {
		super.setDomain(domain);
		super.setSpareDomain(spareDomain);
	}
	
	protected String appId;
	protected String mchId;
	protected String mchKey;
	protected byte[] certBytes;

}
