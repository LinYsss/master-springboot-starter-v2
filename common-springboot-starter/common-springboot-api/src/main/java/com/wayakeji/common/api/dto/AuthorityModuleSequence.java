package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

public class AuthorityModuleSequence {
	
	@NumberFormat
	private Integer id;
	@NumberFormat
	private Integer sequence;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
}
