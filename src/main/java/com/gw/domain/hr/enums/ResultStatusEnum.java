package com.gw.domain.hr.enums;


import lombok.Getter;

/**
 * 枚举类型_返回状态码
 * @author zoujialiang
 */
@Getter
public enum ResultStatusEnum {

    /**
     * STATUS_ERROR_10 工号不能为空提示
     */
    STATUS_ERROR_10("10","工号personnelno不能为空");


    ResultStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态码描述
     */
    private String message;






}
