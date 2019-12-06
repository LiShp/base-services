package com.gw.domain_hr.enums;


import lombok.Getter;

@Getter
public enum ResultStatusEnum {

    STATUS_ERROR_10("10","工号personnelno不能为空");


    ResultStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;






}
