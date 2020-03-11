package com.gw.domain.hr.entity.vo;

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
import javax.validation.constraints.*;
import java.util.Date;

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
public class DomainEmployeeInfoVO extends AutoIncrementKeyBaseDomain<Long> {

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
     * 性别
     */
    @Column(name = "`sex`")
    @ApiModelProperty("性别")
    @NotNull(message = "性别不能为null")
    @Max(value = 2, message = "性别只能为1或者2：1女 2男")
    @Min(value = 1, message = "性别只能为1或者2：1女 2男")
    private Integer sex;

    /**
     * 联系方式
     */
    @Column(name = "`mobile`")
    @ApiModelProperty("联系方式")
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "联系方式输入格式有误，请检查")
    private String mobile;


    /**
     * 邮箱
     */
    @Column(name = "`email`")
    @ApiModelProperty("邮箱")
    private String email;


    /**
     * 所在科室
     */
    @Column(name = "`group_id`")
    @ApiModelProperty("所在科室")
    private Integer groupId;

    /**
     * 所在科室
     */
    @Column(name = "`group_id`")
    @ApiModelProperty("二级部ID")
    private Integer parentId;

    /**
     * 所在科室
     */
    @Column(name = "`group_name`")
    @ApiModelProperty("二级部名称")
    private String parentName;


    /**
     * 人员状态：1在职 2离职
     */
    @Column(name = "`personnel_status`")
    @ApiModelProperty("人员状态：1在职 2离职")
    @Max(value = 2, message = "人员状态只能为1或者2：1在职 2离职")
    @Min(value = 1, message = "人员状态只能为1或者2：1在职 2离职")
    private Integer personnelStatus;

    /**
     * 是否删除：0否 1是
     */
    @Column(name = "`delete_flag`")
    @ApiModelProperty("是否删除：0否 1是")
    private Boolean deleteFlag;

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    @Override
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}