package com.wayakeji.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.common.api.dto.*;
import com.wayakeji.common.api.entity.AuthorityRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
@Mapper
public interface AuthorityRoleMapper extends BaseMapper<AuthorityRole> {

    int delModuleByMid(Integer mid);

    int delModuleByMids(List<Integer> mids);

    /**
     * 新增角色
     * @param params
     * @return
     */
    int insertRole(AuthorityRoleAdd params);

    int deletes(AuthorityRoleDel params);

    int delUsersByRid(AuthorityRoleDel params);

    int delModulesByRid(AuthorityRoleDel params);

    List<Integer> selectByPid(Integer pid);

    Page<Map<String, Object>> queryPage(Page page, @Param("query") AuthorityRoleQuery params);

    int addUsersByRidAndUids(AuthorityRoleUserAdd params);

    int delUsersByRidAndUids(AuthorityRoleUserAdd params);

    int addModulesByRidAndUids(AuthorityRoleModuleAdd params);

    int delModulesByRidAndUids(AuthorityRoleModuleAdd params);

    int selectCheckRid(AuthorityRoleQuery params);

    void delModulesByRidAndUidsV2(AuthorityRoleModuleAdd params);
}
