package com.wayakeji.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wayakeji.common.api.dto.AuthorityModuleAdd;
import com.wayakeji.common.api.dto.AuthorityModuleDel;
import com.wayakeji.common.api.dto.AuthorityModulePut;
import com.wayakeji.common.api.dto.AuthorityModuleSequencePatch;
import com.wayakeji.common.api.entity.AuthorityModule;
import com.wayakeji.common.api.vo.AuthorityApiCacheVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
@Mapper
public interface AuthorityModuleMapper extends BaseMapper<AuthorityModule> {

    List<AuthorityApiCacheVO> selectCacheApis();

    List<Map<String, Object>> selectModuleAll();

    int insertModule(AuthorityModuleAdd params);

    int updateApiQuotePushOne(Integer aid);

    int updateModule(AuthorityModulePut params);

    int deleteModules(AuthorityModuleDel params);

    int updateApiQuote(AuthorityModuleDel params);

    int updateModuleSequence(AuthorityModuleSequencePatch params);

    List<Map<String, Object>> selectRoleModuleAll();

    List<Map<String, Object>> selectModulesByRid(Integer rid);

    List<Map<String, Object>> selectPagesByUid(Long id);

    List<Map<String, Object>> selectMenusByUid(Long id);

    List<Integer> selectModuleIdsByRid(Integer bufRoleId);
}
