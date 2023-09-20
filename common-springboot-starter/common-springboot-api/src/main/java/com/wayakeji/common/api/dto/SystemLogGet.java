package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

public class SystemLogGet {
	
	private Date date;
	
	@NumberFormat
	private Integer pageStart;
	
	@NumberFormat
	private Integer pageSize;
	
	private long timestamp;
	
	private String reqId;
	
	private String rspId;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getRspId() {
		return rspId;
	}

	public void setRspId(String rspId) {
		this.rspId = rspId;
	}
	
}
