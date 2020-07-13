package com.gw.domain.hr.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.cloud.common.base.util.DateUtil;
import com.gw.domain.hr.entity.DomainWorkExperience;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @author weixin
 * 表名：domain_work_experience
*/
@Getter
@Setter
@ToString
@ApiModel("工作经历表-VO")
public class DomainWorkExperienceResponseVO implements Serializable {
    /**
     * 本人员工id
     */
    @ApiModelProperty("本人员工id")
    private Integer personId;

    /**
     * 工号(系统自动生成)^[A-Za-z0-9]+$
     */
    @ApiModelProperty("工号(系统自动生成)")
    private String personnelNo;

    /**
     * 开始日期
     */
    @ApiModelProperty("工作开始日期")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date workStartTime;

    /**
     * 结束日期
     */
    @ApiModelProperty("工作结束日期")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date workEndTime;

    /**
     * 所在企业
     */
    @Column(name = "`work_company`")
    @ApiModelProperty("所在企业")
    private String workCompany;

    /**
     * 所在部门
     */
    @Column(name = "`work_department`")
    @ApiModelProperty("所在部门")
    private String workDepartment;

    /**
     * 公司内部调动的组织id记录
     */
    @ApiModelProperty("公司内部调动的组织id记录")
    private Integer workGroupId;

    /**
     * 职务
     */
    @ApiModelProperty("职务")
    private String workDuty;

    /**
     * 工作内容描述
     */
    @ApiModelProperty("工作内容描述")
    private String workDescription;


    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date updateTime;


    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date createTime;


}