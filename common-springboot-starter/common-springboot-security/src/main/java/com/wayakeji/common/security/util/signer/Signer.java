package com.wayakeji.common.security.util.signer;


import com.wayakeji.common.security.filter.HttpServerRequest;
import com.wayakeji.common.security.login.BufferUser;

/**
 * 验签签名者接口
 * @author hu trace
 */
public interface Signer {
	
	/**
	 * 开始签名
	 */
	String sign(HttpServerRequest request, BufferUser buf) throws Exception;
	
	/**
	 * 获取匹配当前签名器的内容类型
	 * @return
	 */
	String contentType();

	
}