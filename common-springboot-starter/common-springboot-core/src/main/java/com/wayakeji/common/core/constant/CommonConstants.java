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

package com.wayakeji.common.core.constant;

/**
 * @author lengleng
 * @date 2019/2/1
 */
public interface CommonConstants {

	/**
	 * 删除
	 */
	String STATUS_DEL = "1";

	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单树根节点
	 */
	Long MENU_TREE_ROOT_ID = -1L;

	/**
	 * 菜单
	 */
	String MENU = "0";

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * JSON 资源
	 */
	String CONTENT_TYPE = "application/json; charset=utf-8";

	/**
	 * 前端工程名
	 */
	String FRONT_END_PROJECT = "demo-view";

	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "demo";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 0;

	/**
	 * 失败标记
	 */
	Integer FAIL = -1;

	/**
	 * 当前页
	 */
	String CURRENT = "current";

	/**
	 * size
	 */
	String SIZE = "size";

	/**
	 * 请求开始时间
	 */
	String REQUEST_START_TIME = "REQUEST-START-TIME";

	/**
	 * http请求
	 */
	String HTTP = "http://";

	/**
	 * https请求
	 */
	String HTTPS = "https://";
	/**
	 * RMI 远程方法调用
	 */
	String LOOKUP_RMI = "rmi:";

	/**
	 * LDAP 远程方法调用
	 */
	String LOOKUP_LDAP = "ldap:";

	/**
	 * LDAPS 远程方法调用
	 */
	String LOOKUP_LDAPS = "ldaps:";

	/**
	 * 定时任务白名单配置（仅允许访问的包名，如其他需要可以自行添加）
	 */
	String[] JOB_WHITELIST_STR = { "com.wayakeji" };

	/**
	 * 定时任务违规的字符
	 */
	String[] JOB_ERROR_STR = { "java.net.URL", "javax.naming.InitialContext", "org.yaml.snakeyaml",
			"org.springframework", "org.apache" };

}
