package com.wayakeji.authority.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayakeji.authority.mapper.AuthorityUserMapper;
import com.wayakeji.authority.service.AuthorityUserService;
import com.wayakeji.common.api.dto.AuthorityUserEnabled;
import com.wayakeji.common.api.dto.AuthorityUserGet;
import com.wayakeji.common.api.entity.AuthorityUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
@Service
public class AuthorityUserServiceImpl extends ServiceImpl<AuthorityUserMapper, AuthorityUser> implements AuthorityUserService {

    @Override
    public List<Map<String, Object>> selectUsersByRid(Integer rid) {
        return baseMapper.selectUsersByRid(rid);
    }

    @Override
    public Page<Map<String, Object>> queryPage(Page page, AuthorityUserGet params) {
        return baseMapper.queryPage(page,params);
    }

    @Override
    public int updateEnabled(AuthorityUserEnabled params) {
        return baseMapper.updateEnabled(params);
    }

    @Override
    public List<Map<String, Object>> selectNotRoleByRid(Map<String, Object> params) {
        return baseMapper.selectNotRoleByRid(params);
    }
}
