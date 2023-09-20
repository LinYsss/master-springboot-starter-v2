package com.wayakeji.common.security.util.signer;


import com.wayakeji.common.core.util.code.MD5;
import com.wayakeji.common.core.util.code.TextCoding;
import com.wayakeji.common.core.util.qs.ParseConfig;
import com.wayakeji.common.core.util.qs.QueryString;
import com.wayakeji.common.core.util.qs.QueryStringObject;
import com.wayakeji.common.security.config.Configurations;
import com.wayakeji.common.security.filter.HttpServerRequest;
import com.wayakeji.common.security.login.BufferUser;
import org.springframework.stereotype.Component;

@Component
public class QSSigner implements Signer {

	private ParseConfig[] config = new ParseConfig[] {
			ParseConfig.KEY_SORT
	};
	
	public void setConfig(String configs) {
		String[] arr = configs.split(",");
		config = new ParseConfig[arr.length];
		for(int i = 0; i < arr.length; i++) {
			config[i] = ParseConfig.valueOf(arr[i].trim());
		}
	}
	
	@Override
	public String sign(HttpServerRequest request, BufferUser buf) {
		QueryStringObject qso = QueryString.parse(request.getBody().toString(), config);
		return MD5.lowerCase(TextCoding.disrupt(buf.getSignKey()) + "." + qso.toString(), Configurations.charset);
	}

	@Override
	public String contentType() {
		return "application/x-www-form-urlencoded";
	}

}
