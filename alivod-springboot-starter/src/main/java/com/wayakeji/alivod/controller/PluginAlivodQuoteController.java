package com.wayakeji.alivod.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.common.core.util.R;
import com.wayakeji.common.log.annotation.SysLog;
import com.wayakeji.alivod.entity.PluginAlivodQuote;
import com.wayakeji.alivod.service.PluginAlivodQuoteService;
import com.wayakeji.common.security.annotation.Authority;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 视频管理
 *
 * @author waya
 * @date 2022-11-21 14:39:18
 */
@Authority
@RestController
@RequiredArgsConstructor
@RequestMapping("/pluginalivodquote" )
@Tag(name = "视频管理管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class PluginAlivodQuoteController {

    private final  PluginAlivodQuoteService pluginAlivodQuoteService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param pluginAlivodQuote 视频管理
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    public R getPluginAlivodQuotePage(Page page, PluginAlivodQuote pluginAlivodQuote) {
        return R.ok(pluginAlivodQuoteService.page(page, Wrappers.query(pluginAlivodQuote)));
    }


    /**
     * 通过id查询视频管理
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(pluginAlivodQuoteService.getById(id));
    }

    /**
     * 新增视频管理
     * @param pluginAlivodQuote 视频管理
     * @return R
     */
    @Operation(summary = "新增视频管理", description = "新增视频管理")
    @SysLog("新增视频管理" )
    @PostMapping
    public R save(@RequestBody PluginAlivodQuote pluginAlivodQuote) {
        return R.ok(pluginAlivodQuoteService.save(pluginAlivodQuote));
    }

    /**
     * 修改视频管理
     * @param pluginAlivodQuote 视频管理
     * @return R
     */
    @Operation(summary = "修改视频管理", description = "修改视频管理")
    @SysLog("修改视频管理" )
    @PutMapping
    public R updateById(@RequestBody PluginAlivodQuote pluginAlivodQuote) {
        return R.ok(pluginAlivodQuoteService.updateById(pluginAlivodQuote));
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
        return R.ok(pluginAlivodQuoteService.removeById(id));
    }

}
