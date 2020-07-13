package com.gw.domain.hr.entity.hr;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.cloud.common.base.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author weixin
 * 表名：o_group
*/
@Getter
@Setter
@ToString
@ApiModel("")
@Table(name = "`o_group`")
public class Group implements Serializable {

    @Id
    @Column(name = "`group_id`")
    protected Long id;

    /**
     * 部门名称对应商旅deptname部门名称
     */
    @Column(name = "`group_name`")
    @ApiModelProperty("部门名称对应商旅deptname部门名称")
    private String groupName;

    /**
     * 父节点编号对应商旅superdeptno上级部门id
     */
    @Column(name = "`parent_id`")
    @ApiModelProperty("父节点编号对应商旅superdeptno上级部门id")
    private Integer parentId;

    /**
     * 显示顺序对应商旅sno 部门排序序号
     */
    @Column(name = "`show_order`")
    @ApiModelProperty("显示顺序对应商旅sno 部门排序序号")
    private Integer showOrder;

    /**
     * 所在等级
     */
    @Column(name = "`group_level`")
    @ApiModelProperty("所在等级")
    private Integer level;

    /**
     * 子节点数量
     */
    @Column(name = "`child_count`")
    @ApiModelProperty("子节点数量")
    private Integer childCount;

    /**
     * 备注信息
     */
    @Column(name = "`remark`")
    @ApiModelProperty("备注信息")
    private String remark;

    /**
     * 部门电话
     */
    @Column(name = "`department_tel`")
    @ApiModelProperty("部门电话")
    private String groupTel;

    /**
     * 所属区域(保定 天津 徐水等)字典表
     */
    @Column(name = "`address`")
    @ApiModelProperty("所属区域(保定 天津 徐水等)字典表")
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
     * 所属板块id
     */
    @Column(name = "`module_id`")
    @ApiModelProperty("所属板块id")
    private Integer moduleId;

    /**
     * 所属板块名称
     */
    @Column(name = "`module_name`")
    @ApiModelProperty("所属板块名称")
    private String moduleName;

    /**
     * 所属子版块id
     */
    @Column(name = "`sub_module_id`")
    @ApiModelProperty("所属子版块id")
    private Integer subModuleId;

    /**
     * 所属子版块名称
     */
    @Column(name = "`sub_module_name`")
    @ApiModelProperty("所属子版块名称")
    private String subModuleName;

    /**
     * 组织类型（0集团级别 10公司级 20部门级 30科室级）
     */
    @Column(name = "`grade`")
    @ApiModelProperty("组织类型（0集团级别 10公司级 20部门级 30科室级）")
    private Integer grade;

    /**
     * 当前组织直接领导工号
     */
    @Column(name = "`direct_leader_no`")
    @ApiModelProperty("当前组织直接领导工号")
    private String directLeader;

    /**
     * 组织负责人姓名
     */
    @Column(name = "`direct_leader_name`")
    @ApiModelProperty("组织负责人姓名")
    private String directLeaderName;

    /**
     * 是否删除（0：否 1：是）对应商旅status状态（1:启用 0:禁用）
     */
    @Column(name = "`is_delete`")
    @ApiModelProperty("是否删除（0：否 1：是）对应商旅status状态（1:启用 0:禁用）")
    private Integer deleteFlag;

    /**
     * 创建人工号
     */
    @Column(name = "`create_user_number`")
    @ApiModelProperty("")
    private String createPersonnelNo;

    /**
     * 创建人
     */
    @Column(name = "`create_user`")
    @ApiModelProperty("创建人")
    private String createUser;

    /**
     * 创建日期
     */
    @Column(name = "`create_time`")
    @ApiModelProperty("创建日期")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date createTime;

    /**
     * 修改人工号
     */
    @Column(name = "`update_user_number`")
    @ApiModelProperty("")
    private String updatePersonnelNo;

    /**
     * 修改人
     */
    @Column(name = "`update_user`")
    @ApiModelProperty("修改人")
    private String updateUser;

    /**
     * 修改时间
     */
    @Column(name = "`update_time`")
    @ApiModelProperty("修改时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date updateTime;
}