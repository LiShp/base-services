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
import java.util.Date;

/**
 * 表名：domain_org_structure
 * @author gwx
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel("")
public class DomainOrgStructureVO extends AutoIncrementKeyBaseDomain<Long> {

    /**
     * 部门名称对应商旅deptName部门名称
     */
    @Column(name = "`group_name`")
    @ApiModelProperty("部门名称对应商旅deptName部门名称")
    private String groupName;


}