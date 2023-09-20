package com.wayakeji.common.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorityUserPassword {
	
	private Long id;

	@NotBlank
	@Size(min = 6, max = 16)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
