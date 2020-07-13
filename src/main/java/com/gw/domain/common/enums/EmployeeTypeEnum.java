package com.gw.domain.common.enums;


import lombok.Getter;

/**
 * 枚举类型_返回状态码
 * @author weixin
 */
@Getter
public enum EmployeeTypeEnum {

    /**
     * STATUS_ERROR_10 工号不能为空提示
     */
    FORMAL(1,"正式工"),
    NON_FORMAL(2,"非正式工");


    EmployeeTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态码描述
     */
    private String message;






}
