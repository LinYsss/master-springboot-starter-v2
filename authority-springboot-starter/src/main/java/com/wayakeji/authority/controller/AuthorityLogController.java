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
package com.wayakeji.authority.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.wayakeji.authority.limiter.LimitType;
import com.wayakeji.authority.limiter.RateLimiter;
import com.wayakeji.authority.service.AuthorityLogService;
import com.wayakeji.common.api.dto.AuthorityLogDTO;
import com.wayakeji.common.api.entity.AuthorityLog;
import com.wayakeji.common.core.util.R;
import com.wayakeji.common.security.annotation.Authority;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Authority
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/log")
@Tag(name = "日志管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class AuthorityLogController {

	private final AuthorityLogService authorityLogService;

	/**
	 * 简单分页查询
	 * @param page 分页对象
	 * @param log 系统日志
	 * @return
	 */
	@GetMapping("/page")
	public R<IPage<AuthorityLog>> getLogPage(Page page, AuthorityLogDTO log) {
		return R.ok(authorityLogService.getLogByPage(page, log));
	}

	/**
	 * 删除日志
	 * @param authorityLog ID
	 * @return success/false
	 */
	@DeleteMapping
	public R<Boolean> removeById(@RequestBody AuthorityLog authorityLog) {
		return R.ok(authorityLogService.removeById(authorityLog.getId()));
	}

	/**
	 * 插入日志
	 * @param authorityLog 日志实体
	 * @return success/false
	 */
	@PostMapping
	public R<Boolean> save(@Valid @RequestBody AuthorityLog authorityLog) {
		return R.ok(authorityLogService.save(authorityLog));
	}

	/**
	 * 导出excel 表格
	 * @param logDTO 查询条件
	 * @return EXCEL
	 */
	@ResponseExcel
	@GetMapping("/export")
	public List<AuthorityLog> export(AuthorityLogDTO logDTO) {
		return authorityLogService.getLogList(logDTO);
	}

}
