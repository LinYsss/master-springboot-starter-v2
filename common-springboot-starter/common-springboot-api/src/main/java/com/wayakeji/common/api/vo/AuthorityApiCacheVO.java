package com.wayakeji.common.api.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorityApiCacheVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String api;
	private String method;

}
