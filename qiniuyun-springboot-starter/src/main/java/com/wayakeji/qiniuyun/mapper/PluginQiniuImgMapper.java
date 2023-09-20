package com.wayakeji.qiniuyun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.qiniuyun.bean.ImageKeyBean;
import com.wayakeji.qiniuyun.bean.ImageQuoteBean;
import com.wayakeji.qiniuyun.entity.PluginQiniuImg;
import com.wayakeji.qiniuyun.params.QiniuImgDel;
import com.wayakeji.qiniuyun.params.QiniuImgGet;
import com.wayakeji.qiniuyun.params.QiniuImgQuote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 图片库
 *
 * @author waya
 * @date 2022-11-21 11:48:58
 */
@Mapper
public interface PluginQiniuImgMapper extends BaseMapper<PluginQiniuImg> {

    int insertOne(PluginQiniuImg pluginQiniuImg);

    Page<PluginQiniuImg> queryPage(Page page, @Param("query") PluginQiniuImg pluginQiniuImg);

    List<ImageKeyBean> selectKeyByIds(QiniuImgDel params);

    int selectSumQuoteByIds(QiniuImgDel params);

    int removeQiniu(QiniuImgDel params);

    List<ImageQuoteBean> selectQuoteByPid(Integer pid);

    String selectQuoteTableFieldOfName(ImageQuoteBean bean);

    int insertV2(PluginQiniuImg params);

    int updateNameV2(PluginQiniuImg params);

    int deleteV2(QiniuImgDel params);

    List<Map<String, Object>> getFolderV2(QiniuImgGet params);

    int updateQuote(QiniuImgQuote params);

    int updateQuotes(List<QiniuImgQuote> params);

    Page<PluginQiniuImg> queryV2(Page page,@Param("query") PluginQiniuImg pluginQiniuImg);
}
