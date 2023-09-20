package com.wayakeji.common.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorityBelongDel {
	
	@NotBlank
	@Size(max = 32)
	private String belong;
	
	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}
	
}
