package com.wayakeji.authority.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayakeji.authority.mapper.AuthorityModuleMapper;
import com.wayakeji.authority.mapper.AuthorityRoleMapper;
import com.wayakeji.authority.mapper.AuthorityUserMapper;
import com.wayakeji.authority.service.AuthorityRoleService;
import com.wayakeji.authority.service.AuthorityUserService;
import com.wayakeji.common.api.dto.*;
import com.wayakeji.common.api.entity.AuthorityRole;
import com.wayakeji.common.core.exception.SelectException;
import com.wayakeji.common.security.login.BufferParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class AuthorityRoleServiceImpl extends ServiceImpl<AuthorityRoleMapper, AuthorityRole> implements AuthorityRoleService {

    private final AuthorityUserMapper authorityUserMapper;

    private final AuthorityModuleMapper authorityModuleMapper;


    @Override
    public int insert(AuthorityRoleAdd params) {
        return baseMapper.insertRole(params);
    }


    /**
     * 删除角色
     * <p>删除角色会删除角色的子、子子、子子子、...角色，直到没有子角色为止
     * <p>创建包含传入角色ID的Integer集合
     * <p>调用{@link #(Integer, List)}来获取到所有需要删除的角色ID
     * <p>然后依次批量删除角色信息、角色用户绑定、角色权限绑定
     * @param params {@link AuthorityRoleDel}
     * @return 改变数据的条数
     * @see {@link #selectDeleteAllIds(Integer, List)}
     */
    @Override
    public int delete(AuthorityRoleDel params) {
        // 先查询出所有子级
        List<Integer> ids = new ArrayList<>();
        ids.add(params.getId());
        selectDeleteAllIds(params.getId(), ids);
        params.setIds(ids);
        int count = baseMapper.deletes(params);
        count += baseMapper.delUsersByRid(params);
        count += baseMapper.delModulesByRid(params);
        return count;
    }

    @Override
    public Page<Map<String, Object>> query(Page page, AuthorityRoleQuery params) {
        return baseMapper.queryPage(page,params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveUsers(AuthorityRoleUserAdd params, BufferParams buf) {
        checkSaveUsers(params, buf);
        int count = 0;
        if(params.getAdds() != null && params.getAdds().size() > 0) {
            count += baseMapper.addUsersByRidAndUids(params);
        }
        if(params.getDels() != null && params.getDels().size() > 0) {
            count += baseMapper.delUsersByRidAndUids(params);
        }
        return count;
    }

    /**
     * 保存角色下的用户
     * @param rid
     * @param clientId
     * @param buf
     * @return
     */
    @Override
    public int addUserRole(Integer rid, Long clientId, BufferParams buf) {
        AuthorityRoleUserAdd add = new AuthorityRoleUserAdd();
        add.setRid(rid);
        List<Long> adds = new ArrayList<>();
        adds.add(clientId);
        add.setAdds(adds);
        return saveUsers(add, buf);
    }

    /**
     * 保存角色下的模块（权限）
     * @param params
     * @param buf
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveModules(AuthorityRoleModuleAdd params, BufferParams buf) {
        checkSaveModules(params, buf);
        int count = 0;
        if(params.getAdds() != null && params.getAdds().size() > 0) {
            count += baseMapper.addModulesByRidAndUids(params);
        }
        if(params.getDels() != null && params.getDels().size() > 0) {
            count += baseMapper.delModulesByRidAndUids(params);
        }
        return count;
    }

    @Override
    public int saveModulesV2(AuthorityRoleModuleAdd params, BufferParams buf) {
        checkSaveModules(params, buf);
        int count = 0;
        baseMapper.delModulesByRidAndUidsV2(params);//先删除全部
        if(params.getAdds() != null && params.getAdds().size() > 0) {
            count += baseMapper.addModulesByRidAndUids(params);
        }
        return count;
    }

    /**
     * 递归获取角色ID下所有子、子子、子子子...的角色ID
     * @param pid 角色ID
     * @param ids 储存获取到的角色ID的集合
     */
    private void selectDeleteAllIds(Integer pid, List<Integer> ids) {
        List<Integer> list = baseMapper.selectByPid(pid);
        if(list != null && list.size() > 0) {
            for(int i = 0; i < list.size(); i++) {
                ids.add(list.get(i));
                selectDeleteAllIds(list.get(i), ids);
            }
        }
    }

    /**
     * 验证保存角色用户的参数是否正确
     * <p>如果是超管，则跳过验证
     * <p>调用 selectNotRoleUidsByRid 查询出当前角色可操作的用户
     * <p>根据传入的用户列表与查询出的用户列表匹配进行验证
     * @param params {@link AuthorityRoleUserAdd}
     * @param buf {@link BufferParams}
     */
    private void checkSaveUsers(AuthorityRoleUserAdd params, BufferParams buf) {
        if(!buf.isAdmin()) {
            checkRid(params.getRid(), buf.getBufRoleId());
            if(params.getAdds() != null && params.getAdds().size() > 0) {
                List<Long> list = authorityUserMapper.selectNotRoleUidsByRid(buf.getBufRoleId());
                boolean flag;
                long sid;
                for(Long id : params.getAdds()) {
                    flag = false;
                    sid = id.longValue();
                    for(int i = 0; i < list.size(); i++) {
                        if(sid == list.get(i).longValue()) {
                            flag = true;
                            break;
                        }
                    }
                    if(!flag) {
                        throw new SelectException("参数错误，你设置的用户不是你可以管理的用户。");
                    }
                }
            }
        }
    }

    /**
     * 验证保存角色权限的参数是否正确
     * <p>如果是超管，则跳过验证
     * <p>调用{@link AuthorityModuleMapper#selectModuleIdsByRid(Integer)}查询出当前角色可操作的模块
     * <p>根据传入的模块列表与查询出的模块列表匹配进行验证
     * @param params {@link AuthorityRoleModuleAdd}
     * @param buf {@link BufferParams}
     */
    private void checkSaveModules(AuthorityRoleModuleAdd params, BufferParams buf) throws SelectException {
        if(!buf.isAdmin()) {
            checkRid(params.getRid(), buf.getBufRoleId());
            if(params.getAdds() != null && params.getAdds().size() > 0) {
                List<Integer> list = authorityModuleMapper.selectModuleIdsByRid(buf.getBufRoleId());
                boolean flag;
                int sid;
                for(Integer id : params.getAdds()) {
                    flag = false;
                    sid = id.intValue();
                    for(int i = 0; i < list.size(); i++) {
                        if(sid == list.get(i).intValue()) {
                            flag = true;
                            break;
                        }
                    }
                    if(!flag) {
                        throw new SelectException("参数错误，你设置的权限自身并没有权限。");
                    }
                }
            }
        }
    }

    /**
     * 验证传入的角色ID是否正确
     * @param rid 传入需要操作的角色ID
     * @param prid 当前登陆用户的角色ID
     */
    private void checkRid(Integer rid, Integer prid) throws SelectException {
        AuthorityRoleQuery params = new AuthorityRoleQuery();
        params.setId(rid);
        params.setPid(prid);
        int count = baseMapper.selectCheckRid(params);
        if(count != 1) {
            throw new SelectException("参数错误，你传入的角色ID不是你可以管理的角色。");
        }
    }
}
