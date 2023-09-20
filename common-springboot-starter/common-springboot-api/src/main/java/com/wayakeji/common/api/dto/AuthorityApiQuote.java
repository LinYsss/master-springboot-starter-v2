package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

public class AuthorityApiQuote {
	
	@NumberFormat
	private Integer id;
	
	@NumberFormat
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

	public void setQuote(Integer quote) {
		this.quote = quote;
	}
}
