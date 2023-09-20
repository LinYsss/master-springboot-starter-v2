package com.wayakeji.authority.controller;

import com.wayakeji.authority.service.impl.AuthorityLogin;
import com.wayakeji.common.api.dto.LoginDefault;
import com.wayakeji.common.log.annotation.SysLog;
import com.wayakeji.common.security.filter.HttpServerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {
	
	private final AuthorityLogin authorityLogin;

	@SysLog
	@RequestMapping(value = "default", method = RequestMethod.POST)
	public Map<String, Object> defaultLogin(HttpServerRequest request, @RequestBody LoginDefault params)  {
		return authorityLogin.login(request, AuthorityLogin.LoginType.USERNAME, params.getUsername(), params.getPassword());
	}
	
}
