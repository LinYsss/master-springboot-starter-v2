package com.wayakeji.qiniuyun.params;


import com.wayakeji.common.security.login.BufferParams;

/**
 * qiniuyun需要的缓存数据
 * @author hu trace
 *
 */
public class QiniuyunBuffer extends BufferParams {
	
	protected Long bufQiniuyunUid;
	protected Integer bufQiniuyunRid;
	public Long getBufQiniuyunUid() {
		return bufQiniuyunUid;
	}
	public void setBufQiniuyunUid(Long bufQiniuyunUid) {
		this.bufQiniuyunUid = bufQiniuyunUid;
	}
	public Integer getBufQiniuyunRid() {
		return bufQiniuyunRid;
	}
	public void setBufQiniuyunRid(Integer bufQiniuyunRid) {
		this.bufQiniuyunRid = bufQiniuyunRid;
	}
	
}
