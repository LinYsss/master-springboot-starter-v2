package com.wayakeji.alivod.dto;

import javax.validation.constraints.NotBlank;

public class AlivodRefresh {
	
	@NotBlank
	private String videoId;

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	
}
