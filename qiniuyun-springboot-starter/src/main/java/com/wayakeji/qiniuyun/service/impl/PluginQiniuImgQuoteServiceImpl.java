package com.wayakeji.qiniuyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;;
import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.qiniuyun.bean.ImageQuoteBean;
import com.wayakeji.qiniuyun.entity.PluginQiniuImgQuote;
import com.wayakeji.qiniuyun.mapper.PluginQiniuImgMapper;
import com.wayakeji.qiniuyun.mapper.PluginQiniuImgQuoteMapper;
import com.wayakeji.qiniuyun.params.QiniuImgQuote;
import com.wayakeji.qiniuyun.service.PluginQiniuImgQuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author waya code generator
 * @date 2022-11-23 18:17:37
 */
@Service
@RequiredArgsConstructor
public class PluginQiniuImgQuoteServiceImpl extends ServiceImpl<PluginQiniuImgQuoteMapper, PluginQiniuImgQuote> implements PluginQiniuImgQuoteService {

    private final PluginQiniuImgMapper pluginQiniuImgMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOne(Integer id, String alias, String tableName, Integer tableId) {
        return insert(id, alias, null, tableName, tableId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int inserts(int[] ids, String alias, String moduleName, String tableName, Integer tableId) {
        List<ImageQuoteBean> list = new ArrayList<>();
        List<QiniuImgQuote> quotes = new ArrayList<>();
        ImageQuoteBean bean;
        QiniuImgQuote quote;
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] < 1) {
                throw new HandyserveException("参数错误");
            }
            bean = new ImageQuoteBean();
            bean.setPid(ids[i]);
            bean.setAlias(alias);
            bean.setName(moduleName);
            bean.setTable(tableName);
            bean.setTid(tableId);
            list.add(bean);
            quote = new QiniuImgQuote();
            quote.setId(ids[i]);
            quote.setQuote(1);
            quotes.add(quote);
        }
        int num = baseMapper.inserts(list);
        num += pluginQiniuImgMapper.updateQuotes(quotes);
        return num;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletes(String tableName, Integer tableId) {
        ImageQuoteBean params = new ImageQuoteBean();
        params.setTable(tableName);
        params.setTid(tableId);
        List<Integer> list;
        list = baseMapper.selectPidsByTable(params);
        if (list == null || list.size() == 0) {
            return 0;
        }
        List<QiniuImgQuote> quotes = new ArrayList<>();
        Map<Integer, QiniuImgQuote> map = new HashMap<>();
        QiniuImgQuote quote;
        for (int i = 0; i < list.size(); i++) {
            quote = map.get(list.get(i));
            if (quote == null) {
                quote = new QiniuImgQuote();
                quote.setId(list.get(i));
                quote.setQuote(-1);
                map.put(list.get(i), quote);
                quotes.add(quote);
            } else {
                quote.setQuote(quote.getQuote() - 1);
            }
        }
        map = null;
        int num = pluginQiniuImgMapper.updateQuotes(quotes);
        num += baseMapper.delete(params);
        return num;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletes(String tableName, Integer tableId, String moduleName) {
        ImageQuoteBean params = new ImageQuoteBean();
        params.setTable(tableName);
        params.setTid(tableId);
        params.setName(moduleName);
        List<Integer> list;
        list = baseMapper.selectPidsByTable(params);
        if (list == null || list.size() == 0) {
            return 0;
        }
        List<QiniuImgQuote> quotes = new ArrayList<>();
        Map<Integer, QiniuImgQuote> map = new HashMap<>();
        QiniuImgQuote quote;
        for (int i = 0; i < list.size(); i++) {
            quote = map.get(list.get(i));
            if (quote == null) {
                quote = new QiniuImgQuote();
                quote.setId(list.get(i));
                quote.setQuote(-1);
                map.put(list.get(i), quote);
                quotes.add(quote);
            } else {
                quote.setQuote(quote.getQuote() - 1);
            }
        }
        map = null;
        int num = pluginQiniuImgMapper.updateQuotes(quotes);
        num += baseMapper.delete(params);
        return num;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(String tableName, Integer tableId) {
        ImageQuoteBean params = new ImageQuoteBean();
        params.setTable(tableName);
        params.setTid(tableId);
        return delete(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(String tableName, Integer tableId, String moduleName) {
        ImageQuoteBean params = new ImageQuoteBean();
        params.setTable(tableName);
        params.setTid(tableId);
        params.setName(moduleName);
        return delete(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(String tableName, Integer tableId, String moduleName, Integer id) {
        ImageQuoteBean params = new ImageQuoteBean();
        params.setTable(tableName);
        params.setTid(tableId);
        params.setName(moduleName);
        return delete(params, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(String tableName, Integer tableId, Integer id) {
        ImageQuoteBean params = new ImageQuoteBean();
        params.setTable(tableName);
        params.setTid(tableId);
        return delete(params, id);
    }

    private int delete(ImageQuoteBean params) {
        int id = baseMapper.selectPidByTable(params);
        return delete(params, id);
    }

    /**
     * 删除引用记录
     * <p>在所有引用图片表删除数据时调用此方法
     * <p>该方法会删除引用记录，并减少引用数目
     *
     * @param params 包含表名称和表主键id的参数类
     * @param id    图片表id
     * @return 正常情况下返回2，如果出现数据差异，可能返回0或者1。
     * <p>1. 当修改图片引用数量出现sql错误时
     * <p>2. 当删除引用出现sql错误时
     */
    private int delete(ImageQuoteBean params, Integer id) {
        int num = 0;
        if (id != 0) {
            QiniuImgQuote quoteParams = new QiniuImgQuote();
            quoteParams.setId(id);
            quoteParams.setQuote(-1);
            num += pluginQiniuImgMapper.updateQuote(quoteParams);
        }
        num += baseMapper.delete(params);
        return num;
    }


    /**
     * 新增图片引用
     * <p>在所有新增图片的地方应调用此方法
     * <p>图片引用作用与图片记录，图片反查询
     * <p>需要注意各个字段的最大值，它们都可以在表‘plugin_qiniu_img_quote’中查看
     *
     * @param id         图片表主键ID，该字段应该从前端得到
     * @param alias      当前引用图片的模块名称
     * @param moduleName 模块名称，如果它为null，则查询引用时使用关联查询
     * @param tableName  当前引用图片的表名称
     * @param tableId    当前引用图片的表记录主键id（注意，表的主键名称必须为'id'）
     * @return 返回数据更新条数，当前必定返回数字“2”
     * @throws <p>1. 当新增引用记录和修改引用次数时，sql执行出现异常
     *               <p>2. 当修改数据条数时并没有传入图片id时，抛出异常提示数据差异
     */
    public int insert(Integer id, String alias, String moduleName,
                      String tableName, Integer tableId) {
        ImageQuoteBean params = new ImageQuoteBean();
        params.setPid(id);
        params.setAlias(alias);
        params.setName(moduleName);
        params.setTable(tableName);
        params.setTid(tableId);
        int num = baseMapper.insert(params);
        QiniuImgQuote quoteParams = new QiniuImgQuote();
        quoteParams.setId(id);
        quoteParams.setQuote(1);
        num += pluginQiniuImgMapper.updateQuote(quoteParams);
        // 如果不等于2，表示做了并没有做到两次更新操作，可能是参数错误了，这种情况一般不会存在
        // 只会在出现数据差异的时候出现
        if (num != 2) {
            throw new HandyserveException("图片数据出现差异，请刷新后重试");
        }
        return num;
    }
}
