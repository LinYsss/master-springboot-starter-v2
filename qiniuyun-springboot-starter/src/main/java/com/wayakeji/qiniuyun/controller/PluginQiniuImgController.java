package com.wayakeji.qiniuyun.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.common.core.util.R;
import com.wayakeji.common.log.annotation.SysLog;
import com.wayakeji.common.security.annotation.Authority;
import com.wayakeji.common.security.annotation.MultiRequestBody;
import com.wayakeji.common.security.login.BufferParams;
import com.wayakeji.qiniuyun.entity.PluginQiniuImg;
import com.wayakeji.qiniuyun.params.QiniuImgAdd;
import com.wayakeji.qiniuyun.params.QiniuImgDel;
import com.wayakeji.qiniuyun.params.QiniuImgGet;
import com.wayakeji.qiniuyun.service.PluginQiniuImgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * 图片库
 *
 * @author waya
 * @date 2022-11-21 11:48:58
 */
@Authority
@RestController
@RequiredArgsConstructor
@RequestMapping("/plugin/qiniuimg" )
@Tag(name = "图片库管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class PluginQiniuImgController {

    private final PluginQiniuImgService pluginQiniuImgService;


    /**
     * 获取token
     * @return
     */
    @Operation(summary = "获取token", description = "获取token")
    @GetMapping(value = "token")
    public R<Map<String, Object>> token() {
        return R.ok(pluginQiniuImgService.token());
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param pluginQiniuImg 图片库
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public R<Page<PluginQiniuImg>> getPluginQiniuImgPage(Page page, PluginQiniuImg pluginQiniuImg) {
        return R.ok(pluginQiniuImgService.queryPage(page, pluginQiniuImg));
    }

    /**
     * 新增图片库
     * @param pluginQiniuImg 图片库
     * @return R
     */
    @Operation(summary = "新增图片库", description = "新增图片库")
    @SysLog("新增图片库" )
    @PostMapping
    public R save(@MultiRequestBody PluginQiniuImg pluginQiniuImg,BufferParams buf) {
        return R.ok(pluginQiniuImgService.insertOne(pluginQiniuImg,buf));
    }

    /**
     * 修改图片库
     * @param pluginQiniuImg 图片库
     * @return R
     */
    @Operation(summary = "修改图片库", description = "修改图片库")
    @SysLog("修改图片库" )
    @PutMapping
    public R updateById(@RequestBody PluginQiniuImg pluginQiniuImg) {
        return R.ok(pluginQiniuImgService.updateById(pluginQiniuImg));
    }

    /**
     * 通过id删除图片库,批量删除
     * @param params
     * @return R
     */
    @Operation(summary = "通过id删除图片库", description = "通过id删除图片库")
    @SysLog("通过id删除图片库" )
    @DeleteMapping()
    public R removeQiniu(@RequestBody QiniuImgDel params) {
        return R.ok(pluginQiniuImgService.removeQiniu(params));
    }

    /**
     * 查询图片引用
     * @param pid
     * @return
     */
    @Operation(summary = "查询图片引用", description = "查询图片引用")
    @RequestMapping(value = "quote/{pid}", method = RequestMethod.GET)
    public R<List<Map<String, Object>>> selectQuoteByPid(@PathVariable("pid") Integer pid) {
        return R.ok(pluginQiniuImgService.selectQuoteByPid(pid));
    }

    /**
     * 分页查询
     * plugin/qiniuimg/v2
     * @param page 分页对象
     * @param pluginQiniuImg 图片库
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @RequestMapping(value = "v2", method = RequestMethod.GET)
    public R<Page<PluginQiniuImg>> queryV2(Page page, PluginQiniuImg pluginQiniuImg) {
        return R.ok(pluginQiniuImgService.queryV2(page, pluginQiniuImg));
    }


    /**
     * 添加
     *  plugin/qiniuimg/v2
     * @param params
     * @return
     */
    @RequestMapping(value = "v2",method = RequestMethod.POST)
    public R<Integer> insertV2(@MultiRequestBody PluginQiniuImg params, BufferParams buf) {
        return R.ok(pluginQiniuImgService.insertV2(params,buf));
    }

    /**
     * 修改图片名称
     * plugin/qiniuimg/v2
     * @param params
     * @return
     */
    @RequestMapping(value = "v2",method = RequestMethod.PUT)
    public R<Integer> updateNameV2(@RequestBody PluginQiniuImg params){
        return R.ok(pluginQiniuImgService.updateNameV2(params));
    }

    /**
     * 删除文件夹及文件夹里面的图片
     * plugin/qiniuimg/v2
     * @param params
     * @return
     */
    @RequestMapping(value = "v2",method = RequestMethod.DELETE)
    public R<Integer> deleteV2(@RequestBody QiniuImgDel params) {
        return R.ok(pluginQiniuImgService.deleteV2(params));
    }

    /**
     * 查询全部的文件夹
     * plugin/qiniuimg/v2/folder
     * @param params
     * @return
     */
    @RequestMapping(value = "v2/folder", method = RequestMethod.GET)
    public R<List<Map<String, Object>>> getFolderV2(QiniuImgGet params)  {
        return R.ok(pluginQiniuImgService.getFolderV2(params));
    }

    /**
     * plugin/qiniuimg/upload
     * 上传文件 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
     * @param file 资源
     * @return R(/ admin / bucketName / filename)
     */
    @PostMapping(value = "/upload")
    public R upload(@RequestPart("file") MultipartFile file) {
        return R.ok(pluginQiniuImgService.uploadFile(file));
    }


}
