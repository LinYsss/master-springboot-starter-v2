package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AuthorityModulePut {
	
	@NumberFormat
	private Integer id;
	
	@NotBlank
	private String name;
	
	@NumberFormat
	private Integer type;
	private String icon;
	private String page;
	private String explain;
	private String path;
	private List<Integer> apis;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Integer> getApis() {
		return apis;
	}

	public void setApis(List<Integer> apis) {
		this.apis = apis;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}
