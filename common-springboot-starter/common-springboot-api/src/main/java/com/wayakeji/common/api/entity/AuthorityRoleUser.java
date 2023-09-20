package com.wayakeji.common.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("authority_role_user")
@Schema(description = "")
public class AuthorityRoleUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(description ="id")
    private Integer id;

    /**
     * 角色id
     */
    @Schema(description ="角色id")
    private Integer rid;

    /**
     * 用户id
     */
    @Schema(description ="用户id")
    private Long uid;


}
