package com.wayakeji.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayakeji.common.api.dto.AuthorityUserEnabled;
import com.wayakeji.common.api.dto.AuthorityUserGet;
import com.wayakeji.common.api.entity.AuthorityUser;
import com.wayakeji.common.api.vo.AuthorityApiVO;
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
public interface AuthorityUserMapper extends BaseMapper<AuthorityUser> {

    int selectLoginErrorCount(Map<String, Object> params);

    void updateUserLoginInfo(Map<String, Object> params);

    List<AuthorityApiVO> selectApisByUid(Long id);

    Map<String, Object> selectRoleInfoByClientId(Long id);

    List<Map<String, Object>> selectUsersByRid(Integer rid);

    List<Long> selectNotRoleUidsByRid(Integer rid);

    Page<Map<String, Object>> queryPage(Page page,  @Param("query") AuthorityUserGet params);

    int updateEnabled(AuthorityUserEnabled params);

    List<Map<String, Object>> selectNotRoleByRid(Map<String, Object> params);
}
