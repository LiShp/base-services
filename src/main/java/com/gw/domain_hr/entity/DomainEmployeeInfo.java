package com.gw.domain_hr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.cloud.common.base.entity.AutoIncrementKeyBaseDomain;
import com.gw.cloud.common.base.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.*;

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
@Table(name = "`domain_employee_info`")
public class DomainEmployeeInfo extends AutoIncrementKeyBaseDomain<Long> {

    /**
     * 工号(系统自动生成)^[A-Za-z0-9]+$
     */
    @Column(name = "`personnel_no`")
    @ApiModelProperty("工号(系统自动生成)")
    @NotNull(message = "工号不能为null")
    @NotEmpty(message = "工号不能为空字符串")
    @Size(max = 10, min = 9, message = "工号长度为9-10")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "工号只能包含大写字母A-Z和数字0-9")
    private String personnelNo;

    /**
     * 曾用名
     */
    @Column(name = "`once_name`")
    @ApiModelProperty("曾用名")
    private String onceName;

    /**
     * 姓名
     */
    @Column(name = "`name`")
    @ApiModelProperty("姓名")
    @NotNull(message = "姓名不能为null")
    @NotEmpty(message = "姓名不能为空字符串")
    private String name;

    /**
     * 性别
     */
    @Column(name = "`sex`")
    @ApiModelProperty("性别")
    @NotNull(message = "性别不能为null")
    @Max(value = 2, message = "性别只能为1或者2：1女 2男")
    @Min(value = 1, message = "性别只能为1或者2：1女 2男")
    private Integer sex;

    /**
     * 婚姻状况：1未婚 2已婚 3离异 4丧偶
     */
    @Column(name = "`marriage_status`")
    @ApiModelProperty("婚姻状况：1未婚 2已婚 3离异 4丧偶")
    @NotNull(message = "性别不能为null")
    @Max(value = 4, message = "婚姻状况只能为：1未婚 2已婚 3离异 4丧偶")
    @Min(value = 1, message = "婚姻状况只能为：1未婚 2已婚 3离异 4丧偶")
    private Integer marriageStatus;

    /**
     * 出生日期
     */
    @Column(name = "`birth_date`")
    @ApiModelProperty("出生日期")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATE, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    @NotNull(message = "出生日期不能为null")
    private Date birthDate;

    /**
     * 籍贯
     */
    @Column(name = "`native_place`")
    @ApiModelProperty("籍贯")
    @NotNull(message = "籍贯不能为null")
    @NotEmpty(message = "籍贯不能为空字符串")
    private String nativePlace;

    /**
     * 户口所在地
     */
    @Column(name = "`register_residence`")
    @ApiModelProperty("户口所在地")
    @NotNull(message = "户口所在地不能为null")
    @NotEmpty(message = "户口所在地不能为空字符串")
    private String registerResidence;

    /**
     * 居住类型：1定居 2租住
     */
    @Column(name = "`dwelling_type`")
    @ApiModelProperty("居住类型：1定居 2租住")
    @NotNull(message = "户口所在地不能为null")
    @Max(value = 2, message = "居住类型只能为1或者2：1定居 2租住")
    @Min(value = 1, message = "居住类型只能为1或者2：1定居 2租住")
    private Integer dwellingType;

    /**
     * 现居住地
     */
    @Column(name = "`dwelling`")
    @ApiModelProperty("现居住地")
    @NotNull(message = "户口所在地不能为null")
    @NotEmpty(message = "户口所在地不能为空字符串")
    private String dwelling;

    /**
     * 体重
     */
    @Column(name = "`weight`")
    @ApiModelProperty("体重")
    @NotNull(message = "体重不能为null")
    @NotEmpty(message = "体重不能为空字符串")
    private String weight;

    /**
     * 身高
     */
    @Column(name = "`height`")
    @ApiModelProperty("身高")
    @NotNull(message = "身高不能为null")
    @NotEmpty(message = "身高不能为空字符串")
    private String height;

    /**
     * 血型:1A型 2B型 3AB型 4O型 5不详
     */
    @Column(name = "`blood_type`")
    @ApiModelProperty("血型:1A型 2B型 3AB型 4O型 5不详")
    @NotNull(message = "血型不能为null")
    @Max(value = 5, message = "血型只能为1-5：1A型 2B型 3AB型 4O型 5不详")
    @Min(value = 1, message = "血型只能为1-5：1A型 2B型 3AB型 4O型 5不详")
    private Integer bloodType;

    /**
     * 政治面貌：1中国共产党党员 2共青团员 3群众
     */
    @Column(name = "`polity`")
    @ApiModelProperty("政治面貌：1中国共产党党员 2共青团员 3群众")
    @Max(value = 3, message = "政治面貌只能为1-3：1中国共产党党员 2共青团员 3群众")
    @Min(value = 1, message = "政治面貌只能为1-3：1中国共产党党员 2共青团员 3群众")
    private Integer polity;

    /**
     * 民族
     */
    @Column(name = "`natives`")
    @ApiModelProperty("民族")
    @Max(value = 59, message = "民族最大值为59")
    @Min(value = -1, message = "民族最小值为-1")
    private Integer natives;

    /**
     * 学历
     */
    @Column(name = "`diploma`")
    @ApiModelProperty("学历")
    @Max(value = 9, message = "学历最大值为9")
    @Min(value = -1, message = "学历最小值为-1")
    private Integer diploma;

    /**
     * 专业
     */
    @Column(name = "`professional`")
    @ApiModelProperty("专业")
    @NotNull(message = "专业不能为null")
    @NotEmpty(message = "专业不能为空字符串")
    private String professional;

    /**
     * 毕业学校
     */
    @Column(name = "`graduation_school`")
    @ApiModelProperty("毕业学校")
    @NotNull(message = "毕业学校不能为null")
    @NotEmpty(message = "毕业学校不能为空字符串")
    private String graduationSchool;

    /**
     * 毕业日期
     */
    @Column(name = "`graduation_date`")
    @ApiModelProperty("毕业日期")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATE, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    @NotNull(message = "毕业日期不能为null")
    private Date graduationDate;

    /**
     * 英语等级
     */
    @Column(name = "`english_ability`")
    @ApiModelProperty("英语等级")
    @Max(value = 9, message = "英语等级最大值为9")
    @Min(value = -1, message = "英语等级最小值为-1")
    private Integer englishAbility;

    /**
     * 计算机等级
     */
    @Column(name = "`computer_ability`")
    @ApiModelProperty("计算机等级")
    @Max(value = 5, message = "计算机等级最大值为5")
    @Min(value = -1, message = "计算机等级最小值为-1")
    private Integer computerAbility;

    /**
     * 联系方式
     */
    @Column(name = "`mobile`")
    @ApiModelProperty("联系方式")
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "联系方式输入格式有误，请检查")
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
    @Max(value = 6, message = "工作类型最大值为6")
    @Min(value = -1, message = "工作类型最小值-1")
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
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATE, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date joinDate;

    /**
     * 人员状态：1在职 2离职
     */
    @Column(name = "`personnel_status`")
    @ApiModelProperty("人员状态：1在职 2离职")
    @Max(value = 2, message = "人员状态只能为1或者2：1在职 2离职")
    @Min(value = 1, message = "人员状态只能为1或者2：1在职 2离职")
    private Integer personnelStatus;

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
    @Max(value = 5, message = "人员类型最大值为5")
    @Min(value = -1, message = "人员类型最小值为-1")
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
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATE, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
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
     * 证件类型：0身份证 1护照 2学生证 3军官证 4 驾驶证 5回乡证 6台胞证 7港澳通 行证 8台湾通行证 9士兵证 10临时身份证 11户口簿 12警官证 13出生证明 14出生日期 15外国人久居留证 16国际海员证 99其他
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
    @NotNull(message = "创建人(工号)不能为null")
    @NotEmpty(message = "创建人(工号)不能为空字符串")
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
    @NotNull(message = "更新人(工号)不能为null")
    @NotEmpty(message = "更新人(工号)不能为空字符串")
    private String updatePersonnelNo;

    /**
     * 是否删除：0否 1是
     */
    @Column(name = "`delete_flag`")
    @ApiModelProperty("是否删除：0否 1是")
    private Boolean delete_flag;

    public String getPersonnelNo() {
        return personnelNo;
    }

    public void setPersonnelNo(String personnelNo) {
        this.personnelNo = personnelNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}