package com.wayakeji.alivod.dto;

public class UploadVideoVO {
	
	private Integer id;
	private String requestId;
	private String uploadAuth;
	private String uploadAddress;
	private String videoId;
	public UploadVideoVO() {}
	public UploadVideoVO(String requestId, String uploadAuth,
                         String uploadAddress, String videoId) {
		this.requestId = requestId;
		this.uploadAuth = uploadAuth;
		this.uploadAddress = uploadAddress;
		this.videoId = videoId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getUploadAuth() {
		return uploadAuth;
	}
	public void setUploadAuth(String uploadAuth) {
		this.uploadAuth = uploadAuth;
	}
	public String getUploadAddress() {
		return uploadAddress;
	}
	public void setUploadAddress(String uploadAddress) {
		this.uploadAddress = uploadAddress;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	@Override
	public String toString() {
		return "CreateUploadVideoVO [requestId=" + requestId
				+ ", uploadAuth=" + uploadAuth + ", uploadAddress="
				+ uploadAddress + ", videoId=" + videoId + "]";
	}
	
}
