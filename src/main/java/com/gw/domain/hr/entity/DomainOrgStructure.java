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
     * sys_group表的groupId
     */
    @Column(name = "`group_code`")
    @ApiModelProperty("sys_group表的groupId")
    private Integer groupCode;


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
     * 指定审批人(默认为0)旧表为 personnel_id
     */
    @Column(name = "`personnel_no`")
    @ApiModelProperty("指定审批人(默认为0)旧表为 personnel_id")
    private String personnelNo;

    /**
     * 板块(子模块)
     */
    @Column(name = "`company`")
    @ApiModelProperty("板块(子模块)")
    private String company;

    /**
     * 业务模块:1整车业务 2零部件业务 3地产板块 4出行板块 5教育板块 6金融板块 7其他
     */
    @Column(name = "`module`")
    @ApiModelProperty("业务模块:1整车业务 2零部件业务 3地产板块 4出行板块 5教育板块 6金融板块 7其他")
    private String module;

    /**
     * 业务子模块： 整车业务：11研发中心 12生计中心 13制造中心 14营销中心 15职能部门 16品牌公司 零部件业务：21蜂巢 22精工 23曼德 24诺博
     */
    @Column(name = "`sub_module`")
    @ApiModelProperty("业务子模块： 整车业务：11研发中心 12生计中心 13制造中心 14营销中心 15职能部门 16品牌公司 零部件业务：21蜂巢 22精工 23曼德 24诺博")
    private String subModule;

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
     * 部门最高领导工号
     */
    @Column(name = "`dept_top_leader`")
    @ApiModelProperty("部门最高领导工号")
    private String deptTopLeader;

    /**
     * 单位最高领导工号
     */
    @Column(name = "`unit_top_leader`")
    @ApiModelProperty("单位最高领导工号")
    private String unitTopLeader;

    /**
     * 启用时间
     */
    @Column(name = "`enable_time`")
    @ApiModelProperty("启用时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date enableTime;

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
    private Boolean deleteFlag;

    @Override
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}