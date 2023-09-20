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
@TableName("authority_login_history")
@Schema(description = "")
public class AuthorityLoginHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键(自增)
     */
    @TableId(type = IdType.AUTO)
    @Schema(description ="主键(自增)")
    private Long id;

    /**
     * 用户主键
     */
    @Schema(description ="用户主键")
    private Long userid;

    /**
     * 时间
     */
    @Schema(description ="时间")
    private LocalDateTime time;

    /**
     * IP
     */
    @Schema(description ="IP")
    private String ip;

    /**
     * 状态，0-成功/1-账号密码不匹配/2-用户已被禁用/3-登陆次数过多,账号暂时锁定
     */
    @Schema(description ="状态，0-成功/1-账号密码不匹配/2-用户已被禁用/3-登陆次数过多,账号暂时锁定")
    @TableField("`status`")
    private Integer status;

    /**
     * 说明
     */
    @Schema(description ="说明")
    @TableField("`explain`")
    private String explain;


}
