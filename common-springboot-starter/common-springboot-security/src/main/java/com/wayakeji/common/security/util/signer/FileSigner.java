package com.wayakeji.common.security.util.signer;

import com.wayakeji.common.security.filter.HttpServerRequest;
import com.wayakeji.common.security.login.BufferUser;
import org.springframework.stereotype.Component;

@Component
public class FileSigner implements Signer {
	
	@Override
	public String sign(HttpServerRequest request, BufferUser buf) throws Exception {
		return request.getHeader("Scpsat-Sign");
	}

	@Override
	public String contentType() {
		return "multipart/form-data";
	}
	
}
