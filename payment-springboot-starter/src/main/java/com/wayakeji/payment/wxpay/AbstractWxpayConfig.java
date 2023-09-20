package com.wayakeji.payment.wxpay;

import com.wayakeji.payment.pojo.SignType;

/**
 * <p>微信支付配置抽象类, 实现{@link WxpayConfig}中部分不常变得方法
 * <p>eg: {@link #serverErrorRetry()}, {@link #switchDomainErrorNumber()}, {@link #domain()}, {@link #spareDomain()}
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see WxpayConfig
 * @since 1.8
 * @version 1.0
 * @time 2019年7月23日
 */
public abstract class AbstractWxpayConfig implements WxpayConfig {
	
	private static boolean serverErrorRetry = true;
	private static int switchDomainErrorNumber = 0;
	private static String domain = "https://api.mch.weixin.qq.com";
	private static String spareDomain = "https://api2.mch.weixin.qq.com";
	private static int connectTimeout = 3000;
	private static int readTimeout = 5000;
	
	@Override
	public boolean serverErrorRetry() {
		return serverErrorRetry;
	}
	
	@Override
	public int switchDomainErrorNumber() {
		return switchDomainErrorNumber;
	}
	
	@Override
	public String domain() {
		return domain;
	}
	
	@Override
	public String spareDomain() {
		return spareDomain;
	}
	
	@Override
	public SignType signType() {
		return SignType.MD5;
	}
	
	@Override
	public int connectTimeout() {
		return connectTimeout;
	}
	
	@Override
	public int readTimeout() {
		return readTimeout;
	}
	
	/**
	 * <p>设置主域名, 默认'https://api.mch.weixin.qq.com'
	 * @param arg
	 */
	public void setDomain(String arg) {
		domain = arg;
	}
	
	/**
	 * <p>设置备用域名, 默认'https://api2.mch.weixin.qq.com'
	 * @param arg
	 */
	public void setSpareDomain(String arg) {
		spareDomain = arg;
	}
	
	/**
	 * <p>设置当前单次请求错误时是否需要切换域名重试
	 * <p>默认为true, 需要重试
	 * @param arg
	 */
	public void setServerErrorRetry(boolean arg) {
		serverErrorRetry = arg;
	}
	
	/**
	 * <p>设置当前请求错误错误次数频繁达到多少次后主动切换域名
	 * <p>0为永不切换, 默认为0
	 * @param arg
	 */
	public void setSwitchDomainErrorNumber(int arg) {
		switchDomainErrorNumber = arg;
	}
	
}
