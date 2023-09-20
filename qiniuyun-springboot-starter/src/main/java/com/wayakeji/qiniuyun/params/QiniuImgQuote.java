package com.wayakeji.qiniuyun.params;

public class QiniuImgQuote {
	
	private Integer id;
	/**
	 * 正数表示加多少，负数表示减去多少
	 */
	private Integer quote;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuote() {
		return quote;
	}
	/**
	 * 正数表示加多少，负数表示减去多少
	 */
	public void setQuote(Integer quote) {
		this.quote = quote;
	}
	
}
