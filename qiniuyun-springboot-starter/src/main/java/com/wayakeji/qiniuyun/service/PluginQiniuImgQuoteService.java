package com.wayakeji.qiniuyun.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wayakeji.qiniuyun.entity.PluginQiniuImgQuote;

/**
 * @author waya code generator
 * @date 2022-11-23 18:17:37
 */
public interface PluginQiniuImgQuoteService extends IService<PluginQiniuImgQuote> {

    /**
     * 新增图片引用
     * <p>在所有新增图片的地方应调用此方法
     * <p>图片引用作用与图片记录，图片反查询
     * <p>需要注意各个字段的最大值，它们都可以在表‘plugin_qiniu_img_quote’中查看
     *
     * @param id        图片表主键ID，该字段应该从前端得到
     * @param alias     当前引用图片的模块名称
     * @param tableName 当前引用图片的表名称
     * @param tableId   当前引用图片的表记录主键id（注意，表的主键名称必须为'id'）
     * @return 返回数据更新条数，当前必定返回数字“2”
     * <p>1. 当新增引用记录和修改引用次数时，sql执行出现异常
     * <p>2. 当修改数据条数时并没有传入图片id时，抛出异常提示数据差异
     */
    int insertOne(Integer id, String alias, String tableName, Integer tableId);

    /**
     * 新增图片引用
     * <p>在所有新增图片的地方应调用此方法
     * <p>图片引用作用与图片记录，图片反查询
     * <p>需要注意各个字段的最大值，它们都可以在表‘plugin_qiniu_img_quote’中查看
     *
     * @param ids       图片表主键ID，该字段应该从前端得到
     * @param alias     当前引用图片的模块名称
     * @param tableName 当前引用图片的表名称
     * @param tableId   当前引用图片的表记录主键id（注意，表的主键名称必须为'id'）
     * @return 返回数据更新条数，当前必定返回数字“2”
     * <p>1. 当新增引用记录和修改引用次数时，sql执行出现异常
     * <p>2. 当修改数据条数时并没有传入图片id时，抛出异常提示数据差异
     */
    int inserts(int[] ids, String alias, String moduleName, String tableName, Integer tableId);

    /**
     * 批量删除引用
     * <p>根据表名和表主键删除
     * <p>适用于删除表引用图片以及富文本中引用图片
     *
     * @param tableName 表名
     * @param tableId   表主键
     * @return
     */
    int deletes(String tableName, Integer tableId);

    /**
     * 批量删除引用
     * <p>根据表名和表主键和自定义属性删除
     * <p>适用于删除富文本中引用图片
     *
     * @param tableName  表名
     * @param tableId    表主键
     * @param moduleName 模块名称
     */
    int deletes(String tableName, Integer tableId, String moduleName);

    /**
     * 删除引用记录
     * <p>在所有引用图片表删除数据时调用此方法
     * <p>该方法会删除引用记录，并减少引用数目
     *
     * @param tableName 表名称
     * @param tableId   表主键id
     * @return 正常情况下返回2，如果出现数据差异，可能返回0或者1。
     * <p>1. 当查询图片id出现sql错误时
     * <p>2. 当修改图片引用数量出现sql错误时
     * <p>3. 当删除引用出现sql错误时
     */
    int delete(String tableName, Integer tableId);

    /**
     * 删除引用记录
     * <p>在所有引用图片表删除数据时调用此方法
     * <p>该方法会删除引用记录，并减少引用数目
     *
     * @param tableName  表名称
     * @param tableId    表主键id
     * @param moduleName 模块名称
     * @return 正常情况下返回2，如果出现数据差异，可能返回0或者1。
     * <p>1. 当查询图片id出现sql错误时
     * <p>2. 当修改图片引用数量出现sql错误时
     * <p>3. 当删除引用出现sql错误时
     */
    int delete(String tableName, Integer tableId, String moduleName);

    /**
     * 删除引用记录
     * <p>在所有引用图片表删除数据时调用此方法
     * <p>该方法会删除引用记录，并减少引用数目
     *
     * @param tableName  表名称
     * @param tableId    表主键id
     * @param moduleName 模块名称
     * @param id         图片表id
     * @return 正常情况下返回2，如果出现数据差异，可能返回0或者1。
     * <p>1. 当查询图片id出现sql错误时
     * <p>2. 当修改图片引用数量出现sql错误时
     * <p>3. 当删除引用出现sql错误时
     */
    int delete(String tableName, Integer tableId, String moduleName, Integer id);

    /**
     * 删除引用记录
     * <p>在所有引用图片表删除数据时调用此方法
     * <p>该方法会删除引用记录，并减少引用数目
     *
     * @param tableName 表名称
     * @param tableId   表主键id
     * @param id        图片表id
     * @return 正常情况下返回2，如果出现数据差异，可能返回0或者1。
     * <p>1. 当修改图片引用数量出现sql错误时
     * <p>2. 当删除引用出现sql错误时
     */
    int delete(String tableName, Integer tableId, Integer id);
}
