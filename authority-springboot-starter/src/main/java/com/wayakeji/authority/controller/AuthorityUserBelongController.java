package com.wayakeji.authority.controller;

import com.wayakeji.authority.service.AuthorityUserBelongService;
import com.wayakeji.common.api.dto.AuthorityBelongDel;
import com.wayakeji.common.api.entity.AuthorityUserBelong;
import com.wayakeji.common.core.util.R;
import com.wayakeji.common.log.annotation.SysLog;
import com.wayakeji.common.security.annotation.Authority;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 *
 *@AllArgsConstructor 和@RequiredArgsConstructor都可以用来替换@Autowired写法，
 * 区别在@RequiredArgsConstructor必须要有final修饰。
 *
 * @author LY
 * @date 2023-06-15 11:30:15
 */
@Authority
@RestController
@RequiredArgsConstructor
@RequestMapping("/authority/belong" )
@Tag(name = "管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class AuthorityUserBelongController {

    private final AuthorityUserBelongService authorityUserBelongService;

    /**
     * 查询全部
     * @return
     */
    @Operation(summary = "查询全部", description = "查询全部")
    @GetMapping
    public R<List<Map<String, Object>>> getAuthorityUserBelongAll() {
        return R.ok(authorityUserBelongService.listMaps());
    }


    /**
     * 新增
     * @param authorityUserBelong 
     * @return R
     */
    @Operation(summary = "新增", description = "新增")
    @SysLog("新增" )
    @PostMapping
    public R save(@RequestBody AuthorityUserBelong authorityUserBelong) {
        return R.ok(authorityUserBelongService.save(authorityUserBelong));
    }


    /**
     * 通过id删除
     * @param params
     * @return R
     */
    @Operation(summary = "通过id删除", description = "通过id删除")
    @SysLog("通过id删除" )
    @DeleteMapping
    public R removeById(@RequestBody AuthorityBelongDel params) {
        return R.ok(authorityUserBelongService.removeById(params.getBelong()));
    }

}
