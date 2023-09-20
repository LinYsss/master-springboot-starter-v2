package com.wayakeji.payment.wxpay;

import com.wayakeji.payment.pojo.SignType;

import java.io.InputStream;

/**
 * <p>微信配置的标准接口
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @since 1.8
 * @version 1.0
 * @time 2019年7月23日
 */
public interface WxpayConfig {
	
	/**
	 * <p>appId
	 * @return
	 */
	String appId();
	
	/**
	 * <p>商户号
	 * @return
	 */
	String mchId();
	
	/**
	 * <p>商户密钥
	 * @return
	 */
	String mchKey();
	
	/**
	 * <p>授权文件
	 * @return
	 */
	InputStream certStream();
	
	/**
	 * <p>请求的主域名
	 * @return
	 */
	String domain();
	
	/**
	 * <p>请求的备用域名
	 * @return
	 */
	String spareDomain();
	
	/**
	 * <p>签名类型
	 * @return
	 */
	SignType signType();
	
	/**
	 * <p>当请求主域名频繁出现多少次后切换备用域名
	 * @return
	 */
	int switchDomainErrorNumber();
	
	/**
	 * <p>当请求微信支付，微信方出现5xx的错误时，针对当前请求是否切换域名重试，最多切换一次
	 * @return
	 */
	boolean serverErrorRetry();
	
	/**
	 * <p>连接超时时间
	 * @return
	 */
	int connectTimeout();
	
	/**
	 * <p>读取超时时间
	 * @return
	 */
	int readTimeout();
	
}
