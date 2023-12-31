package com.wayakeji.common.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 添加角色的参数接收类
 * <p>必填: name
 * <p>选填: explain
 * @author hu trace
 */
public class AuthorityRoleAdd {
	
	private Integer id;
	
	private Integer pid;
	
	@NotBlank
	private String name;
	
	private String explain;
	
	@NotNull
	private Long clientId;
	
	private Date addTime = new Date();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
}
