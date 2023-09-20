package com.wayakeji.alivod.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wayakeji.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 视频管理
 *
 * @author waya
 * @date 2022-11-21 14:39:18
 */
@Data
@TableName("plugin_alivod_quote")
@Schema(description = "视频管理")
public class PluginAlivodQuote {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="id")
    private Integer id;

    /**
     * 视频表id
     */
    @Schema(description ="视频表id")
    private Integer pid;

    /**
     * 引用的别名（模块名）
     */
    @Schema(description ="引用的别名（模块名）")
    private String alias;

    /**
     * 模块详情名称（如果它为null，则关联查询name）
     */
    @Schema(description ="模块详情名称（如果它为null，则关联查询name）")
    private String name;

    /**
     * 引用的表名
     */
    @Schema(description ="引用的表名")
    private String table;

    /**
     * 引用表的id
     */
    @Schema(description ="引用表的id")
    private Integer tid;

    /**
     * 创建时间
     */
    @Schema(description ="创建时间")
    private LocalDateTime time;


}
