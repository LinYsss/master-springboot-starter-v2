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

package com.wayakeji.common.log.aspect;

import com.wayakeji.common.api.entity.AuthorityLog;
import com.wayakeji.common.log.util.LogTypeEnum;
import com.wayakeji.common.log.util.SysLogUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 操作日志使用spring event异步入库
 *
 * @author L.cm
 */
@Aspect
@Slf4j
public class WebLogAspect {

	@Pointcut("execution(* com..*.controller..*(..))) ||execution(* com..*.controllers..*(..))) ||@annotation(com.wayakeji.common.log.annotation.SysLog)||@annotation(javax.websocket.server.ServerEndpoint)")
	public void webLog() {
	}

	@Around("webLog()")
	@SneakyThrows
	public Object around(ProceedingJoinPoint point) {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

		AuthorityLog logVo = SysLogUtils.getSysLog();
		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Object obj;
		try {
			obj = point.proceed();
		}
		catch (Exception e) {
			logVo.setType(LogTypeEnum.ERROR.getType());
			logVo.setException(e.getMessage());
			throw e;
		}
		finally {
			Long endTime = System.currentTimeMillis();
			logVo.setTime(endTime - startTime);
			log.debug("来自IP地址：{}的请求，接口：{}，请求耗时：{}ms", logVo.getRemoteAddr(), logVo.getRequestUri(), logVo.getTime());
		}
		return obj;
	}

}
