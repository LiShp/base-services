package com.gw.domain.hr.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 表名：domain_org_structure
 * @author zoujialiang
 */
@Getter
@Setter
@ToString
@ApiModel("组织节点")
public class NodeVO implements Serializable {

    private static final long serialVersionUID = 1495836611159931449L;
    /**
     * 部门名称对应商旅deptName部门名称
     */

    @ApiModelProperty("节点名称：组织/员工名称")
    private String nodeName;

    @ApiModelProperty("节点值：组织ID/员工工号")
    private String nodeValue;

    @ApiModelProperty("节点类型：0组织、1员工")
    private Integer type;

    @ApiModelProperty("子节点")
    private List<NodeVO> nodeVO;


}