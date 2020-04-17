package com.gw.domain.hr.entity.vo;

import com.gw.cloud.common.base.entity.AutoIncrementKeyBaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.*;

/**
 * 表名：domain_employee_Info
 * @author zoujialiang
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("")
public class EmployeeOrgVO extends AutoIncrementKeyBaseDomain<Long> {

    /**
     * 工号(系统自动生成)^[A-Za-z0-9]+$
     */
    @Column(name = "`personnel_no`")
    @ApiModelProperty("工号(系统自动生成)")
    @NotNull(message = "工号不能为null")
    @NotEmpty(message = "工号不能为空字符串")
    @Size(max = 10, min = 9, message = "工号长度为9-10")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "工号只能包含大写字母A-Z和数字0-9")
    private String personnelNo;


    /**
     * 姓名
     */
    @Column(name = "`name`")
    @ApiModelProperty("姓名")
    @NotNull(message = "姓名不能为null")
    @NotEmpty(message = "姓名不能为空字符串")
    private String name;


    /**
     * 所在科室
     */
    @Column(name = "`group_id`")
    @ApiModelProperty("所在科室名称")
    private String groupName;

    /**
     * 所在科室
     */
    @Column(name = "`group_name`")
    @ApiModelProperty("二级部名称")
    private String parentName;


}