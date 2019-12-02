package com.gw.domain_hr.enums;


import lombok.Getter;

@Getter
public enum TableNameEnum {

    TABLE_NAME_SYSGROUP("sys_Group", "HR组织架构表"),
    TABLE_NAME_SYSFIELDVALUE("sys_FieldValue", "HR基础信息表"),
    TABLE_NAME_HRPERSONNEL("hr_Personnel", "HR员工信息表"),
    TABLE_NAME_ORGSTRUCTURE("domain_org_structure", "DOAMIN组织架构表"),
    TABLE_NAME_BASICINFO("domain_basic_info", "DOAMIN基础信息表"),
    TABLE_NAME_EMPLOYEEINFO("domain_employee_info", "DOAMIN员工信息表"),
    TABLE_NAME_MAXTIME("domain_max_time", "时间表");

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableMessage() {
        return tableMessage;
    }

    public void setTableMessage(String tableMessage) {
        this.tableMessage = tableMessage;
    }

    TableNameEnum(String tableName, String tableMessage) {
        this.tableName = tableName;
        this.tableMessage = tableMessage;
    }

    private String tableName;

    private String tableMessage;


}
