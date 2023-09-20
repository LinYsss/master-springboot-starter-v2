package com.wayakeji.common.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wayakeji.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 
 *
 * @author LY
 * @date 2023-06-15 11:30:16
 */
@Data
@TableName("authority_api")
@Schema(description = "")
public class AuthorityApi implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(description ="id")
    private Integer id;

    /**
     * api地址
     */
    @Schema(description ="api地址")
    @TableField("`api`")
    private String api;

    /**
     * api请求方式
     */
    @Schema(description ="api请求方式")
    @TableField("`method`")
    private String method;

    /**
     * 引用次数
     */
    @Schema(description ="引用次数")
    @TableField("`quote`")
    private Integer quote;

    /**
     * 说明
     */
    @Schema(description ="说明")
    @TableField("`explain`")
    private String explain;


}
