package com.wayakeji.common.security.util.signer;

import com.alibaba.fastjson.JSONObject;
import com.wayakeji.common.core.util.code.MD5;
import com.wayakeji.common.core.util.code.TextCoding;
import com.wayakeji.common.core.util.qs.ParseConfig;
import com.wayakeji.common.core.util.qs.QueryStringObject;
import com.wayakeji.common.security.config.Configurations;
import com.wayakeji.common.security.filter.HttpServerRequest;
import com.wayakeji.common.security.login.BufferUser;
import org.springframework.stereotype.Component;

import java.util.Map.Entry;
import java.util.Set;

@Component
public class XMLSigner implements Signer {
	
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
	public String sign(HttpServerRequest request, BufferUser buf) throws Exception {
		JSONObject json = JSONObject.parseObject(request.getBody().toString());
		Set<Entry<String, Object>> set = json.getInnerMap().entrySet();
		QueryStringObject qso = new QueryStringObject(json.size(), config);
		for(Entry<String, Object> entry : set) {
			qso.put(entry.getKey(), entry.getValue().toString());
		}
		return MD5.lowerCase(TextCoding.disrupt(buf.getSignKey()) + "." + qso.toString(), Configurations.charset);
	}
	
	@Override
	public String contentType() {
		return "text/xml";
	}
	
}
