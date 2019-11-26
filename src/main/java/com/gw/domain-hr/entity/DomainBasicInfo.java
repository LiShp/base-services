package com.gw.domain-hr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.cloud.common.base.entity.AutoIncrementKeyBaseDomain;
import com.gw.cloud.common.base.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 表名：domain_basic_info
*/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("")
@Table(name = "`domain_basic_info`")
public class DomainBasicInfo extends AutoIncrementKeyBaseDomain<Long> {
    /**
     * 父节点id
     */
    @Column(name = "`parent_id`")
    @ApiModelProperty("父节点id")
    private Integer parentId;

    /**
     * 数据值编号
     */
    @Column(name = "`value_no`")
    @ApiModelProperty("数据值编号")
    private Integer valueNo;

    /**
     * 数据值内容
     */
    @Column(name = "`text`")
    @ApiModelProperty("数据值内容")
    private String text;

    /**
     * 中文名称
     */
    @Column(name = "`name`")
    @ApiModelProperty("中文名称")
    private String name;

    /**
     * 英文名称
     */
    @Column(name = "`f_key`")
    @ApiModelProperty("英文名称")
    private String fKey;

    /**
     * 显示顺序
     */
    @Column(name = "`show_order`")
    @ApiModelProperty("显示顺序")
    private Integer showOrder;

    /**
     * 标识是17年前后数据
     */
    @Column(name = "`code`")
    @ApiModelProperty("标识是17年前后数据")
    private String code;

    /**
     * 是否删除（0：否 1：是）
     */
    @Column(name = "`delete`")
    @ApiModelProperty("是否删除（0：否 1：是）")
    private Boolean delete;

    /**
     * 备注信息
     */
    @Column(name = "`remark`")
    @ApiModelProperty("备注信息")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date createTime;

    /**
     * 创建人(工号)
     */
    @Column(name = "`create_personnel_no`")
    @ApiModelProperty("创建人(工号)")
    private String createPersonnelNo;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date updateTime;

    /**
     * 更新人(工号)
     */
    @Column(name = "`update_personnel_no`")
    @ApiModelProperty("更新人(工号)")
    private String updatePersonnelNo;
}