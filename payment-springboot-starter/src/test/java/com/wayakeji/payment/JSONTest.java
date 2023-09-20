package com.wayakeji.payment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONTest {
	
	public static void main(String[] args) {
		String str = "{\"str_str4\":\"value4\",\"str_str3\":\"value3\",\"str_str2\":\"value2\",\"str_str1\":\"value1\"}";
		TestBean bean = JSON.parseObject(str, TestBean.class);
		System.out.println(bean);
		JSONObject json = (JSONObject) JSON.toJSON(bean);
		System.out.println(json);
	}
	
}
