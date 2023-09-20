package com.wayakeji.alivod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wayakeji.alivod.dto.AlivodQuoteBean;
import com.wayakeji.alivod.entity.PluginAlivodQuote;
import org.apache.ibatis.annotations.Mapper;

/**
 * 视频管理
 *
 * @author waya
 * @date 2022-11-21 14:39:18
 */
@Mapper
public interface PluginAlivodQuoteMapper extends BaseMapper<PluginAlivodQuote> {

    int insert(AlivodQuoteBean params);

    int selectPidByTable(AlivodQuoteBean params);

    int deleteQuote(AlivodQuoteBean params);
}
