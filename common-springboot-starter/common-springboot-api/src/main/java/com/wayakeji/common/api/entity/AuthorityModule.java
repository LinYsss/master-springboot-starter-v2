package com.wayakeji.common.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wayakeji.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
@Data
@TableName("authority_module")
@Schema(description = "")
public class AuthorityModule implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(description ="id")
    private Integer id;

    /**
     * pid
     */
    @Schema(description ="pid")
    private Integer pid;

    /**
     * 名称
     */
    @Schema(description ="名称")
    @TableField("`name`")
    private String name;

    /**
     * icon
     */
    @Schema(description ="icon")
    private String icon;

    /**
     * 1-目录，2-菜单，3-页面，4-操作（对应api）
     */
    @Schema(description ="1-目录，2-菜单，3-页面，4-操作（对应api）")
    @TableField("`type`")
    private Integer type;

    /**
     * api的id
     */
    @Schema(description ="api的id")
    private Integer aid;

    /**
     * 页面地址
     */
    @Schema(description ="页面地址")
    @TableField("`page`")
    private String page;

    /**
     * 说明
     */
    @Schema(description ="说明")
    @TableField("`explain`")
    private String explain;

    /**
     * 排序号
     */
    @Schema(description ="排序号")
    @TableField("`sequence`")
    private Integer sequence;


}
