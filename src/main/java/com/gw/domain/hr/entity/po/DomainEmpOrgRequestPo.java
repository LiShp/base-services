package com.gw.domain.hr.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.cloud.common.base.entity.AutoIncrementKeyBaseDomain;
import com.gw.domain.hr.common.util.DateUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
public class DomainEmpOrgRequestPo extends AutoIncrementKeyBaseDomain<Long> {

    /**
     * 员工姓名
     */
    private String nameLike;

    /**
     * 员工工号
     */
    private String personnelNo;
    /**
     * 组织ID
     */
    private Integer groupId;

    /**
     * 组织名称
     */
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

    /**
     * 是否正式员工,2:非正式工,1:正式工.
     */
    private Integer isFormal;


}