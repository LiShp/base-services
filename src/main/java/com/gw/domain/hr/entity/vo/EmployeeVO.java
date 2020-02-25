package com.gw.domain.hr.entity.vo;

import com.gw.cloud.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * @description 员工信息
 * @auther gwx
 * @date 2020-02-25
 */
@Getter
@Setter
public class EmployeeVO implements Serializable {

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
