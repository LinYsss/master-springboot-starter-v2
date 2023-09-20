package com.wayakeji.alivod.service.impl;

import com.aliyuncs.vod.model.v20170321.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayakeji.alivod.dto.*;
import com.wayakeji.alivod.entity.PluginAlivod;
import com.wayakeji.alivod.impl.Alivod;
import com.wayakeji.alivod.mapper.PluginAlivodMapper;
import com.wayakeji.alivod.service.PluginAlivodService;
import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.common.security.login.BufferParams;
import com.wayakeji.common.security.login.LoginHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频管理
 *
 * @author waya
 * @date 2022-11-21 14:39:18
 */
@Service
@RequiredArgsConstructor
public class PluginAlivodServiceImpl extends ServiceImpl<PluginAlivodMapper, PluginAlivod> implements PluginAlivodService {

    private final Alivod alivod;

    @Override
    public UploadVideoVO insert(PluginAlivod pluginAlivod, BufferParams buf) throws Exception {
        UploadVideoVO rsp = upload(pluginAlivod);
        pluginAlivod.setVideoid(rsp.getVideoId());
        pluginAlivod.setAdduid(buf.getClientId());
        pluginAlivod.setAddrid(buf.getBufRoleId());
        try {
            baseMapper.insert(pluginAlivod);
        }catch (Exception e) {
            try {
                remove(rsp.getVideoId());
            }catch (Exception e1) {}
            throw new Exception(e);
        }
        rsp.setId(pluginAlivod.getId());
        return rsp;
    }

    @Override
    public UploadVideoVO refresh(String videoId) {
        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
        request.setVideoId(videoId);
        try {
            RefreshUploadVideoResponse response = Alivod.client().getAcsResponse(request);
            return new UploadVideoVO(response.getRequestId(),
                    response.getUploadAuth(),
                    response.getUploadAddress(),
                    response.getVideoId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandyserveException("刷新上传Token失败");
        }
    }

    @Override
    public int updateOriginalAddr(AlivodUrl params) {
        return baseMapper.updateOriginalAddr(params);
    }

    @Override
    public IPage<PluginAlivod> queryPage(Page page, PluginAlivod pluginAlivod) {
        return baseMapper.queryPage(page,pluginAlivod);
    }

    @Override
    public int delete(AlivodDel params) {
        List<AlivodVidBean> list = baseMapper.selectVidByIds(params);
        StringBuilder strb = new StringBuilder();
        for(AlivodVidBean vid : list) {
            // 校验，当需要删除的vid在数据库只存在一条时（此种情况防止多个账号复制相同数据并引用）
            if(vid.getNum() == 1) {
                if(strb.length() > 0) {
                    strb.append(",");
                }
                strb.append(vid.getVid());
            }
        }

        int num = alivodDelete(params);
        if(num != params.getIds().size()) {
            throw new HandyserveException("参数错误");
        }
        // if(strb.length() > 0) {
        // 	try {
        // 		// remove(strb.toString());
        // 	}catch (Exception e) {
        // 		throw new HandyserveException(e.getMessage());
        // 	}
        // }
        return num;
    }

    @Override
    public List<Map<String, Object>> selectQuoteByPid(Integer pid) {
        List<AlivodQuoteBean> list = baseMapper.selectQuoteByPid(pid);
        List<Map<String, Object>> result = new ArrayList<>((list.size() * 3 / 2));
        Map<String, Object> data;
        for(AlivodQuoteBean bean : list) {
            data = new HashMap<>(3);
            if(bean.getName() == null || bean.getName().isEmpty()) {
                data.put("name", baseMapper.selectQuoteTableFieldOfName(bean));
            }else {
                data.put("name", bean.getName());
            }
            data.put("alias", bean.getAlias());
            result.add(data);
        }
        return result;
    }

    public int alivodDelete(AlivodDel params) throws HandyserveException {
        int count;
        try {
            count = baseMapper.selectQuoteCountByPids(params);
        }catch (HandyserveException e) {
            throw new HandyserveException(e);
        }
        if(count != 0) {
            throw new HandyserveException("有视频被引用，无法删除");
        }
        return baseMapper.alivodDelete(params);
    }

    private UploadVideoVO upload(PluginAlivod params) throws Exception {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle(params.getName());
        request.setFileName(params.getName());
        request.setCateId(alivod.getCateId());
        request.setTags("");
        request.setCoverURL(params.getCover());
        request.setDescription("");
        request.setStorageLocation(alivod.getStorageLocation());
        try {
            CreateUploadVideoResponse response = Alivod.client().getAcsResponse(request);
            return new UploadVideoVO(response.getRequestId(),
                    response.getUploadAuth(),
                    response.getUploadAddress(),
                    response.getVideoId());
        }catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new Exception("上传视频失败");
        }
    }

    private void remove(String videoIds) throws Exception {
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoIds);
        try {
            Alivod.client().getAcsResponse(request);
        }catch (Exception e) {
            e.printStackTrace();
            throw new HandyserveException("删除视频失败");
        }
    }
}
