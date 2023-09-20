package com.wayakeji.alivod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.alivod.dto.*;
import com.wayakeji.alivod.entity.PluginAlivod;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 视频管理
 *
 * @author waya
 * @date 2022-11-21 14:39:18
 */
@Mapper
public interface PluginAlivodMapper extends BaseMapper<PluginAlivod> {

    List<String> selectUpdateVideo();

    int updateVideoTranscodeAddr(List<Map<String, Object>> params);

    int insert(PluginAlivod pluginAlivod);

    int updateOriginalAddr(AlivodUrl params);

    IPage<PluginAlivod> queryPage(Page page, @Param("query") PluginAlivod pluginAlivod);//@Param("query")

    List<AlivodVidBean> selectVidByIds(AlivodDel params);

    int selectQuoteCountByPids(AlivodDel params);

    int alivodDelete(AlivodDel params);

    List<AlivodQuoteBean> selectQuoteByPid(Integer pid);

    String selectQuoteTableFieldOfName(AlivodQuoteBean bean);

    int updateQuote(AlivodQuote quoteParams);
}
