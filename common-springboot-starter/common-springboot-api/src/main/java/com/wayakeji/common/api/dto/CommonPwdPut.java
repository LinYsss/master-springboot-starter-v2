package com.wayakeji.common.api.dto;

import javax.validation.constraints.NotBlank;

public class CommonPwdPut {
	
	private Long id;
	@NotBlank
	private String password;
	@NotBlank
	private String oldPassword;
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
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}
