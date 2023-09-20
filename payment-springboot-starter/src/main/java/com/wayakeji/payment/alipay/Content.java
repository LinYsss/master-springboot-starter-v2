package com.wayakeji.payment.alipay;

import com.alibaba.fastjson.JSONObject;

public class Content {
	
	private JSONObject json;
	
	private Content() {
		json = new JSONObject();
	}
	
	public static Content bulid() {
		return new Content();
	}
	
	public Content set(String key, Object value) {
		json.put(key, value);
		return this;
	}

	@Override
	public String toString() {
		return json.toJSONString();
	}
	
}
