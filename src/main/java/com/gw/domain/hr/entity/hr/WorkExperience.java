package com.gw.domain.hr.entity.hr;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.cloud.common.base.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author weixin
 * 表名：domain_work_experience
*/
@Getter
@Setter
@ToString
@ApiModel("HR-工作经历表")
@Table(name = "`p_work_experience`")
public class WorkExperience  {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`work_experience_id`")
    @ApiModelProperty("主键id")
    private Integer workExperienceId;

    /**
     * 本人员工id
     */
    @Column(name = "`person_id`")
    @ApiModelProperty("本人员工id")
    private Integer personId;

    /**
     * 开始日期
     */
    @Column(name = "`work_start_time`")
    @ApiModelProperty("开始日期")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date workStartTime;

    /**
     * 结束日期
     */
    @Column(name = "`work_end_time`")
    @ApiModelProperty("结束日期")
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
    @Column(name = "`work_group_id`")
    @ApiModelProperty("公司内部调动的组织id记录")
    private Integer workGroupId;

    /**
     * 职务
     */
    @Column(name = "`work_duty`")
    @ApiModelProperty("职务")
    private String workDuty;

    /**
     * 工作内容描述
     */
    @Column(name = "`work_description`")
    @ApiModelProperty("工作内容描述")
    private String workDescription;

    /**
     * 直接主管
     */
    @Column(name = "`work_direct_supervisor`")
    @ApiModelProperty("直接主管")
    private String workDirectSupervisor;

    /**
     * 直接主管电话
     */
    @Column(name = "`supervisor_phone`")
    @ApiModelProperty("直接主管电话")
    private String supervisorPhone;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date updateTime;

    /**
     * 更新人id
     */
    @Column(name = "`update_user_id`")
    @ApiModelProperty("更新人id")
    private Integer updateUserId;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date createTime;

    /**
     * 创建人id
     */
    @Column(name = "`create_user_id`")
    @ApiModelProperty("创建人id")
    private Integer createUserId;

    /**
     * 是否删除,0:否,1:是
     */
    @Column(name = "`is_delete`")
    @ApiModelProperty("是否删除,0:否,1:是")
    private Integer isDelete;
}