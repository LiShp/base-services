package com.gw.domain.hr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.cloud.common.base.entity.AutoIncrementKeyBaseDomain;
import com.gw.cloud.common.base.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author weixin
 * 表名：domain_file_info
 * 表注释：文件信息表
*/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel("文件信息表")
@Table(name = "`domain_file_info`")
public class DomainFileInfo extends AutoIncrementKeyBaseDomain<Long> {
    /**
     * 文件key
     */
    @Column(name = "`code`")
    @ApiModelProperty("文件key")
    private String code;

    /**
     * 文件名
     */
    @Column(name = "`name`")
    @ApiModelProperty("文件名")
    private String name;

    /**
     * 是否是图片
     */
    @Column(name = "`isImg`")
    @ApiModelProperty("是否是图片")
    private Byte isimg;

    /**
     * 文件类型
     */
    @Column(name = "`contentType`")
    @ApiModelProperty("文件类型")
    private String contenttype;

    /**
     * 文件大小
     */
    @Column(name = "`size`")
    @ApiModelProperty("文件大小")
    private Integer fileSize;

    /**
     * 物理路径
     */
    @Column(name = "`path`")
    @ApiModelProperty("物理路径")
    private String path;

    /**
     * 文件网络url
     */
    @Column(name = "`url`")
    @ApiModelProperty("文件网络url")
    private String url;

    /**
     * 文件存储地方
     */
    @Column(name = "`source`")
    @ApiModelProperty("文件存储地方")
    private String source;

    /**
     * 逻辑删除标志
     */
    @Column(name = "`state`")
    @ApiModelProperty("逻辑删除标志")
    private Integer state;

    /**
     * 创建时间
     */
    @Column(name = "`createTime`")
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "`updateTime`")
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date updateTime;

    /**
     * 文件盘/桶
     */
    @Column(name = "`bucket`")
    @ApiModelProperty("文件盘/桶")
    private String bucket;
}