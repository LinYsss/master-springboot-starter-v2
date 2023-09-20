package com.wayakeji.common.security.util.signer;


import com.wayakeji.common.core.util.code.MD5;
import com.wayakeji.common.core.util.code.TextCoding;
import com.wayakeji.common.security.config.Configurations;
import com.wayakeji.common.security.filter.HttpServerRequest;
import com.wayakeji.common.security.login.BufferUser;
import org.springframework.stereotype.Component;

@Component
public class JSONScpsatSigner implements Signer {
	
	@Override
	public String sign(HttpServerRequest request, BufferUser buf) {
		String str = TextCoding.disrupt(buf.getSignKey()) + "." + request.getBody();
		return MD5.lowerCase(str, Configurations.charset);
	}
	
	@Override
	public String contentType() {
		return "application/scpsat-json";
	}
	
}
