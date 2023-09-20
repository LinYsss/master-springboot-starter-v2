package com.wayakeji.alivod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wayakeji.alivod.dto.UploadVideoVO;
import com.wayakeji.alivod.entity.PluginAlivod;
import com.wayakeji.alivod.entity.PluginAlivodQuote;

/**
 * 视频管理
 *
 * @author waya
 * @date 2022-11-21 14:39:18
 */
public interface PluginAlivodQuoteService extends IService<PluginAlivodQuote> {

    /**
     * 新增视频引用
     * <p>在所有新增视频的地方应调用此方法
     * <p>视频引用作用与视频记录，视频反查询
     * <p>需要注意各个字段的最大值，它们都可以在表‘plugin_alivod_quote’中查看
     * @param vid 视频表主键ID，该字段应该从前端得到
     * @param alias 当前引用视频的模块名称
     * @param tableName 当前引用视频的表名称
     * @param tableId 当前引用视频的表记录主键id（注意，表的主键名称必须为'id'）
     * @return 返回数据更新条数，当前必定返回数字“2”
     * <p>1. 当新增引用记录和修改引用次数时，sql执行出现异常
     * <p>2. 当修改数据条数时并没有传入视频id时，抛出异常提示数据差异
     */
    int insertAlivodQuote(Integer vid, String alias, String tableName, Integer tableId);


    /**
     * 删除引用记录
     * <p>在所有引用视频表删除数据时调用此方法
     * <p>该方法会删除引用记录，并减少引用数目
     * @param tableName 表名称
     * @param tableId 表主键id
     * @return 正常情况下返回2，如果出现数据差异，可能返回0或者1。
     * <p>1. 当查询视频id出现sql错误时
     * <p>2. 当修改视频引用数量出现sql错误时
     * <p>3. 当删除引用出现sql错误时
     */
     int deleteV1(String tableName, Integer tableId);

    /**
     * 删除引用记录
     * <p>在所有引用视频表删除数据时调用此方法
     * <p>该方法会删除引用记录，并减少引用数目
     * @param tableName 表名称
     * @param tableId 表主键id
     * @param vid 视频表id
     * @return 正常情况下返回2，如果出现数据差异，可能返回0或者1。
     * <p>1. 当修改图片引用数量出现sql错误时
     * <p>2. 当删除引用出现sql错误时
     */
    int deleteV2(String tableName, Integer tableId, Integer vid);

    /**
     * 删除引用记录
     * <p>在所有引用视频表删除数据时调用此方法
     * <p>该方法会删除引用记录，并减少引用数目
     * @param tableName 表名称
     * @param tableId 表主键id
     * @param moduleName 模块名称
     * @return 正常情况下返回2，如果出现数据差异，可能返回0或者1。
     * <p>1. 当查询视频id出现sql错误时
     * <p>2. 当修改视频引用数量出现sql错误时
     * <p>3. 当删除引用出现sql错误时
     */
    int deleteV3(String tableName, Integer tableId, String moduleName);

    /**
     * 删除引用记录
     * <p>在所有引用视频表删除数据时调用此方法
     * <p>该方法会删除引用记录，并减少引用数目
     * @param tableName 表名称
     * @param tableId 表主键id
     * @param moduleName 模块名称
     * @param vid 视频表id
     * @return 正常情况下返回2，如果出现数据差异，可能返回0或者1。
     * <p>1. 当查询视频id出现sql错误时
     * <p>2. 当修改视频引用数量出现sql错误时
     * <p>3. 当删除引用出现sql错误时
     */
    int deleteV4(String tableName, Integer tableId, String moduleName, Integer vid);
}
