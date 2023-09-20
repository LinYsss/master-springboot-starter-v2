package com.wayakeji.payment.wxpay;

import com.alibaba.fastjson.JSONObject;
import com.wayakeji.payment.common.AbstractModule;
import com.wayakeji.payment.common.CommonPojo;
import com.wayakeji.payment.common.ParameterParse;
import com.wayakeji.payment.exception.PaymentRequestException;
import com.wayakeji.payment.exception.PaymentResponseException;
import com.wayakeji.payment.pojo.ContentType;
import com.wayakeji.payment.pojo.HttpMethod;
import org.dom4j.DocumentException;

public abstract class DefaultWxpayModule<T extends CommonPojo> extends AbstractModule<T> {

	public DefaultWxpayModule(WxpayConfig config) {
		super(config);
	}
	
	@Override
	public HttpMethod method() {
		return HttpMethod.POST;
	}

	@Override
	public ContentType contentType() {
		return ContentType.XML;
	}
	
	public boolean useCert() {
		return false;
	}

	@Override
	public JSONObject parseResponse(String str) throws PaymentRequestException, PaymentResponseException {
		try {
			return new JSONObject(ParameterParse.xmlToMap(str));
		}catch (DocumentException e) {
			throw new PaymentResponseException(e.getMessage(), e);
		}
	}
	
	@Override
	public JSONObject execute() throws PaymentRequestException, PaymentResponseException {
		if(useCert()) {
			String url = (isSpare ? config.spareDomain() : config.domain()) + path();
			CertRequest request = new CertRequest();
			String resp;
			try {
				resp = request.execute(config, url, parameter, headers);
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
			}catch (Exception e) {
				throw new PaymentRequestException(e.getMessage(), e);
			}
		}else {
			return super.execute();
		}
	}
	
}
