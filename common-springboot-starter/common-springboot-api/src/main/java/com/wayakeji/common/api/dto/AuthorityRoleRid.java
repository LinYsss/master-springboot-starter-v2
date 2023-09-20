package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

/**
 * 角色ID参数接收类
 * @author hu trace
 */
public class AuthorityRoleRid {
	
	@NumberFormat
	private Integer rid;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
}
