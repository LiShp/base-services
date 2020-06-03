package com.gw.domain.hr.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.domain.hr.commonutils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 表名：domain_employee_Info
 * @author gwx
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel("")
public class EmployeeOrgVO implements Serializable {

    /**
     * 工号(系统自动生成)^[A-Za-z0-9]+$
     */
    @ApiModelProperty("工号(系统自动生成)")
    private String personnelNo;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;


    /**
     * 所在科室
     */
    @ApiModelProperty("所在科室名称")
    private String groupId;

    /**
     * 所在科室
     */
    @ApiModelProperty("所在科室名称")
    private String groupName;


    /**
     * 员工状态
     */
    @ApiModelProperty("员工状态:1在职、2离职")
    private Integer personnelStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date createTime;


    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date updateTime;

    /**
     * unitId：单位ID
     */
    @ApiModelProperty("单位ID")
    private Integer unitId;


    /**
     * unitName：单位名称
     */
    @ApiModelProperty("单位名称")
    private String unitName;
    /**
     * departmentId：部门ID
     */
    @ApiModelProperty("部门ID")
    private Integer departmentId;


    /**
     * departmentName：部门名称
     */
    @ApiModelProperty("部门名称")
    private String departmentName;

    /**
     * 性别：1男、2女
     */
    @ApiModelProperty("性别：1男、2女")
    private Integer sex;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 操作人：操作人工号
     */
    @ApiModelProperty("操作人工号")
    private String createPersonnelNo;

    /**
     * 员工ID
     */
    @ApiModelProperty("员工ID")
    private Long id;


    /**
     * 是否删除：0否 1是
     */
    @ApiModelProperty("是否删除：0否 1是")
    private String deleteFlag;

    /**
     * 入职日期
     */
    @ApiModelProperty("入职日期")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date joinDate;

    /**
     * 职务
     */
    @ApiModelProperty("职务")
    private String dutyName;




}