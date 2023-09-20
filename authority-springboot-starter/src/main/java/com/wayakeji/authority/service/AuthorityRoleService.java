package com.wayakeji.authority.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayakeji.common.api.dto.*;
import com.wayakeji.common.api.entity.AuthorityRole;
import com.wayakeji.common.security.login.BufferParams;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
public interface AuthorityRoleService extends IService<AuthorityRole> {

    /**
     * 新增角色
     * @param params
     * @return
     */
    int insert(AuthorityRoleAdd params);

    /**
     * 删除角色
     * <p>删除角色会删除角色的子、子子、子子子、...角色，直到没有子角色为止
     * <p>创建包含传入角色ID的Integer集合
     * <p>调用{@link #(Integer, List)}来获取到所有需要删除的角色ID
     * <p>然后依次批量删除角色信息、角色用户绑定、角色权限绑定
     * @param params {@link AuthorityRoleDel}
     * @return 改变数据的条数
     */
    int delete(AuthorityRoleDel params);

    Page<Map<String, Object>> query(Page page, AuthorityRoleQuery params);

    int saveUsers(AuthorityRoleUserAdd params, BufferParams buf);

    int addUserRole(Integer rid, Long clientId, BufferParams buf);

    int saveModules(AuthorityRoleModuleAdd params, BufferParams buf);

    int saveModulesV2(AuthorityRoleModuleAdd params, BufferParams buf);
}
