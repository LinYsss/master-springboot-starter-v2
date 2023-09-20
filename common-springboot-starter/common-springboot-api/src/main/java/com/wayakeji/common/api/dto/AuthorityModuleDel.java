package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

import java.util.List;

public class AuthorityModuleDel {
	
	@NumberFormat
	private List<Integer> ids;
	
	private List<AuthorityApiQuote> apiQuotes;
	
	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<AuthorityApiQuote> getApiQuotes() {
		return apiQuotes;
	}

	public void setApiQuotes(List<AuthorityApiQuote> apiQuotes) {
		this.apiQuotes = apiQuotes;
	}
	
}
