package com.gw.domain_hr.entity;

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
 * 表名：domain_employee_Info
*/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("")
@Table(name = "`domain_employee_Info`")
public class DomainEmployeeInfo extends AutoIncrementKeyBaseDomain<Long> {
    /**
     * 工号(系统自动生成)
     */
    @Column(name = "`personnel_no`")
    @ApiModelProperty("工号(系统自动生成)")
    private String personnelNo;

    /**
     * 曾用名
     */
    @Column(name = "`once_name`")
    @ApiModelProperty("曾用名")
    private String onceName;

    /**
     * 性别
     */
    @Column(name = "`sex`")
    @ApiModelProperty("性别")
    private Boolean sex;

    /**
     * 婚姻状况：1未婚 2已婚 3离异 4丧偶
     */
    @Column(name = "`marriage_status`")
    @ApiModelProperty("婚姻状况：1未婚 2已婚 3离异 4丧偶")
    private Boolean marriageStatus;

    /**
     * 出生日期
     */
    @Column(name = "`birth_date`")
    @ApiModelProperty("出生日期")
    private Date birthDate;

    /**
     * 籍贯
     */
    @Column(name = "`native_place`")
    @ApiModelProperty("籍贯")
    private String nativePlace;

    /**
     * 户口所在地
     */
    @Column(name = "`register_residence`")
    @ApiModelProperty("户口所在地")
    private String registerResidence;

    /**
     * 居住类型：1定居 2租住
     */
    @Column(name = "`dwelling_type`")
    @ApiModelProperty("居住类型：1定居 2租住")
    private Boolean dwellingType;

    /**
     * 现居住地
     */
    @Column(name = "`dwelling`")
    @ApiModelProperty("现居住地")
    private String dwelling;

    /**
     * 体重
     */
    @Column(name = "`weight`")
    @ApiModelProperty("体重")
    private String weight;

    /**
     * 身高
     */
    @Column(name = "`height`")
    @ApiModelProperty("身高")
    private String height;

    /**
     * 血型:1A型 2B型 3AB型 4O型 5不详
     */
    @Column(name = "`blood_type`")
    @ApiModelProperty("血型:1A型 2B型 3AB型 4O型 5不详")
    private Boolean bloodType;

    /**
     * 政治面貌：1中国共产党党员 2共青团员 3群众
     */
    @Column(name = "`polity`")
    @ApiModelProperty("政治面貌：1中国共产党党员 2共青团员 3群众")
    private Boolean polity;

    /**
     * 民族
     */
    @Column(name = "`natives`")
    @ApiModelProperty("民族")
    private Integer natives;

    /**
     * 学历
     */
    @Column(name = "`diploma`")
    @ApiModelProperty("学历")
    private Integer diploma;

    /**
     * 专业
     */
    @Column(name = "`professional`")
    @ApiModelProperty("专业")
    private String professional;

    /**
     * 毕业学校
     */
    @Column(name = "`graduation_school`")
    @ApiModelProperty("毕业学校")
    private String graduationSchool;

    /**
     * 毕业日期
     */
    @Column(name = "`graduation_date`")
    @ApiModelProperty("毕业日期")
    private Date graduationDate;

    /**
     * 英语等级
     */
    @Column(name = "`english_ability`")
    @ApiModelProperty("英语等级")
    private Integer englishAbility;

    /**
     * 计算机等级
     */
    @Column(name = "`computer_ability`")
    @ApiModelProperty("计算机等级")
    private Integer computerAbility;

    /**
     * 联系方式
     */
    @Column(name = "`mobile`")
    @ApiModelProperty("联系方式")
    private String mobile;

    /**
     * 家庭电话
     */
    @Column(name = "`home_phone`")
    @ApiModelProperty("家庭电话")
    private String homePhone;

    /**
     * 邮箱
     */
    @Column(name = "`email`")
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 职称
     */
    @Column(name = "`post_title`")
    @ApiModelProperty("职称")
    private Integer postTitle;

    /**
     * 所在科室
     */
    @Column(name = "`group_id`")
    @ApiModelProperty("所在科室")
    private Integer groupId;

    /**
     * 工作类型
     */
    @Column(name = "`work_type`")
    @ApiModelProperty("工作类型")
    private Integer workType;

    /**
     * 职务
     */
    @Column(name = "`duty`")
    @ApiModelProperty("职务")
    private Integer duty;

    /**
     * 职类
     */
    @Column(name = "`position_type`")
    @ApiModelProperty("职类")
    private Integer positionType;

    /**
     * 职系
     */
    @Column(name = "`position_serial`")
    @ApiModelProperty("职系")
    private Integer positionSerial;

    /**
     * 职位
     */
    @Column(name = "`position`")
    @ApiModelProperty("职位")
    private Integer position;

    /**
     * 职级
     */
    @Column(name = "`position_level`")
    @ApiModelProperty("职级")
    private Integer positionLevel;

    /**
     * 入厂日期
     */
    @Column(name = "`join_date`")
    @ApiModelProperty("入厂日期")
    private Date joinDate;

    /**
     * 人员状态：1在职 2离职
     */
    @Column(name = "`personnel_status`")
    @ApiModelProperty("人员状态：1在职 2离职")
    private Boolean personnelStatus;

    /**
     * 是否外籍人：0否 1是
     */
    @Column(name = "`foregin`")
    @ApiModelProperty("是否外籍人：0否 1是")
    private Boolean foregin;

    /**
     * 人员类型
     */
    @Column(name = "`person_type`")
    @ApiModelProperty("人员类型")
    private Integer personType;

    /**
     * 工作所在地
     */
    @Column(name = "`work_address`")
    @ApiModelProperty("工作所在地")
    private Integer workAddress;

    /**
     * 离职原因
     */
    @Column(name = "`dismission_reason`")
    @ApiModelProperty("离职原因")
    private Integer dismissionReason;

    /**
     * 离职类型
     */
    @Column(name = "`dismission_type`")
    @ApiModelProperty("离职类型")
    private Integer dismissionType;

    /**
     * 离职日期
     */
    @Column(name = "`dismission_date`")
    @ApiModelProperty("离职日期")
    private Date dismissionDate;

    /**
     * 职位编码
     */
    @Column(name = "`position_code`")
    @ApiModelProperty("职位编码")
    private String positionCode;

    /**
     * 岗位实践阶段
     */
    @Column(name = "`position_step`")
    @ApiModelProperty("岗位实践阶段")
    private Integer positionStep;

    /**
     * 细分岗位
     */
    @Column(name = "`position_son`")
    @ApiModelProperty("细分岗位")
    private String positionSon;

    /**
     * 讲师级别
     */
    @Column(name = "`teach_level`")
    @ApiModelProperty("讲师级别")
    private Integer teachLevel;

    /**
     * 职务等级：0员工级 10 主管级 20科级 30部级 40副总级 50总经理  99高层
     */
    @Column(name = "`duty_level`")
    @ApiModelProperty("职务等级：0员工级 10 主管级 20科级 30部级 40副总级 50总经理  99高层")
    private Integer dutyLevel;

    /**
     *  证件类型：0身份证 1护照 2学生证 3军官证 4 驾驶证 5回乡证 6台胞证 7港澳通 行证 8台湾通行证 9士兵证 10临时身份证 11户口簿 12警官证 13出生证明 14出生日期 15外国人久居留证 16国际海员证 99其他
     */
    @Column(name = "`card_type`")
    @ApiModelProperty(" 证件类型：0身份证 1护照 2学生证 3军官证 4 驾驶证 5回乡证 6台胞证 7港澳通 行证 8台湾通行证 9士兵证 10临时身份证 11户口簿 12警官证 13出生证明 14出生日期 15外国人久居留证 16国际海员证 99其他")
    private Integer cardType;

    /**
     * 证件姓名
     */
    @Column(name = "`card_name`")
    @ApiModelProperty("证件姓名")
    private String cardName;

    /**
     * 身份证号码
     */
    @Column(name = "`card_no`")
    @ApiModelProperty("身份证号码")
    private String cardNo;

    /**
     * 是否删除：0否 1是
     */
    @Column(name = "`delete`")
    @ApiModelProperty("是否删除：0否 1是")
    private Boolean delete;

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
}