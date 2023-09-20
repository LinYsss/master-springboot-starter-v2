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

package com.wayakeji.authority.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayakeji.authority.mapper.AuthorityLogMapper;
import com.wayakeji.authority.service.AuthorityLogService;
import com.wayakeji.common.api.dto.AuthorityLogDTO;
import com.wayakeji.common.api.entity.AuthorityLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Service
public class AuthorityLogServiceImpl extends ServiceImpl<AuthorityLogMapper, AuthorityLog> implements AuthorityLogService {

	@Override
	public Page getLogByPage(Page page, AuthorityLogDTO logDTO) {
		return baseMapper.selectPage(page, buildQueryWrapper(logDTO));
	}

	/**
	 * 列表查询日志
	 * @param sysLog 查询条件
	 * @return List
	 */
	@Override
	public List getLogList(AuthorityLogDTO sysLog) {
		return baseMapper.selectList(buildQueryWrapper(sysLog));
	}

	/**
	 * 构建查询的 wrapper
	 * @param sysLog 查询条件
	 * @return LambdaQueryWrapper
	 */
	private LambdaQueryWrapper buildQueryWrapper(AuthorityLogDTO sysLog) {
		LambdaQueryWrapper<AuthorityLog> wrapper = Wrappers.<AuthorityLog>lambdaQuery()
				.eq(StrUtil.isNotBlank(sysLog.getType()), AuthorityLog::getType, sysLog.getType())
				.like(StrUtil.isNotBlank(sysLog.getRemoteAddr()), AuthorityLog::getRemoteAddr, sysLog.getRemoteAddr());

		if (ArrayUtil.isNotEmpty(sysLog.getCreateTime())) {
			wrapper.ge(AuthorityLog::getCreateTime, sysLog.getCreateTime()[0]).le(AuthorityLog::getCreateTime,
					sysLog.getCreateTime()[1]);
		}

		return wrapper;
	}

}
