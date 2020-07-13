package com.gw.domain.hr.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
public class EmployeeOrgPrivacyResponseResponseVO extends EmployeeOrgResponseVO {

    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    @DateTimeFormat(pattern = com.gw.cloud.common.base.util.DateUtil.DEFAULT_FORMAT_PATTERN_DATE)
    @JsonFormat(pattern = com.gw.cloud.common.base.util.DateUtil.DEFAULT_FORMAT_PATTERN_DATE, timezone = com.gw.cloud.common.base.util.DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date birthDate;

    /**
     * 体重
     */
    @ApiModelProperty("体重")
    private String weight;

    /**
     * 身高
     */
    @ApiModelProperty("身高")
    private String height;

    /**
     * 学历
     */
    @ApiModelProperty("学历")
    private String diplomaName;

    /**
     * 专业
     */
    @ApiModelProperty("专业")
    private String professional;

    /**
     * 毕业学校
     */
    @ApiModelProperty("毕业学校")
    private String graduateSchool;
}