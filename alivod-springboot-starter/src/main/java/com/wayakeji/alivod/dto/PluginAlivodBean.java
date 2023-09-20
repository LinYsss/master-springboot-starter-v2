package com.wayakeji.alivod.dto;

import java.util.Date;

public class PluginAlivodBean {
	
	private Integer id;
	private String videoId;
	private String cover;
	private String videoOriginalAddr;
	private String videoTranscodeAddr;
	private Long size;
	private Integer videoTime;
	private String name;
	private Date startTime;
	private Date endTime;
	private Integer state;
	private Long addUid;
	private Integer addRid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getVideoOriginalAddr() {
		return videoOriginalAddr;
	}
	public void setVideoOriginalAddr(String videoOriginalAddr) {
		this.videoOriginalAddr = videoOriginalAddr;
	}
	public String getVideoTranscodeAddr() {
		return videoTranscodeAddr;
	}
	public void setVideoTranscodeAddr(String videoTranscodeAddr) {
		this.videoTranscodeAddr = videoTranscodeAddr;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Integer getVideoTime() {
		return videoTime;
	}
	public void setVideoTime(Integer videoTime) {
		this.videoTime = videoTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getAddUid() {
		return addUid;
	}
	public void setAddUid(Long addUid) {
		this.addUid = addUid;
	}
	public Integer getAddRid() {
		return addRid;
	}
	public void setAddRid(Integer addRid) {
		this.addRid = addRid;
	}
	
}
