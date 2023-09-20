package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

public class AuthorityUserEnabled {
	
	private Long id;
	
	@NumberFormat
	private Integer enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
}
