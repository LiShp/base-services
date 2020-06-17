package com.gw.domain.hr.enums;


import lombok.Getter;

/**
 * 枚举类型_数据库表
 * @author zoujialiang
 */
@Getter
public enum TableNameEnum {

    /**
     * HR组织架构表
     */
    TABLE_NAME_SYSGROUP("sys_Group", "HR组织架构表"),
    /**
     * HR基础信息表
     */
    TABLE_NAME_SYSFIELDVALUE("sys_FieldValue", "HR基础信息表"),
    /**
     * HR员工信息表
     */
    TABLE_NAME_HRPERSONNEL("hr_Personnel", "HR员工信息表"),
    /**
     * DOAMIN组织架构表
     */
    TABLE_NAME_ORGSTRUCTURE("domain_org_structure", "DOAMIN组织架构表"),
    /**
     * DOAMIN基础信息表
     */
    TABLE_NAME_BASICINFO("domain_basic_info", "DOAMIN基础信息表"),
    /**
     * DOAMIN员工信息表
     */
    TABLE_NAME_EMPLOYEEINFO("domain_employee_info", "DOAMIN员工信息表"),
    /**
     * DOAMIN员工职位信息表
     */
    TABLE_NAME_WORK("domain_work_experience", "DOAMIN员工工作经历"),

    /**
     * DOAMIN员工职位信息表
     */
    TABLE_NAME_FILE("domain_file_info", "DOAMIN员工头像"),
    /**
     * 时间表
     */
    TABLE_NAME_MAXTIME("domain_max_time", "时间表");

    public String getTableName() {
        return tableName;
    }

    public String getTableMessage() {
        return tableMessage;
    }

    TableNameEnum(String tableName, String tableMessage) {
        this.tableName = tableName;
        this.tableMessage = tableMessage;
    }

    /**
     * 数据库表_英文名
     */
    private String tableName;

    /**
     * 数据库表_中文描述
     */
    private String tableMessage;


}
