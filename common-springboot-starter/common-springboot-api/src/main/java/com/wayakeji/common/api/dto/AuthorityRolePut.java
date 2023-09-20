package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

/**
 * 修改角色信息的参数接口类
 * <pre>
 *  必填: 
 *    id -> 修改的数据标识
 *    name -> 名称
 * @author hu trace
 */
public class AuthorityRolePut {
	
	@NumberFormat
	private Integer id;
	
	@NotBlank
	private String name;
	
	private String explain;

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

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}
