package com.wayakeji.authority.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.authority.service.AuthorityUserService;
import com.wayakeji.authority.service.impl.AuthorityLogin;
import com.wayakeji.common.api.dto.*;
import com.wayakeji.common.api.entity.AuthorityLog;
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

import java.util.HashMap;
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
@RequestMapping("/authority/user" )
@Tag(name = "管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class AuthorityUserController {

    private final AuthorityUserService authorityUserService;

    private final AuthorityLogin authorityLogin;

    /**
     * 分页查询
     * @param page 分页对象
     * @param params
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @RequestMapping(method = RequestMethod.GET)
    public R query(Page page, @MultiRequestBody AuthorityUserGet params, BufferParams buf) {
        params.setAddUid(buf.getBufUid());
        if(params.getAddUid() == null || params.getAddUid() == 0) {
            params.setAddUid(buf.getClientId());
        }
        params.setAddRid(buf.getBufRoleId());
        return R.ok(authorityUserService.queryPage(page,params));
    }


    @RequestMapping(method = RequestMethod.POST)
    public int insert(@MultiRequestBody AuthorityUserAdd params, BufferParams buf) {
        AuthorityUser user = new AuthorityUser();
        user.setPhone(params.getPhone());
        user.setEmail(params.getEmail());
        user.setBelong(params.getBelong());
        user.setUsername(params.getUsername());
        user.setPassword(params.getPassword());
        user.setNickname(params.getNickname());
        user.setOrganization(params.getOrganization());
        return authorityLogin.create(user, buf);
    }

    @RequestMapping(value = "enabled", method = RequestMethod.PATCH)
    public int enabled(@MultiRequestBody AuthorityUserEnabled params) {
        return authorityUserService.updateEnabled(params);
    }

    @RequestMapping(value = "password", method = RequestMethod.PATCH)
    public int password(@MultiRequestBody AuthorityUserPassword params){
        return authorityLogin.updatePassword(params.getId(), params.getPassword(),null);
    }

    @RequestMapping(value = "pwd", method = RequestMethod.PUT)
    public int updatePwd(@RequestBody CommonPwdPut params) {
        return authorityLogin.updatePassword(params.getId(), params.getPassword(), params.getOldPassword());
    }

    /**
     * 修改用户信息
     * @param params
     */
    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@MultiRequestBody AuthorityUser params) {
        authorityUserService.updateById(params);
    }

    @RequestMapping(value = "byrid", method = RequestMethod.GET)
    public R<List<Map<String, Object>>> selectNotRoleAll(@MultiRequestBody BufferParams buf) {
        Map<String, Object> params = new HashMap<String, Object>();
        if(!buf.isAdmin()) {
            params.put("uid", buf.getBufUid());
            if(buf.getBufUid() == null || buf.getBufUid() == 0) {
                params.put("uid", buf.getClientId());
            }
            params.put("rid", buf.getBufRoleId());
        }
        return R.ok(authorityUserService.selectNotRoleByRid(params));
    }

}
