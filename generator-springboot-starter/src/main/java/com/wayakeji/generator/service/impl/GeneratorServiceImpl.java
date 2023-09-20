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

package com.wayakeji.generator.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.generator.entity.GenConfig;
import com.wayakeji.generator.mapper.GeneratorMapper;
import com.wayakeji.generator.service.GenCodeService;
import com.wayakeji.generator.service.GeneratorService;
import com.wayakeji.generator.support.StyleTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author lengleng
 * @date 2018-07-30
 * <p>
 * 代码生成器
 */
@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {

	private final GeneratorMapper generatorMapper;

	private final Map<String, GenCodeService> genCodeServiceMap;

	/**
	 * 分页查询表
	 * @param tableName 查询条件
	 * @param dsName
	 * @return
	 */
	@Override
	public IPage<List<Map<String, Object>>> getPage(Page page, String tableName, String dsName) {
		page.setOptimizeCountSql(false);
		return generatorMapper.queryList(page, tableName);
	}

	/**
	 * 预览代码
	 * @param genConfig 查询条件
	 * @return
	 */
	@Override
	public Map<String, String> previewCode(GenConfig genConfig) {

		String tableNames = genConfig.getTableName();
		String dsName = genConfig.getDsName();

		// 获取实现
		GenCodeService genCodeService = genCodeServiceMap.get(StyleTypeEnum.getDecs(genConfig.getStyle()));

		for (String tableName : StrUtil.split(tableNames, StrUtil.DASHED)) {
			// 查询表信息
			Map<String, String> table = generatorMapper.queryTable(tableName, dsName);
			// 查询列信息
			List<Map<String, String>> columns = generatorMapper.queryColumns(tableName, dsName);
			// 生成代码
			return genCodeService.gen(genConfig, table, columns, null, null);
		}

		return MapUtil.empty();
	}

	/**
	 * 生成代码
	 * @param genConfig 生成配置
	 * @return
	 */
	@Override
	public byte[] generatorCode(GenConfig genConfig) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		String tableNames = genConfig.getTableName();
		String dsName = genConfig.getDsName();

		GenCodeService genCodeService = genCodeServiceMap.get(StyleTypeEnum.getDecs(genConfig.getStyle()));

		for (String tableName : StrUtil.split(tableNames, StrUtil.DASHED)) {
			// 查询表信息
			Map<String, String> table = generatorMapper.queryTable(tableName, dsName);
			// 查询列信息
			List<Map<String, String>> columns = generatorMapper.queryColumns(tableName, dsName);
			// 生成代码
			genCodeService.gen(genConfig, table, columns, zip, null);
		}
		IoUtil.close(zip);
		return outputStream.toByteArray();
	}

}
