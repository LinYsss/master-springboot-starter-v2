package com.wayakeji.alivod.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

public class AlivodUrl extends AlivodBuffer {
	
	@NotBlank
	private String videoId;

	private String url;
	
	@NumberFormat
	private Integer state;

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
