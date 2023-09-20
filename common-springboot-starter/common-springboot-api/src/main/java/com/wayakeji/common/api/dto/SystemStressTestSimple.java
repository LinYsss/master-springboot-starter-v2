package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class SystemStressTestSimple {
	
	@NotBlank
	private String data;
	
	private Date time;
	
	@NumberFormat
	private Integer rspstrlen;
	
	public SystemStressTestSimple() {
		time = new Date();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getRspstrlen() {
		return rspstrlen;
	}

	public void setRspstrlen(Integer rspstrlen) {
		this.rspstrlen = rspstrlen;
	}
	
}
