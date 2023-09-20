package com.wayakeji.common.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorityUserAdd {
	
	@NotBlank
	@Size(min = 2, max = 64)
	private String username;
	
	@NotBlank
	@Size(min = 6, max = 16)
	private String password;
	
	@NotBlank
	private String belong;
	
	private String email;
	private String phone;
	private String nickname;
	private String organization;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
}
