package com.wayakeji.alivod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayakeji.alivod.dto.AlivodDel;
import com.wayakeji.alivod.dto.AlivodUrl;
import com.wayakeji.alivod.dto.UploadVideoVO;
import com.wayakeji.alivod.entity.PluginAlivod;
import com.wayakeji.common.security.login.BufferParams;

import java.util.List;
import java.util.Map;

/**
 * 视频管理
 *
 * @author waya
 * @date 2022-11-21 14:39:18
 */
public interface PluginAlivodService extends IService<PluginAlivod> {


    UploadVideoVO insert(PluginAlivod pluginAlivod, BufferParams buf) throws Exception;

    UploadVideoVO refresh(String videoId);

    int updateOriginalAddr(AlivodUrl params);

    IPage<PluginAlivod> queryPage(Page page, PluginAlivod pluginAlivod);

    int delete(AlivodDel params);

    List<Map<String, Object>> selectQuoteByPid(Integer pid);
}
