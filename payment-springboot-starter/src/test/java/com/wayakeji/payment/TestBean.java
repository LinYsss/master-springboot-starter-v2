package com.wayakeji.payment;

import com.alibaba.fastjson.annotation.JSONField;

public class TestBean {
	
	@JSONField(name = "str_str1")
	private String strStr1;
	@JSONField(name = "str_str2")
	private String strStr2;
	@JSONField(name = "str_str3")
	private String strStr3;
	@JSONField(name = "str_str4")
	private String strStr4;
	public String getStrStr1() {
		return strStr1;
	}
	public void setStrStr1(String strStr1) {
		this.strStr1 = strStr1;
	}
	public String getStrStr2() {
		return strStr2;
	}
	public void setStrStr2(String strStr2) {
		this.strStr2 = strStr2;
	}
	public String getStrStr3() {
		return strStr3;
	}
	public void setStrStr3(String strStr3) {
		this.strStr3 = strStr3;
	}
	public String getStrStr4() {
		return strStr4;
	}
	public void setStrStr4(String strStr4) {
		this.strStr4 = strStr4;
	}
	@Override
	public String toString() {
		return "TestBean [strStr1=" + strStr1 + ", strStr2=" + strStr2
				+ ", strStr3=" + strStr3 + ", strStr4=" + strStr4 + "]";
	}
	
}
