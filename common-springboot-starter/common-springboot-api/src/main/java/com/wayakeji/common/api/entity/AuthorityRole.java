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
import java.time.LocalDateTime;

/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
@Data
@TableName("authority_role")
@Schema(description = "")
public class AuthorityRole implements Serializable {
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
     * name
     */
    @Schema(description ="name")
    private String name;

    /**
     * explain
     */
    @Schema(description ="explain")
    @TableField("`explain`")
    private String explain;

    /**
     * clientid
     */
    @Schema(description ="clientid")
    private Long clientid;

    /**
     * addtime
     */
    @Schema(description ="addtime")
    private LocalDateTime addtime;


}
