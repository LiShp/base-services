package com.gw.domain.hr.entity.po;

import com.gw.domain.hr.entity.DomainWorkExperience;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 表名：domain_work_experience
*/
@Getter
@Setter
@ToString
@ApiModel("工作经历表-PO")
public class DomainWorkExperienceResultPo extends DomainWorkExperience {
    /**
     * 工号(系统自动生成)^[A-Za-z0-9]+$
     */
    @ApiModelProperty("工号(系统自动生成)")
    private String personnelNo;
}