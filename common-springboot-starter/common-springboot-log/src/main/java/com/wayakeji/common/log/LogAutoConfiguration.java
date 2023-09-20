/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wayakeji.common.log;

import com.wayakeji.common.log.aspect.SysLogAspect;
import com.wayakeji.common.log.aspect.WebLogAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 
 * @date 2019/2/1 日志自动配置
 * @EnableAsync  注解，开启异步任务。
 *
 * ConditionalOnWebApplication 当前项目是Web项目的条件下,创建bean
 * EnableAsync注解的意思是可以异步执行,就是开启多线程的意思.
 *注解 @RequiredArgsConstructor
 * 生成带有必需参数的构造函数。 必需的参数是最终字段和具有约束的字段，例如@NonNull
 *
 * 1. Full 模式 Lite 模式
 * proxyBeanMethods = true 或不写，是Full模式
 * proxyBeanMethods = false 是lite模式
 *
 * Full模式下通过方法调用指向的仍旧是原来的Bean
 * 利用cglib代理增强，bean是单例的，@Bean方法调用生成实例时，如果已经存在这个bean,直接返回
 *
 *  lite模式下，直接返回新实例对象,提高Spring启动速度
 */
@EnableAsync
@RequiredArgsConstructor
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {

//	@Bean
//	public SysLogListener sysLogListener() {
//		return new SysLogListener();
//	}

	@Bean
	public SysLogAspect sysLogAspect() {
		return new SysLogAspect();
	}

	@Bean
	public WebLogAspect webLogAspect() {
		return new WebLogAspect();
	}

}
