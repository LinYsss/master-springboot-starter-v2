package com.wayakeji.qiniuyun.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.common.core.util.code.TextCoding;
import com.wayakeji.common.security.login.BufferParams;
import com.wayakeji.common.security.login.LoginHelper;
import com.wayakeji.qiniuyun.bean.ImageKeyBean;
import com.wayakeji.qiniuyun.bean.ImageQuoteBean;
import com.wayakeji.qiniuyun.config.Qiniuyun;
import com.wayakeji.qiniuyun.entity.PluginQiniuImg;
import com.wayakeji.qiniuyun.mapper.PluginQiniuImgMapper;
import com.wayakeji.qiniuyun.params.QiniuImgDel;
import com.wayakeji.qiniuyun.params.QiniuImgGet;
import com.wayakeji.qiniuyun.service.PluginQiniuImgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片库
 *
 * @author waya
 * @date 2022-11-21 11:48:58
 */
@Service
public class PluginQiniuImgServiceImpl extends ServiceImpl<PluginQiniuImgMapper, PluginQiniuImg> implements PluginQiniuImgService {

    @Override
    public Map<String, Object> token() {
        Map<String, Object> result = new HashMap<>(3);
        String yyyyMM = DateTimeFormatter.ofPattern("yyyyMM").format(LocalDate.now());
        result.put("key", Qiniuyun.getKey() + "/" + yyyyMM + "/" + TextCoding.createLowerRule32());
        result.put("token", Qiniuyun.uploadToken());
        return result;
    }

    @Override
    public int insertOne(PluginQiniuImg pluginQiniuImg, BufferParams buf) {
        pluginQiniuImg.setAddr(Qiniuyun.getDomain() + pluginQiniuImg.getKey() + Qiniuyun.getImageslim());
        pluginQiniuImg.setAdduid(LoginHelper.clientId());
        pluginQiniuImg.setAdduid(buf.getClientId());
        pluginQiniuImg.setAddrid(buf.getBufRoleId());
        return baseMapper.insertOne(pluginQiniuImg);
    }

    @Override
    public Page<PluginQiniuImg> queryPage(Page page, PluginQiniuImg pluginQiniuImg) {
        return baseMapper.queryPage(page, pluginQiniuImg);
    }

    @Override
    public int removeQiniu(QiniuImgDel params) {
        List<ImageKeyBean> list = baseMapper.selectKeyByIds(params);
        List<String> keyList = new ArrayList<>(list.size() * 3 / 2);
        for (ImageKeyBean key : list) {
            // 校验，当需要删除的key在数据库只存在一条时（此种情况防止多个账号复制相同数据并引用）
            if (key.getNum() == 1) {
                keyList.add(key.getKey());
            }
        }
        int num;
        int count = baseMapper.selectSumQuoteByIds(params);
        if (count != 0) {
            throw new HandyserveException("有图片被引用，无法删除");
        } else {
            num = baseMapper.removeQiniu(params);
        }

        if (num != params.getIds().size()) {
            throw new HandyserveException("参数错误");
        }
        return num;
    }

    @Override
    public List<Map<String, Object>> selectQuoteByPid(Integer pid) {
        List<ImageQuoteBean> list = baseMapper.selectQuoteByPid(pid);
        List<Map<String, Object>> result = new ArrayList<>((list.size() * 3 / 2));
        Map<String, Object> data;
        for (ImageQuoteBean bean : list) {
            data = new HashMap<>(3);
            if (bean.getName() == null || bean.getName().isEmpty()) {
                data.put("name", baseMapper.selectQuoteTableFieldOfName(bean));
            } else {
                data.put("name", bean.getName());
            }
            data.put("alias", bean.getAlias());
            result.add(data);
        }
        return result;
    }

    @Override
    public int insertV2(PluginQiniuImg params,BufferParams buf) {
        params.setAdduid(buf.getClientId());
        params.setAddrid(buf.getBufRoleId());
        if (params.getType().intValue() != 1) {
            params.setAddr(Qiniuyun.getDomain() + params.getKey() + Qiniuyun.getImageslim());
        }
        return baseMapper.insertV2(params);
    }

    @Override
    public int updateNameV2(PluginQiniuImg params) {
        return baseMapper.updateNameV2(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteV2(QiniuImgDel params) {
        baseMapper.removeQiniu(params);
        return baseMapper.deleteV2(params);
    }

    @Override
    public List<Map<String, Object>> getFolderV2(QiniuImgGet params) {
        return baseMapper.getFolderV2(params);
    }

    @Override
    public Page<PluginQiniuImg> queryV2(Page page, PluginQiniuImg pluginQiniuImg) {
        return baseMapper.queryV2(page, pluginQiniuImg);
    }

    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String key = upload(file);
        PluginQiniuImg pluginQiniuImg = new PluginQiniuImg();
        pluginQiniuImg.setKey(key);
        pluginQiniuImg.setName(fileName);
        pluginQiniuImg.setSize(Integer.parseInt(String.valueOf(file.getSize())));
        pluginQiniuImg.setAddr(Qiniuyun.getDomain() + pluginQiniuImg.getKey() + Qiniuyun.getImageslim());
        BufferParams buf = new BufferParams();
        insertOne(pluginQiniuImg,buf);
        Map<String, Object> map = new HashMap<>();
        map.put("fileName", fileName);
        map.put("url", pluginQiniuImg.getAddr());
        return map;
    }


    public String upload(MultipartFile file) throws HandyserveException {
        //构造一个带指定 Region 对象的配置类
        Configuration configuration = new Configuration(Region.region0());
        UploadManager manager = new UploadManager(configuration);
        //获取key和token
        Map<String, Object> map = token();
        try {
            InputStream inputStream = file.getInputStream();
            Response response = manager.put(inputStream, map.get("key").toString(), map.get("token").toString(), null, null);
            if (response.isOK()) {
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return putRet.key;
            }
        } catch (QiniuException e) {
            log.error(e.getCause().getMessage());
            throw new HandyserveException("上传图片错误");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
