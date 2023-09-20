package com.wayakeji.authority.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.authority.service.AuthorityLoginHistoryService;
import com.wayakeji.common.api.entity.AuthorityLoginHistory;
import com.wayakeji.common.core.util.R;
import com.wayakeji.common.log.annotation.SysLog;
import com.wayakeji.common.security.annotation.Authority;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
@Authority
@RestController
@RequiredArgsConstructor
@RequestMapping("/authorityloginhistory" )
@Tag(name = "管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class AuthorityLoginHistoryController {

    private final AuthorityLoginHistoryService authorityLoginHistoryService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param authorityLoginHistory 
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    public R getAuthorityLoginHistoryPage(Page page, AuthorityLoginHistory authorityLoginHistory) {
        return R.ok(authorityLoginHistoryService.page(page, Wrappers.query(authorityLoginHistory)));
    }


    /**
     * 通过id查询
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(authorityLoginHistoryService.getById(id));
    }

    /**
     * 新增
     * @param authorityLoginHistory 
     * @return R
     */
    @Operation(summary = "新增", description = "新增")
    @SysLog("新增" )
    @PostMapping
    public R save(@RequestBody AuthorityLoginHistory authorityLoginHistory) {
        return R.ok(authorityLoginHistoryService.save(authorityLoginHistory));
    }

    /**
     * 修改
     * @param authorityLoginHistory 
     * @return R
     */
    @Operation(summary = "修改", description = "修改")
    @SysLog("修改" )
    @PutMapping
    public R updateById(@RequestBody AuthorityLoginHistory authorityLoginHistory) {
        return R.ok(authorityLoginHistoryService.updateById(authorityLoginHistory));
    }

    /**
     * 通过id删除
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除", description = "通过id删除")
    @SysLog("通过id删除" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Long id) {
        return R.ok(authorityLoginHistoryService.removeById(id));
    }

}
