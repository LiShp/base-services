package com.gw.domain.hr.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author weixin
 * @description 员工信息
 * @date 2020-02-25
 */
@Getter
@Setter
@ToString
public class EmployeeResponseVO implements Serializable {

    /**
     * 工号
     */
    @ApiModelProperty("工号(系统自动生成)")
    private String personnelNo;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

}
