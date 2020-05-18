package com.gw.domain.hr.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.domain.hr.commonutils.DateUtil;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

/**
 * 表名：domain_employee_Info、domain_org_structure
 * @author gwx
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DomainEmpOrgResultPo extends DomainEmployeeInfo {

    /**
     * 组织ID
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 组织名称
     */
    @Column(name = "group_id")
    private String groupName;

    /**
     * 员工状态
     */
    private Integer personnelStatus;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date createTime;


    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date updateTime;

    /**
     * unitId：单位ID
     */
    @Column(name = "unit_id")
    private Integer unitId;


    /**
     * unitName：单位名称
     */
    private String unitName;

    /**
     * departmentId：部门ID
     */
    private Integer departmentId;


    /**
     * departmentName：部门名称
     */
    private String departmentName;


}