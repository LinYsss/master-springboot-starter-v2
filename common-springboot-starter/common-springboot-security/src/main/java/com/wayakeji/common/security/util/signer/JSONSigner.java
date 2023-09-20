package com.wayakeji.common.security.util.signer;

import cn.hutool.core.util.ObjectUtil;
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
public class JSONSigner implements Signer {
	
	private ParseConfig[] config = new ParseConfig[] {
			ParseConfig.KEY_SORT,ParseConfig.NOT_DECODE_VALUE, ParseConfig.BLANK_VALUE_APPEND
	};
	
	public void setConfig(ParseConfig[] configs) {
		this.config = configs;
	}

	@Override
	public String sign(HttpServerRequest request, BufferUser buf) {
		JSONObject json = JSONObject.parseObject(request.getBody().toString());
		Set<Entry<String, Object>> set = json.getInnerMap().entrySet();
		QueryStringObject qso = new QueryStringObject(json.size(), config);
		for(Entry<String, Object> entry : set) {
			if(!ObjectUtil.isEmpty(entry.getValue())){
				qso.put(entry.getKey().toString(), entry.getValue().toString());
			}
		}
		String str = TextCoding.disrupt(buf.getSignKey()) + "." + qso.toString();
		return MD5.lowerCase(str, Configurations.charset);
	}
	
	@Override
	public String contentType() {
		return "application/json";
	}
	
}
