package com.wayakeji.qiniuyun.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayakeji.common.security.login.BufferParams;
import com.wayakeji.qiniuyun.entity.PluginQiniuImg;
import com.wayakeji.qiniuyun.params.QiniuImgDel;
import com.wayakeji.qiniuyun.params.QiniuImgGet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 图片引用service
 * <p>图片引用作用与图片记录，图片反查询
 * <p>在所有引用了图片的表进行增、删、改数据记录时，都必须调用对应的方法来处理图片引用信息
 * <p>否则会出现数据差异问题
 * <p>所有引用表的主键名称必须为'id'，且必须有一个字段为'name'
 * <p><b>在添加引用时，会使用模块名称与表名称，强烈建议将模块名称与表名称建立枚举进行引用，方便统一管理
 *
 * @author hu trace
 */
public interface PluginQiniuImgService extends IService<PluginQiniuImg> {

    Map<String, Object> token();

    int insertOne(PluginQiniuImg pluginQiniuImg,BufferParams buf);

    Page<PluginQiniuImg> queryPage(Page page, PluginQiniuImg pluginQiniuImg);

    int removeQiniu(QiniuImgDel params);

    List<Map<String, Object>> selectQuoteByPid(Integer pid);

    int insertV2(PluginQiniuImg params, BufferParams buf);

    int updateNameV2(PluginQiniuImg params);

    int deleteV2(QiniuImgDel params);

    List<Map<String, Object>> getFolderV2(QiniuImgGet params);

    Page<PluginQiniuImg> queryV2(Page page, PluginQiniuImg pluginQiniuImg);

    Map<String,Object> uploadFile(MultipartFile file);
}
