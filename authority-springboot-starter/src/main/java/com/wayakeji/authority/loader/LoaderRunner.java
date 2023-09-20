package com.wayakeji.authority.loader;

import com.wayakeji.authority.service.AuthorityModuleService;
import com.wayakeji.common.security.login.CacheApis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 初始化，创建权限表等数据
 * @author 
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoaderRunner implements CommandLineRunner, Ordered {
	
	private final AuthorityModuleService authorityModuleService;

	@Override
	public void run(String... args){
		log.debug("开始初始化apis");
		CacheApis.set(authorityModuleService.selectCacheApis());
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
