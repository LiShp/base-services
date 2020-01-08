package com.gw.domain.hr.entity;


import com.gw.cloud.common.base.entity.AutoIncrementKeyBaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


import javax.persistence.Column;
import javax.persistence.Table;
import java.util.List;

/**
 * 表名：domain_basic_info
 * @author zoujialiang
*/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("")
@Table(name = "`domain_org_structure`")
public class DomainOrgStructureNode extends AutoIncrementKeyBaseDomain<Long> {
    /**
     * 父节点id
     */
    @Column(name = "`parent_id`")
    @ApiModelProperty("父节点id")
    private Integer parentId;

    /**
     * 中文名称
     */
    @Column(name = "`group_name`")
    @ApiModelProperty("中文名称")
    private String groupName;

    private List list;


}