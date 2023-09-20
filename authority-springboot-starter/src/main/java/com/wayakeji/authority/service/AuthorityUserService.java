package com.wayakeji.authority.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayakeji.common.api.dto.AuthorityUserEnabled;
import com.wayakeji.common.api.dto.AuthorityUserGet;
import com.wayakeji.common.api.entity.AuthorityUser;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
public interface AuthorityUserService extends IService<AuthorityUser> {

    List<Map<String, Object>> selectUsersByRid(Integer rid);

    Page<Map<String, Object>> queryPage(Page page, AuthorityUserGet params);

    int updateEnabled(AuthorityUserEnabled params);

    List<Map<String, Object>> selectNotRoleByRid(Map<String, Object> params);
}
