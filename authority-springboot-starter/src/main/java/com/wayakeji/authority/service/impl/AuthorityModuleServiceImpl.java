package com.wayakeji.authority.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayakeji.authority.mapper.AuthorityApiMapper;
import com.wayakeji.authority.mapper.AuthorityModuleMapper;
import com.wayakeji.authority.mapper.AuthorityRoleMapper;
import com.wayakeji.authority.mapper.AuthorityUserMapper;
import com.wayakeji.authority.service.AuthorityModuleService;
import com.wayakeji.common.api.dto.*;
import com.wayakeji.common.api.entity.AuthorityApi;
import com.wayakeji.common.api.entity.AuthorityModule;
import com.wayakeji.common.api.entity.AuthorityRole;
import com.wayakeji.common.api.entity.AuthorityUser;
import com.wayakeji.common.api.vo.AuthorityApiCacheVO;
import com.wayakeji.common.api.vo.AuthorityApiVO;
import com.wayakeji.common.core.exception.InsertException;
import com.wayakeji.common.core.exception.SelectException;
import com.wayakeji.common.core.exception.UpdateException;
import com.wayakeji.common.security.login.BufferAuthority;
import com.wayakeji.common.security.login.Buffers;
import com.wayakeji.common.security.login.CacheApis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
@Service
@RequiredArgsConstructor
public class AuthorityModuleServiceImpl extends ServiceImpl<AuthorityModuleMapper, AuthorityModule> implements AuthorityModuleService {

    private final AuthorityApiMapper authorityApiMapper;

    private final AuthorityRoleMapper authorityRoleMapper;

    private final AuthorityUserMapper authorityUserMapper;

    private final  Buffers buffers;

    @Override
    public List<AuthorityApiCacheVO> selectCacheApis() {
        return baseMapper.selectCacheApis();
    }

    @Override
    public int insertApi(AuthorityApi params) {
        return authorityApiMapper.insert(params);
    }

    @Override
    public List<AuthorityApi> selectApiAll() {
        return authorityApiMapper.selectList(Wrappers.<AuthorityApi>lambdaQuery().orderByAsc(AuthorityApi::getQuote));
    }

    @Override
    public int updateApi(AuthorityApi params) {
        return authorityApiMapper.updateById(params);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteApi(Integer id) {
        int count = authorityRoleMapper.delModuleByMid(id);
        count += baseMapper.delete(Wrappers.<AuthorityModule>query().lambda().eq(AuthorityModule::getAid, id));
        count += authorityApiMapper.deleteById(id);
        return 0;
    }

    @Override
    public List<Map<String, Object>> selectModuleAll() {
        return baseMapper.selectModuleAll();
    }

    @Override
    public int insertDirectory(AuthorityModuleAdd params) {
        int count = baseMapper.insertModule(params);
        if(params.getType().intValue() == 4) {
            CacheApis.add(params.getId(), params.getApi(), params.getMethod());
        }
        return count;
    }

    @Override
    public int insertMenu(AuthorityModuleAdd params) {
        if(params.getSequence() == null) {
            throw new InsertException("请对页面/菜单添加排序号。");
        }
        return insertDirectory(params);

    }

    @Override
    public int insertPage(AuthorityModuleAdd params) {
        if(params.getSequence() == null) {
            throw new InsertException("请对页面/菜单添加排序号。");
        }
        if(params.getPage() == null) {
            throw new InsertException("新增页面必须添加页面地址。");
        }
        return insertDirectory(params);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertAction(AuthorityModuleAdd params) {
        int count = 0;
        if(params.getAid() != null && params.getAid() != 0) {
            count += insertDirectory(params);
            count += baseMapper.updateApiQuotePushOne(params.getAid());
        }else if(params.getApi() != null && params.getMethod() != null) {
            AuthorityApi apiAdd = new AuthorityApi();
            apiAdd.setApi(params.getApi());
            apiAdd.setMethod(params.getMethod());
            apiAdd.setQuote(1);
            apiAdd.setExplain(params.getExplain());
            count += insertApi(apiAdd);
            params.setAid(apiAdd.getId());
            count += insertDirectory(params);
        }
        return count;

    }

    @Override
    public int updateDirectory(AuthorityModulePut params) {
        return baseMapper.updateModule(params);
    }

    @Override
    public int updateMenu(AuthorityModulePut params) {
        return baseMapper.updateModule(params);
    }

    @Override
    public int updatePage(AuthorityModulePut params) {
        if(params.getPage() == null) {
            throw new UpdateException("新增页面必须添加页面地址。");
        }
        return baseMapper.updateModule(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteModules(AuthorityModuleDel params) {
        int count = baseMapper.deleteModules(params);
        CacheApis.removes(params.getIds());
        count += authorityRoleMapper.delModuleByMids(params.getIds());
        if(params.getApiQuotes() != null && params.getApiQuotes().size() > 0) {
            count += baseMapper.updateApiQuote(params);
        }
        return count;
    }

    @Override
    public int updateModuleSequence(AuthorityModuleSequencePatch params) {
        return baseMapper.updateModuleSequence(params);
    }

    @Override
    public List<Map<String, Object>> selectRoleModuleAll() {
        return baseMapper.selectRoleModuleAll();
    }

    @Override
    public List<Map<String, Object>> selectModulesByRid(Integer rid) {
        return baseMapper.selectModulesByRid(rid);
    }

    @Override
    public List<AuthorityApiVO> queryApiById(AuthorityUser user) {
        List<AuthorityApiVO> apis = authorityUserMapper.selectApisByUid(user.getId());
        BufferAuthority buf = new BufferAuthority();
        if(apis == null || apis.size() == 0) {
            buf.setModule("");
        }else {
            StringBuilder strb = new StringBuilder();
            for(int i = apis.size() - 1; i >= 0; i--) {
                strb.append(apis.get(i).getId());
                if(i != 0) {
                    strb.append(",");
                }
            }
            buf.setModule(strb.toString());
        }
        try {
            buffers.setAuthority(user.getId(), buf);
        }catch (IOException e) {
            throw new SelectException(e);
        }
        return apis;
    }

    @Override
    public Map<String, Object> queryPageAndMenusById(AuthorityUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put("pages", baseMapper.selectPagesByUid(user.getId()));
        map.put("menus", baseMapper.selectMenusByUid(user.getId()));
        map.put("apis", queryApiById(user));
        return map;
    }
}
