package com.wayakeji.alivod.dto;


import com.wayakeji.common.security.login.BufferParams;

/**
 * alivod需要的缓存数据
 * @author hu trace
 *
 */
public class AlivodBuffer extends BufferParams {
	
	protected Long bufAlivodUid;
	protected Integer bufAlivodRid;
	public Long getBufAlivodUid() {
		return bufAlivodUid;
	}
	public void setBufAlivodUid(Long bufAlivodUid) {
		this.bufAlivodUid = bufAlivodUid;
	}
	public Integer getBufAlivodRid() {
		return bufAlivodRid;
	}
	public void setBufAlivodRid(Integer bufAlivodRid) {
		this.bufAlivodRid = bufAlivodRid;
	}
	
}
