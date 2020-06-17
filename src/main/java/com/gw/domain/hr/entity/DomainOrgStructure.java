package com.gw.domain.hr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.cloud.common.base.entity.AutoIncrementKeyBaseDomain;
import com.gw.cloud.common.base.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 表名：domain_org_structure
 * @author zoujialiang
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("")
@Table(name = "`domain_org_structure`")
public class DomainOrgStructure extends AutoIncrementKeyBaseDomain<Long> {

    /**
     * 部门名称对应商旅deptName部门名称
     */
    @Column(name = "`group_name`")
    @ApiModelProperty("部门名称对应商旅deptName部门名称")
    private String groupName;

    /**
     * 父节点编号对应商旅superDeptNO上级部门ID
     */
    @Column(name = "`parent_id`")
    @ApiModelProperty("父节点编号对应商旅superDeptNO上级部门ID")
    private Integer parentId;

    /**
     * 显示顺序对应商旅sno部门排序序号
     */
    @Column(name = "`show_order`")
    @ApiModelProperty("显示顺序对应商旅sno部门排序序号")
    private Integer showOrder;

    /**
     * 所在等级
     */
    @Column(name = "`level`")
    @ApiModelProperty("所在等级")
    private Integer level;
    /**
     * 子节点数量
     */
    @Column(name = "`child_count`")
    @ApiModelProperty("子节点数量")
    private Integer childCount;
    /**
     * 部门电话
     */
    @Column(name = "`group_tel`")
    @ApiModelProperty("部门电话")
    private String groupTel;

    /**
     * 所在区域(参照表sys_filedvalue：v_f_key=G_Address)
     */
    @Column(name = "`address`")
    @ApiModelProperty("所在区域(参照表sys_filedvalue：v_f_key=G_Address)")
    private Integer address;

    /**
     * 单位id
     */
    @Column(name = "`unit_id`")
    @ApiModelProperty("单位id")
    private Integer unitId;

    /**
     * 单位名称
     */
    @Column(name = "`unit_name`")
    @ApiModelProperty("单位名称")
    private String unitName;

    /**
     * 部门id
     */
    @Column(name = "`department_id`")
    @ApiModelProperty("部门id")
    private Integer departmentId;

    /**
     * 部门名称
     */
    @Column(name = "`department_name`")
    @ApiModelProperty("部门名称")
    private String departmentName;

    /**
     * 科室id
     */
    @Column(name = "`team_id`")
    @ApiModelProperty("科室id")
    private Integer teamId;

    /**
     * 科室名称
     */
    @Column(name = "`team_name`")
    @ApiModelProperty("科室名称")
    private String teamName;


    /**
     * 业务模块:1整车业务 2零部件业务 3地产板块 4出行板块 5教育板块 6金融板块 7其他
     */
    @Column(name = "`module_id`")
    @ApiModelProperty("业务模块:1整车业务 2零部件业务 3地产板块 4出行板块 5教育板块 6金融板块 7其他")
    private Integer moduleId;

    /**
     * 业务模块:1整车业务 2零部件业务 3地产板块 4出行板块 5教育板块 6金融板块 7其他
     */
    @Column(name = "`module_name`")
    @ApiModelProperty("业务模块:1整车业务 2零部件业务 3地产板块 4出行板块 5教育板块 6金融板块 7其他")
    private String moduleName;

    /**
     * 业务子模块： 整车业务：11研发中心 12生计中心 13制造中心 14营销中心 15职能部门 16品牌公司 零部件业务：21蜂巢 22精工 23曼德 24诺博
     */
    @Column(name = "`sub_module_id`")
    @ApiModelProperty("业务子模块： 整车业务：11研发中心 12生计中心 13制造中心 14营销中心 15职能部门 16品牌公司 零部件业务：21蜂巢 22精工 23曼德 24诺博")
    private String subModuleId;

    /**
     * 业务子模块： 整车业务：11研发中心 12生计中心 13制造中心 14营销中心 15职能部门 16品牌公司 零部件业务：21蜂巢 22精工 23曼德 24诺博
     */
    @Column(name = "`sub_module_name`")
    @ApiModelProperty("业务子模块： 整车业务：11研发中心 12生计中心 13制造中心 14营销中心 15职能部门 16品牌公司 零部件业务：21蜂巢 22精工 23曼德 24诺博")
    private String subModuleName;

    /**
     * 组织等级(0集团级别 10公司级 20部门级 30科室级)
     */
    @Column(name = "`grade`")
    @ApiModelProperty("组织等级(0集团级别 10公司级 20部门级 30科室级)")
    private Integer grade;

    /**
     * 当前组织直接领导工号
     */
    @Column(name = "`direct_leader`")
    @ApiModelProperty("当前组织直接领导工号")
    private String directLeader;

    /**
     * 组织负责人姓名
     */
    @Column(name = "`direct_leader_name`")
    @ApiModelProperty("组织负责人姓名")
    private String directLeaderName;


    /**
     * 备注信息
     */
    @Column(name = "`remark`")
    @ApiModelProperty("备注信息")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date createTime;

    /**
     * 创建人(工号)
     */
    @Column(name = "`create_personnel_no`")
    @ApiModelProperty("创建人(工号)")
    private String createPersonnelNo;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date updateTime;

    /**
     * 更新人(工号)
     */
    @Column(name = "`update_personnel_no`")
    @ApiModelProperty("更新人(工号)")
    private String updatePersonnelNo;

    /**
     * 是否删除：0否 1是
     */
    @Column(name = "`delete_flag`")
    @ApiModelProperty("是否删除：0否 1是")
    private Integer deleteFlag;

}