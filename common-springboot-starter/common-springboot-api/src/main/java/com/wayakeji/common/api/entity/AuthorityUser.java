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
@TableName("authority_user")
@Schema(description = "")
public class AuthorityUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @Schema(description ="主键")
    private Long id;

    /**
     * 用户名
     */
    @Schema(description ="用户名")
    private String username;

    /**
     * 电话
     */
    @Schema(description ="电话")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description ="邮箱")
    private String email;

    /**
     * 密码摘要
     */
    @Schema(description ="密码摘要")
    private String password;

    /**
     * 密码盐
     */
    @Schema(description ="密码盐")
    private String salt;

    /**
     * 密码混淆
     */
    @Schema(description ="密码混淆")
    private String mix;

    /**
     * 用户昵称
     */
    @Schema(description ="用户昵称")
    private String nickname;

    /**
     * 密钥，用于加解密/签名
     */
    @Schema(description ="密钥，用于加解密/签名")
    private String secret;

    /**
     * 邀请码
     */
    @Schema(description ="邀请码")
    private String invitecode;

    /**
     * 被邀请码
     */
    @Schema(description ="被邀请码")
    private String invitecodefrom;

    /**
     * 组织
     */
    @Schema(description ="组织")
    private String organization;

    /**
     * 是否启用，1-肯定，0-否定
     */
    @Schema(description ="是否启用，1-肯定，0-否定")
    @TableField("`enabled`")
    private Integer enabled;

    /**
     * 用户是属于哪个域的？如后台管理用户、小程序用户、API用户等
     */
    @Schema(description ="用户是属于哪个域的？如后台管理用户、小程序用户、API用户等")
    private String belong;

    /**
     * 创建时间
     */
    @Schema(description ="创建时间")
    private LocalDateTime createtime;

    /**
     * 创建IP
     */
    @Schema(description ="创建IP")
    private String createip;

    /**
     * 最后登录时间
     */
    @Schema(description ="最后登录时间")
    private LocalDateTime logintime;

    /**
     * 最后登录IP
     */
    @Schema(description ="最后登录IP")
    private String loginip;

    /**
     * 创建人ID
     */
    @Schema(description ="创建人ID")
    private Long adduid;

    /**
     * 创建人角色ID
     */
    @Schema(description ="创建人角色ID")
    private Integer addrid;


}
