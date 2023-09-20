package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

public class AuthorityApiDel {
	
	@NumberFormat
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
