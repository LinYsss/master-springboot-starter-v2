package com.wayakeji.common.api.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorityApiVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;
	private String name;
	private String api;
	private String method;
	
}
