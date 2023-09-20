package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorityApiPut {
	
	@NumberFormat
	private Integer id;
	
	@NotBlank
	@Size(max = 128)
	private String api;
	
	@NotBlank
	@Size(max = 16)
	private String method;
	
	private String explain;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}
