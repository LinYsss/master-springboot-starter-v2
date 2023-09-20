package com.wayakeji.qiniuyun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wayakeji.qiniuyun.bean.ImageQuoteBean;
import com.wayakeji.qiniuyun.entity.PluginQiniuImgQuote;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 *
 * @author waya code generator
 * @date 2022-11-23 18:17:37
 */
@Mapper
public interface PluginQiniuImgQuoteMapper extends BaseMapper<PluginQiniuImgQuote> {

    int insert(ImageQuoteBean params);

    int inserts(List<ImageQuoteBean> params);

    int selectPidByTable(ImageQuoteBean params);

    List<Integer> selectPidsByTable(ImageQuoteBean params);

    int delete(ImageQuoteBean params);
}
