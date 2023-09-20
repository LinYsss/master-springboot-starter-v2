package com.wayakeji.alivod.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.alivod.dto.AlivodDel;
import com.wayakeji.alivod.dto.AlivodRefresh;
import com.wayakeji.alivod.dto.AlivodUrl;
import com.wayakeji.alivod.dto.UploadVideoVO;
import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.common.core.util.R;
import com.wayakeji.common.log.annotation.SysLog;
import com.wayakeji.alivod.entity.PluginAlivod;
import com.wayakeji.alivod.service.PluginAlivodService;
import com.wayakeji.common.security.annotation.Authority;
import com.wayakeji.common.security.annotation.MultiRequestBody;
import com.wayakeji.common.security.login.BufferParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 视频管理
 *
 * @author waya
 * @date 2022-11-21 14:39:18
 */
@Authority
@RestController
@RequiredArgsConstructor
@RequestMapping("/plugin/alivod" )
@Tag(name = "视频管理管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class PluginAlivodController {

    private final  PluginAlivodService pluginAlivodService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param pluginAlivod 视频管理
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    public R<IPage<PluginAlivod>> getPluginAlivodPage(Page page, PluginAlivod pluginAlivod) {
        return R.ok(pluginAlivodService.queryPage(page, pluginAlivod));
    }


    /**
     * 通过id查询视频管理
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id") Integer id) {
        return R.ok(pluginAlivodService.getById(id));
    }

    /**
     * 新增视频管理
     * @param pluginAlivod 视频管理
     * @return R
     */
    @Operation(summary = "新增视频管理", description = "新增视频管理")
    @SysLog("新增视频管理" )
    @PostMapping
    public R<UploadVideoVO> save(@MultiRequestBody PluginAlivod pluginAlivod, BufferParams buf) throws Exception {
        return R.ok(pluginAlivodService.insert(pluginAlivod,buf));
    }

    /**
     * 修改视频管理
     * @param pluginAlivod 视频管理
     * @return R
     */
    @Operation(summary = "修改视频管理", description = "修改视频管理")
    @SysLog("修改视频管理" )
    @PutMapping("update")
    public R updateById(@RequestBody PluginAlivod pluginAlivod) {
        return R.ok(pluginAlivodService.updateById(pluginAlivod));
    }

    /**
     * 刷新上传Token
     * @param params
     * @return
     * @throws Exception
     */
    @SysLog("刷新上传Token" )
    @RequestMapping(method = RequestMethod.PUT)
    public R<UploadVideoVO> refresh(@RequestBody AlivodRefresh params) throws Exception {
        return R.ok(pluginAlivodService.refresh(params.getVideoId()));
    }

    /**
     * 修改视频转码地址
     * @param params
     * @return
     * @throws HandyserveException
     */
    @RequestMapping(value = "url", method = RequestMethod.PATCH)
    public R updateVideoAddr(@RequestBody AlivodUrl params) throws HandyserveException{
        if(params.getState() == 3 && params.getUrl() == null) {
            throw new HandyserveException("参数错误");
        }
        return R.ok(pluginAlivodService.updateOriginalAddr(params));
    }

    /**
     * 通过id删除视频管理
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除视频管理", description = "通过id删除视频管理")
    @SysLog("通过id删除视频管理" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(pluginAlivodService.removeById(id));
    }

    /**
     * 批量删除
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public R delete(@RequestBody AlivodDel params) throws Exception {
        return R.ok(pluginAlivodService.delete(params));
    }


    /**
     *查询视频引用详情
     * @param pid
     * @return
     */
    @RequestMapping(value = "quote", method = RequestMethod.GET)
    public R<List<Map<String, Object>>> selectQuoteByPid(Integer pid) {
        return R.ok(pluginAlivodService.selectQuoteByPid(pid));
    }

}
