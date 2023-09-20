package com.wayakeji.alivod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayakeji.alivod.dto.AlivodQuote;
import com.wayakeji.alivod.dto.AlivodQuoteBean;
import com.wayakeji.alivod.entity.PluginAlivodQuote;
import com.wayakeji.alivod.mapper.PluginAlivodMapper;
import com.wayakeji.alivod.mapper.PluginAlivodQuoteMapper;
import com.wayakeji.alivod.service.PluginAlivodQuoteService;
import com.wayakeji.common.core.exception.HandyserveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 视频管理
 *
 * @author waya
 * @date 2022-11-21 14:39:18
 */
@Service
@RequiredArgsConstructor
public class PluginAlivodQuoteServiceImpl extends ServiceImpl<PluginAlivodQuoteMapper, PluginAlivodQuote> implements PluginAlivodQuoteService {

    private final PluginAlivodMapper pluginAlivodMapper;

    @Override
    public int insertAlivodQuote(Integer vid, String alias, String tableName, Integer tableId) {
        return insert(vid, alias, null, tableName, tableId);
    }

    @Override
    public int deleteV1(String tableName, Integer tableId) {
        AlivodQuoteBean params = new AlivodQuoteBean();
        params.setTable(tableName);
        params.setTid(tableId);
        return delete(params);
    }

    @Override
    public int deleteV2(String tableName, Integer tableId, Integer vid) {
        AlivodQuoteBean params = new AlivodQuoteBean();
        params.setTable(tableName);
        params.setTid(tableId);
        return delete(params, vid);
    }

    @Override
    public int deleteV3(String tableName, Integer tableId, String moduleName) {
        AlivodQuoteBean params = new AlivodQuoteBean();
        params.setTable(tableName);
        params.setTid(tableId);
        params.setName(moduleName);
        return delete(params);
    }

    @Override
    public int deleteV4(String tableName, Integer tableId, String moduleName, Integer vid) {
        AlivodQuoteBean params = new AlivodQuoteBean();
        params.setTable(tableName);
        params.setTid(tableId);
        params.setName(moduleName);
        return delete(params, vid);
    }



    /**
     * 新增视频引用
     * <p>在所有新增视频的地方应调用此方法
     * <p>视频引用作用与视频记录，视频反查询
     * <p>需要注意各个字段的最大值，它们都可以在表‘plugin_alivod_quote’中查看
     * @param vid 视频表主键ID，该字段应该从前端得到
     * @param alias 当前引用视频的模块名称
     * @param moduleName 模块名称，如果它为null，则查询引用时使用关联查询
     * @param tableName 当前引用视频的表名称
     * @param tableId 当前引用视频的表记录主键id（注意，表的主键名称必须为'id'）
     * @return 返回数据更新条数，当前必定返回数字“2”
     * <p>1. 当新增引用记录和修改引用次数时，sql执行出现异常
     * <p>2. 当修改数据条数时并没有传入视频id时，抛出异常提示数据差异
     * @Transactional 用于设置需要进行回滚的异常类数组，当方法中抛出指定异常数组中的异常时，则进行事务回滚。
     */
    @Transactional(rollbackFor = Exception.class)
    public int insert(Integer vid, String alias, String moduleName,
                      String tableName, Integer tableId){
        AlivodQuoteBean params = new AlivodQuoteBean();
        params.setPid(vid);
        params.setAlias(alias);
        params.setName(moduleName);
        params.setTable(tableName);
        params.setTid(tableId);
        int num = baseMapper.insert(params);
        AlivodQuote quoteParams = new AlivodQuote();
        quoteParams.setId(vid);
        quoteParams.setQuote(1);
        num += pluginAlivodMapper.updateQuote(quoteParams);
        // 如果不等于2，表示做了并没有做到两次更新操作，可能是参数错误了，这种情况一般不会存在
        // 只会在出现数据差异的时候出现
        if(num != 2) {
            throw new HandyserveException("视频数据出现差异，请刷新后重试");
        }
        return num;
    }


    private int delete(AlivodQuoteBean params) {
        int vid;
        try {
            vid = baseMapper.selectPidByTable(params);
        }catch (HandyserveException e) {
            throw new HandyserveException(e.getMessage());
        }
        return delete(params, vid);
    }

    /**
     * 删除引用记录
     * <p>在所有引用视频表删除数据时调用此方法
     * <p>该方法会删除引用记录，并减少引用数目
     * @param params 包含表名称和表主键id的参数类
     * @param vid 视频表id
     * @return 正常情况下返回2，如果出现数据差异，可能返回0或者1。
     * <p>1. 当修改图片引用数量出现sql错误时
     * <p>2. 当删除引用出现sql错误时
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(AlivodQuoteBean params, Integer vid) {
        int num = 0;
        if(vid != 0) {
            AlivodQuote quoteParams = new AlivodQuote();
            quoteParams.setId(vid);
            quoteParams.setQuote(-1);
            num += pluginAlivodMapper.updateQuote(quoteParams);
        }
        num += baseMapper.deleteQuote(params);
        return num;
    }
}
