package com.wayakeji.authority.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.authority.service.AuthorityModuleService;
import com.wayakeji.authority.service.AuthorityRoleService;
import com.wayakeji.authority.service.AuthorityUserService;
import com.wayakeji.common.api.dto.*;
import com.wayakeji.common.api.entity.AuthorityRole;
import com.wayakeji.common.api.entity.AuthorityUser;
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
 *权限模块->角色模块的接口类
 * @author LY
 * @date 2023-06-15 11:30:16
 */
@Authority
@RestController
@RequiredArgsConstructor
@RequestMapping("/authority/role" )
@Tag(name = "管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class AuthorityRoleController {

    private final AuthorityRoleService authorityRoleService;

    private final AuthorityUserService authorityUserService;

    private final AuthorityModuleService authorityModuleService;


    /**
     * 新增角色
     * <p>接口地址: authority/role
     * <p>请求方式: POST
     * @param params {@link AuthorityRoleAdd}
     * @param buf {@link BufferParams}
     * @return 改变数据条数
     */
    @RequestMapping(method = RequestMethod.POST)
    @Validated
    public R insert(@MultiRequestBody AuthorityRoleAdd params, BufferParams buf) {
        params.setPid(buf.getBufRoleId());
        return R.ok(authorityRoleService.insert(params));
    }

    /**
     * 删除角色
     * <p>删除角色会删除角色的子、子子、子子子、...角色，直到没有子角色为止
     * <p>该接口是一个非常危险的操作接口，删除后是不可逆的
     * <p>接口地址: authority/role
     * <p>请求方式: DELETE
     * @param params {@link AuthorityRoleDel}
     * @return 改变数据条数
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Validated
    public R delete(@RequestBody AuthorityRoleDel params) {
        return R.ok(authorityRoleService.delete(params));
    }


    /**
     * 分页查询角色列表
     * <p>接口会根据当前请求的用户判断，如果是超级管理员，则是查询所有角色列表
     * <p>否则，查询当前请求用户角色的下一级（不会查询多级）角色列表
     * <p>接口地址: authority/role
     * <p>请求方式: GET
     * @param params {@link }
     * @param buf {@link BufferParams}
     * @return 标准的queryPage响应，map中包含count（数据条数）和list（数据集合）
     */
    @RequestMapping(method = RequestMethod.GET)
    public R query(Page page, @MultiRequestBody AuthorityRoleQuery params, BufferParams buf) {
        if(!buf.isAdmin()) {
            params.setPid(buf.getBufRoleId());
        }
        return R.ok(authorityRoleService.query(page,params));
    }

    /**
     * 修改角色
     * <p>接口地址: authority/role
     * <p>请求方式: PUT
     * @param params {@link AuthorityRole}
     * @return 改变数据的条数
     */
    @RequestMapping(method = RequestMethod.PUT)
    public R updateById(@RequestBody AuthorityRole params){
        return R.ok(authorityRoleService.updateById(params));
    }


    /**
     * 查询角色下绑定的用户
     * <p>系统根据当前请求的用户角色ID查询该角色下已经绑定的用户
     * <p>接口地址: authority/role/user
     * <p>请求方式: GET
     * @param params {@link AuthorityRoleRid}
     * @return 用户信息列表
     */
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public R<List<Map<String, Object>>> selectUsersByRid(@MultiRequestBody AuthorityRoleRid params) {
        return R.ok(authorityUserService.selectUsersByRid(params.getRid()));
    }

    /**
     * 保存角色下的用户
     * <p>当前操作会验证是否是超管
     * <p>如果不是超管会验证传入的rid是否正确，以及添加的用户列表是否是当前用户可以操作的用户
     * <p>接口地址: authority/role/user
     * <p>请求方式: POST
     * @param params {@link AuthorityRoleUserAdd}
     * @param buf {@link BufferParams}
     * @return 改变数据的条数
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public R saveUsers(@MultiRequestBody AuthorityRoleUserAdd params, BufferParams buf) {
        return R.ok(authorityRoleService.saveUsers(params, buf));
    }

    /**
     * 查询当前角色下绑定的权限
     * <p>系统根据当前请求的用户角色ID查询该角色下已经绑定的权限
     * <p>接口地址: authority/role/module
     * <p>请求方式: GET
     * @param params {@link AuthorityRoleRid}
     * @return 模块信息列表
     * @see {@link AuthorityModuleService#selectModulesByRid(Integer)}
     */
    @RequestMapping(value = "module", method = RequestMethod.GET)
    public R<List<Map<String, Object>>> selectModulesByRid(@MultiRequestBody AuthorityRoleRid params) {
        return R.ok(authorityModuleService.selectModulesByRid(params.getRid()));
    }


    /**
     * 保存角色下的权限
     * <p>当前操作会验证是否是超管
     * <p>如果不是超管会验证传入的rid是否正确，以及添加的权限列表是否是当前用户可以操作的权限
     * <p>接口地址: authority/role/module
     * <p>请求方式: POST
     * @param params {@link AuthorityRoleModuleAdd}
     * @param buf {@link BufferParams}
     * @return 改变数据的条数
     * @see {@link AuthorityRoleService#saveModules(AuthorityRoleModuleAdd, BufferParams)}
     */
    @RequestMapping(value = "module", method = RequestMethod.POST)
    public R saveModules(@MultiRequestBody AuthorityRoleModuleAdd params, BufferParams buf) {
        return R.ok(authorityRoleService.saveModules(params, buf));
    }


    /**
     * 保存角色下的权限v2
     * <p>当前操作会验证是否是超管
     * <p>如果不是超管会验证传入的rid是否正确，以及添加的权限列表是否是当前用户可以操作的权限
     * <p>接口地址: authority/role/moduleV2
     * <p>请求方式: POST
     * @param params {@link AuthorityRoleModuleAdd}
     * @param buf {@link BufferParams}
     * @return 改变数据的条数
     * @see {@link AuthorityRoleService#saveModules(AuthorityRoleModuleAdd, BufferParams)}
     */
    @RequestMapping(value = "moduleV2", method = RequestMethod.POST)
    @Validated
    public R saveModulesV2(@MultiRequestBody AuthorityRoleModuleAdd params, BufferParams buf) {
        return  R.ok(authorityRoleService.saveModulesV2(params, buf));
    }



}
