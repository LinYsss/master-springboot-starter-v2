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
@TableName("plugin_alivod")
@Schema(description = "视频管理")
public class PluginAlivod {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="id")
    private Integer id;

    /**
     * 阿里云视频id
     */
    @Schema(description ="阿里云视频id")
    private String videoid;

    /**
     * 视频封面
     */
    @Schema(description ="视频封面")
    private String cover;

    /**
     * 阿里云原视频地址
     */
    @Schema(description ="阿里云原视频地址")
    private String videooriginaladdr;

    /**
     * 阿里云转码后视频地址
     */
    @Schema(description ="阿里云转码后视频地址")
    private String videotranscodeaddr;

    /**
     * 视频大小，单位byte
     */
    @Schema(description ="视频大小，单位byte")
    private Long size;

    /**
     * 视频时长，单位秒
     */
    @Schema(description ="视频时长，单位秒")
    private Integer videotime;

    /**
     * 视频名称
     */
    @Schema(description ="视频名称")
    private String name;

    /**
     * 开始上传时间，即创建时间
     */
    @Schema(description ="开始上传时间，即创建时间")
    private LocalDateTime starttime;

    /**
     * 结束上传时间
     */
    @Schema(description ="结束上传时间")
    private LocalDateTime endtime;

    /**
     * 上传状态，1-上传中、2-上传失败、3-转码中、4-转码失败、5-完成
     */
    @Schema(description ="上传状态，1-上传中、2-上传失败、3-转码中、4-转码失败、5-完成")
    private Integer state;

    /**
     * 视频被引用的次数
     */
    @Schema(description ="视频被引用的次数")
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


}
