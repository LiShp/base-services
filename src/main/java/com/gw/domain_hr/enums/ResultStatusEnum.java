package com.gw.domain_hr.enums;


import lombok.Getter;

@Getter
public enum ResultStatusEnum {

    STATUS_SUCCESS("S","执行成功"),
    STATUS_ERROR("E","执行失败");


    public String getCode() {
        return code;
    }

    ResultStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String code;

    private String message;






}
