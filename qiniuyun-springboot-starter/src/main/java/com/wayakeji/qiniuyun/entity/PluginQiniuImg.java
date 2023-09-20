package com.wayakeji.qiniuyun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wayakeji.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 图片库
 *
 * @author waya
 * @date 2022-11-21 11:48:58
 */
@Data
@TableName("plugin_qiniu_img")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "图片库")
public class PluginQiniuImg extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="id")
    private Integer id;

    /**
     * 七牛云key
     */
    @Schema(description ="七牛云key")
    private String key;

    /**
     * 七牛云图片地址
     */
    @Schema(description ="七牛云图片地址")
    private String addr;

    /**
     * 图片大小，单位byte
     */
    @Schema(description ="图片大小，单位byte")
    private Integer size;

    /**
     * 图片名称
     */
    @Schema(description ="图片名称")
    private String name;

    /**
     * 开始上传时间，即创建时间
     */
    @Schema(description ="开始上传时间，即创建时间")
    private LocalDateTime time;

    /**
     * 图片被引用的次数
     */
    @Schema(description ="图片被引用的次数")
    private Integer quote;

    /**
     * 用户ID(该值可通过缓存bufAlivodUid指定)
     */
    @Schema(description ="用户ID(该值可通过缓存bufAlivodUid指定)")
    private Long adduid;

    /**
     * 角色ID(该值可通过缓存bufAlivodRid指定)
     */
    @Schema(description ="角色ID(该值可通过缓存bufAlivodRid指定)")
    private Integer addrid;

    /**
     * 最后修改时间
     */
    @Schema(description ="最后修改时间")
    private LocalDateTime modifytime;

    /**
     * 文件夹Id
     */
    @Schema(description ="文件夹Id")
    private Integer pid;

    /**
     * 类型 0 ：图片， 1：文件夹
     */
    @Schema(description ="类型 0 ：图片， 1：文件夹")
    private Integer type;


}
