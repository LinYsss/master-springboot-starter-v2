package com.wayakeji.alivod.dto;

import org.springframework.format.annotation.NumberFormat;

public class AlivodGet extends AlivodBuffer {
	
	private String name;
	
	private Integer state;
	
	@NumberFormat
	private Integer pageStart;
	
	@NumberFormat
	private Integer pageSize;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getPageStart() {
		return pageStart;
	}

	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
