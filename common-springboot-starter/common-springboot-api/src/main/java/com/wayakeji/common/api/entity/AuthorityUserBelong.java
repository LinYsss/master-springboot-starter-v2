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
 * @date 2023-06-15 11:30:15
 */
@Data
@TableName("authority_user_belong")
@Schema(description = "")
public class AuthorityUserBelong implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * belong
     */
    @TableId(type = IdType.AUTO)
    @Schema(description ="belong")
    private String belong;

    /**
     * 说明
     */
    @Schema(description ="说明")
    @TableField("`explain`")
    private String explain;


}
