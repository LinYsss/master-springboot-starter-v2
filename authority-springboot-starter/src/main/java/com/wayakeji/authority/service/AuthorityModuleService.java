package com.wayakeji.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wayakeji.common.api.dto.*;
import com.wayakeji.common.api.entity.AuthorityApi;
import com.wayakeji.common.api.entity.AuthorityModule;
import com.wayakeji.common.api.entity.AuthorityUser;
import com.wayakeji.common.api.vo.AuthorityApiCacheVO;
import com.wayakeji.common.api.vo.AuthorityApiVO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
public interface AuthorityModuleService extends IService<AuthorityModule> {

    List<AuthorityApiCacheVO> selectCacheApis();

    int insertApi(AuthorityApi params);

    List<AuthorityApi> selectApiAll();

    int updateApi(AuthorityApi params);

    int deleteApi(Integer id);

    List<Map<String, Object>> selectModuleAll();

    int insertDirectory(AuthorityModuleAdd params);

    int insertMenu(AuthorityModuleAdd params);

    int insertPage(AuthorityModuleAdd params);

    int insertAction(AuthorityModuleAdd params);

    int updateDirectory(AuthorityModulePut params);

    int updateMenu(AuthorityModulePut params);

    int updatePage(AuthorityModulePut params);

    int deleteModules(AuthorityModuleDel params);

    int updateModuleSequence(AuthorityModuleSequencePatch params);

    List<Map<String, Object>> selectRoleModuleAll();

    List<Map<String, Object>> selectModulesByRid(Integer bufRoleId);

    List<AuthorityApiVO> queryApiById(AuthorityUser user);

    Map<String, Object> queryPageAndMenusById(AuthorityUser user);
}
