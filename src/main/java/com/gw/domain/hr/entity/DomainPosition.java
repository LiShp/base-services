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
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * 表名：domain_position
 * @author zoujialiang
*/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("")
@Table(name = "`domain_position`")
public class DomainPosition extends AutoIncrementKeyBaseDomain<Long> {
    /**
     * 职位ID
     */
    @Column(name = "`position_id`")
    @ApiModelProperty("职位id")
    private Integer positionId;

    /**
     * 组织id
     */
    @Column(name = "`group_id`")
    @ApiModelProperty("组织id")
    private Integer groupId;

    /**
     * 职位
     */
    @Column(name = "`position_number`")
    @ApiModelProperty("职位")
    private String positionNumber;

    /**
     * 职位名称
     */
    @Column(name = "`position_name`")
    @ApiModelProperty("职位名称")
    private String positionName;

    /**
     * 工作概要
     */
    @Column(name = "`summary`")
    @ApiModelProperty("工作概要")
    private String summary;

    /**
     * 职责权限
     */
    @Column(name = "`popedoma`")
    @ApiModelProperty("职责权限")
    private String popedoma;

    /**
     * 任职资格
     */
    @Column(name = "`qualification`")
    @ApiModelProperty("任职资格")
    private String qualification;

    /**
     * 备注信息
     */
    @Column(name = "`remark`")
    @ApiModelProperty("备注信息")
    private String remark;

    /**
     * 无备注
     */
    @Column(name = "`down_position`")
    @ApiModelProperty("")
    private String downPosition;

    /**
     * 无备注
     */
    @Column(name = "`up_position`")
    @ApiModelProperty("")
    private String upPosition;

    /**
     * 定岗人数
     */
    @Column(name = "`count`")
    @ApiModelProperty("定岗人数")
    private Integer count;

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

    /**
     * 是否删除：0否 1是
     */
    @Column(name = "`delete_flag`")
    @ApiModelProperty("是否删除：0否 1是")
    private Boolean deleteFlag;

    @Override
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}