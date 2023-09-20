package com.wayakeji.authority.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.authority.service.AuthorityModuleService;
import com.wayakeji.common.api.dto.*;
import com.wayakeji.common.api.entity.AuthorityApi;
import com.wayakeji.common.api.entity.AuthorityUser;
import com.wayakeji.common.api.vo.AuthorityApiVO;
import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.common.core.exception.SelectException;
import com.wayakeji.common.core.util.R;
import com.wayakeji.common.log.annotation.SysLog;
import com.wayakeji.common.security.annotation.Authority;
import com.wayakeji.common.security.annotation.MultiRequestBody;
import com.wayakeji.common.security.login.BufferParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
@Authority
@RestController
@RequiredArgsConstructor
@RequestMapping("/authority/module" )
@Tag(name = "管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class AuthorityModuleController {

    private final AuthorityModuleService authorityModuleService;

    /**
     * 添加api(接口)
     * @param params
     * @return
     */
    @PostMapping("api")
    public R<AuthorityApi> addApi(@RequestBody AuthorityApi params) {
        authorityModuleService.insertApi(params);
        return R.ok(params);
    }

    /**
     * 查询全部的api
     * @return
     */
    @GetMapping("api")
    public R<List<AuthorityApi>> selectApis(){
        return R.ok(authorityModuleService.selectApiAll());
    }

    /**
     * 修改api
     * @param params
     * @return
     */
    @PutMapping("api")
    public R updateApi(@RequestBody AuthorityApi params){
        return R.ok(authorityModuleService.updateApi(params));
    }

    /**
     * 删除api
     * @param params
     * @return
     */
    @DeleteMapping("api")
    public R deleteApi(@RequestBody AuthorityApiDel params){
        return R.ok(authorityModuleService.deleteApi(params.getId()));
    }

    /**
     * 查询全部的模块和api
     * @return
     */
    @GetMapping
    public R<List<Map<String, Object>>> selectModules() {
        return R.ok(authorityModuleService.selectModuleAll());
    }

    @PostMapping
    public R<AuthorityModuleAdd> insertModule(@RequestBody AuthorityModuleAdd params){
        int type = params.getType().intValue();
        switch (type) {
            case 1:
                authorityModuleService.insertDirectory(params);
                break;
            case 2:
                authorityModuleService.insertMenu(params);
                break;
            case 3:
                authorityModuleService.insertPage(params);
                break;
            case 4:
                authorityModuleService.insertAction(params);
                break;
            default://authority.module.error.type
                return R.failed("权限模块类型错误");
        }
        return R.ok(params);
    }

    @PutMapping
    public R<AuthorityModulePut> updateModule(@RequestBody AuthorityModulePut params){
        int type = params.getType().intValue();
        switch (type) {
            case 1:
                authorityModuleService.updateDirectory(params);
                break;
            case 2:
                authorityModuleService.updateMenu(params);
                break;
            case 3:
                authorityModuleService.updatePage(params);
                break;
            default:
                return R.failed("权限模块类型错误");
        }
        return R.ok(params);
    }

    @DeleteMapping
    public R deleteModule(@MultiRequestBody AuthorityModuleDel params) {
        return R.ok(authorityModuleService.deleteModules(params));
    }


    @RequestMapping(value = "sequence", method = RequestMethod.PATCH)
    public R sequence(@MultiRequestBody AuthorityModuleSequencePatch params) {
        return R.ok(authorityModuleService.updateModuleSequence(params));
    }

    @RequestMapping(value = "byrid", method = RequestMethod.GET)
    public R<List<Map<String, Object>>> byRid(@MultiRequestBody BufferParams buf) {
        if(buf.isAdmin()) {
            return R.ok(authorityModuleService.selectRoleModuleAll());
        }
        return R.ok(authorityModuleService.selectModulesByRid(buf.getBufRoleId()));
    }


    /**
     * 通过id(clientId)查询api列表
     * authority/module/apiId
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "apiId", method = RequestMethod.GET)
    public R<List<AuthorityApiVO>> queryApiById(@MultiRequestBody AuthorityUser user) {
        if(user.getId() == null) {
            throw new SelectException("数据错误");
        }
        return R.ok(authorityModuleService.queryApiById(user));
    }

    /**
     * 通过id(clientId)查询page和 menus列表
     * authority/module/pageId
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "pageId", method = RequestMethod.GET)
    public R<Map<String, Object>> queryPageAndMenusById(@MultiRequestBody AuthorityUser user)  {
        if(user.getId() == null) {
            throw new SelectException("数据错误");
        }
        return R.ok(authorityModuleService.queryPageAndMenusById(user));
    }

}
