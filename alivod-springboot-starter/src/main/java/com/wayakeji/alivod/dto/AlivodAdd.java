package com.wayakeji.alivod.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

public class AlivodAdd extends AlivodBuffer {
	
	private Integer id;
	
	private String videoId;
	
	@NotBlank
	private String name;
	
	@NumberFormat
	private Long size;

	@NumberFormat
	private Integer videoTime;
	
	private String cover;

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

}
