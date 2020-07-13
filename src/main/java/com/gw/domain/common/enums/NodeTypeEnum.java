package com.gw.domain.common.enums;


import lombok.Getter;

/**
 * 枚举类型_返回状态码
 * @author weixin
 */
@Getter
public enum NodeTypeEnum {

    /**
     * STATUS_ERROR_10 工号不能为空提示
     */
    ROG(2,"组织信息"),
    EMP(1,"员工信息");


    NodeTypeEnum(Integer code, String message) {
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
