package com.wayakeji.qiniuyun.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.common.core.util.R;
import com.wayakeji.common.log.annotation.SysLog;
import com.wayakeji.common.security.annotation.Authority;
import com.wayakeji.qiniuyun.entity.PluginQiniuImgQuote;
import com.wayakeji.qiniuyun.service.PluginQiniuImgQuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author waya code generator
 * @date 2022-11-23 18:17:37
 */
@Authority
@RestController
@RequiredArgsConstructor
@RequestMapping("/plugin/qiniuimg/quote" )
@Tag(name = "图片库管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class PluginQiniuImgQuoteController {

    private final PluginQiniuImgQuoteService pluginQiniuImgQuoteService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param pluginQiniuImgQuote 
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    public R getPluginQiniuImgQuotePage(Page page, PluginQiniuImgQuote pluginQiniuImgQuote) {
        return R.ok(pluginQiniuImgQuoteService.page(page, Wrappers.query(pluginQiniuImgQuote)));
    }


    /**
     * 通过id查询
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(pluginQiniuImgQuoteService.getById(id));
    }

    /**
     * 新增
     * @param pluginQiniuImgQuote 
     * @return R
     */
    @Operation(summary = "新增", description = "新增")
    @SysLog("新增" )
    @PostMapping
    public R save(@RequestBody PluginQiniuImgQuote pluginQiniuImgQuote) {
        return R.ok(pluginQiniuImgQuoteService.save(pluginQiniuImgQuote));
    }

    /**
     * 修改
     * @param pluginQiniuImgQuote 
     * @return R
     */
    @Operation(summary = "修改", description = "修改")
    @SysLog("修改" )
    @PutMapping
    public R updateById(@RequestBody PluginQiniuImgQuote pluginQiniuImgQuote) {
        return R.ok(pluginQiniuImgQuoteService.updateById(pluginQiniuImgQuote));
    }

    /**
     * 通过id删除
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除", description = "通过id删除")
    @SysLog("通过id删除" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(pluginQiniuImgQuoteService.removeById(id));
    }

}
