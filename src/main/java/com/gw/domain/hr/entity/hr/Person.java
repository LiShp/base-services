package com.gw.domain.hr.entity.hr;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.cloud.common.base.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author weixin
 * 表名：p_person
*/
@Getter
@Setter
@ToString
@ApiModel("")
@Table(name = "`p_person`")
public class Person  {

    @Column(name = "`person_id`")
    protected Long personId;
    /**
     * 工号
     */
    @Column(name = "`person_number`")
    @ApiModelProperty("工号")
    private String personnelNo;

    /**
     * 是否正式员工,2:非正式工,1:正式工.
     */
    @Column(name = "`is_formal`")
    @ApiModelProperty("是否正式员工,2:非正式工,1:正式工.")
    private Integer isFormal;

    /**
     * 是否外籍,0:否,1:是
     */
    @Column(name = "`is_foreign`")
    @ApiModelProperty("是否外籍,0:否,1:是")
    private Integer isForeign;

    /**
     * 国籍,字典表维护
     */
    @Column(name = "`country`")
    @ApiModelProperty("国籍,字典表维护")
    private Integer country;

    /**
     * 证件类型,字典表维护
     */
    @Column(name = "`certificate_type`")
    @ApiModelProperty("证件类型,字典表维护")
    private Integer certificateType;

    /**
     * 证件号码
     */
    @Column(name = "`certificate_number`")
    @ApiModelProperty("证件号码")
    private String certificateNumber;

    /**
     * 身份证号
     */
    @Column(name = "`id_card_number`")
    @ApiModelProperty("身份证号")
    private String idCardNumber;

    /**
     * 证件姓名
     */
    @Column(name = "`certificate_name`")
    @ApiModelProperty("证件姓名")
    private String certificateName;

    /**
     * 中文姓名
     */
    @Column(name = "`chinese_name`")
    @ApiModelProperty("中文姓名")
    private String name;

    /**
     * 曾用名
     */
    @Column(name = "`name_used`")
    @ApiModelProperty("曾用名")
    private String nameUsed;

    /**
     * 性别,1:男,2:女
     */
    @Column(name = "`sex`")
    @ApiModelProperty("性别,1:男,2:女")
    private Integer sex;

    /**
     * 婚姻状况,1:未婚,2:已婚,3:离异,4:丧偶
     */
    @Column(name = "`marriage`")
    @ApiModelProperty("婚姻状况,1:未婚,2:已婚,3:离异,4:丧偶")
    private Integer marriage;

    /**
     * 出生日期
     */
    @Column(name = "`birthday`")
    @ApiModelProperty("出生日期")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
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
    @Column(name = "`registered_residence`")
    @ApiModelProperty("户口所在地")
    private String registeredResidence;

    /**
     * 居住类型,1:定居,2:租住
     */
    @Column(name = "`live_type`")
    @ApiModelProperty("居住类型,1:定居,2:租住")
    private Integer liveType;

    /**
     * 现居住地
     */
    @Column(name = "`current_live_place`")
    @ApiModelProperty("现居住地")
    private String currentLivePlace;

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
     * 血型,0:不详,1:A型,2:B型,3:AB型,4:O型
     */
    @Column(name = "`blood_type`")
    @ApiModelProperty("血型,0:不详,1:A型,2:B型,3:AB型,4:O型")
    private Integer bloodType;

    /**
     * 政治面貌,1:中国共产党党员,2:共青团员,3:群众
     */
    @Column(name = "`political_affiliation`")
    @ApiModelProperty("政治面貌,1:中国共产党党员,2:共青团员,3:群众")
    private Integer politicalAffiliation;

    /**
     * 民族,字典表维护
     */
    @Column(name = "`nation`")
    @ApiModelProperty("民族,字典表维护")
    private Integer nation;

    /**
     * 健康状况,来源于入职系统
     */
    @Column(name = "`health`")
    @ApiModelProperty("健康状况,来源于入职系统")
    private String health;

    /**
     * 简历编号
     */
    @Column(name = "`resume_number`")
    @ApiModelProperty("简历编号")
    private String resumeNumber;

    /**
     * 户籍编号
     */
    @Column(name = "`house_number`")
    @ApiModelProperty("户籍编号")
    private String houseNumber;

    /**
     * 户籍所在地
     */
    @Column(name = "`dwelling_unit`")
    @ApiModelProperty("户籍所在地")
    private String dwellingUnit;

    /**
     * 工作区域,公司所在地,字典表维护
     */
    @Column(name = "`work_address`")
    @ApiModelProperty("工作区域,公司所在地,字典表维护")
    private Integer workAddress;

    /**
     * 学历,字典表维护,1:博士,2:硕士,3:本科,4:专科,5:高中,6:中专/中,7:初中,8:初中以下
     */
    @Column(name = "`diploma`")
    @ApiModelProperty("学历,字典表维护,1:博士,2:硕士,3:本科,4:专科,5:高中,6:中专/中,7:初中,8:初中以下")
    private Integer diploma;

    /**
     * 教育类型,字典表维护,1:统招,2:自考,3:成教,4:网络,5:夜校,6:公司大专班
     */
    @Column(name = "`education_type`")
    @ApiModelProperty("教育类型,字典表维护,1:统招,2:自考,3:成教,4:网络,5:夜校,6:公司大专班")
    private Integer educationType;

    /**
     * 招聘来源,字典表维护,1:校园招聘,2:普工招聘,3:有经验招聘,5:复职
     */
    @Column(name = "`recruitment_sources`")
    @ApiModelProperty("招聘来源,字典表维护,1:校园招聘,2:普工招聘,3:有经验招聘,5:复职")
    private Integer recruitmentSources;

    /**
     * 毕业学校
     */
    @Column(name = "`graduate_school`")
    @ApiModelProperty("毕业学校")
    private String graduateSchool;

    /**
     * 专业
     */
    @Column(name = "`professional`")
    @ApiModelProperty("专业")
    private String professional;

    /**
     * 毕业日期
     */
    @Column(name = "`graduate_time`")
    @ApiModelProperty("毕业日期")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date graduateTime;

    /**
     * 英语等级,0:无,1:专业八级,2:专业四级,3:八级,4:六级,5:四级,6:三级,7:雅思,8:托福,字典表维护
     */
    @Column(name = "`english_ability`")
    @ApiModelProperty("英语等级,0:无,1:专业八级,2:专业四级,3:八级,4:六级,5:四级,6:三级,7:雅思,8:托福,字典表维护")
    private Integer englishAbility;

    /**
     * 计算机等级,0:无,1:一级,2:二级,3:三级,4:四级,字典表维护
     */
    @Column(name = "`computer_ability`")
    @ApiModelProperty("计算机等级,0:无,1:一级,2:二级,3:三级,4:四级,字典表维护")
    private Integer computerAbility;

    /**
     * 其他证书
     */
    @Column(name = "`certificate`")
    @ApiModelProperty("其他证书")
    private String certificate;

    /**
     * 其他语言
     */
    @Column(name = "`other_language`")
    @ApiModelProperty("其他语言")
    private String otherLanguage;

    /**
     * 手机号码
     */
    @Column(name = "`phone_number`")
    @ApiModelProperty("手机号码")
    private String mobile;

    /**
     * 家庭联系电话
     */
    @Column(name = "`home_phone`")
    @ApiModelProperty("家庭联系电话")
    private String homePhone;

    /**
     * 邮箱
     */
    @Column(name = "`email`")
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * QQ
     */
    @Column(name = "`qq`")
    @ApiModelProperty("QQ")
    private String qq;

    /**
     * 职称,字典表维护
     */
    @Column(name = "`title`")
    @ApiModelProperty("职称,字典表维护")
    private Integer title;

    /**
     * 所在组织,组织表id
     */
    @Column(name = "`group_id`")
    @ApiModelProperty("所在组织,组织表id")
    private Integer groupId;

    /**
     * 用工类型,1:正式人员,2:聘用人员,3:返聘人员,4:实习人员,5:劳务派遣,字典表维护
     */
    @Column(name = "`work_type`")
    @ApiModelProperty("用工类型,1:正式人员,2:聘用人员,3:返聘人员,4:实习人员,5:劳务派遣,字典表维护")
    private Integer workType;

    /**
     * 职务,字典表维护,举例:董事长,本部长,总经理,科长
     */
    @Column(name = "`duty`")
    @ApiModelProperty("职务,字典表维护,举例:董事长,本部长,总经理,科长")
    private Integer duty;

    /**
     * 职类,字典表维护,举例:行政服务,售后服务,配件管理,供应链,信息技术
     */
    @Column(name = "`category`")
    @ApiModelProperty("职类,字典表维护,举例:行政服务,售后服务,配件管理,供应链,信息技术")
    private Integer category;

    /**
     * 职系,字典表维护,举例:管理层,产品企划,过程设计,制造技术,售后技术
     */
    @Column(name = "`grade`")
    @ApiModelProperty("职系,字典表维护,举例:管理层,产品企划,过程设计,制造技术,售后技术")
    private Integer grade;

    /**
     * 职位,字典表维护,举例:组长,班长,秘书,文员,董事长
     */
    @Column(name = "`position`")
    @ApiModelProperty("职位,字典表维护,举例:组长,班长,秘书,文员,董事长")
    private Integer position;

    /**
     * 职级,字典表维护,
     */
    @Column(name = "`rank`")
    @ApiModelProperty("职级,字典表维护,")
    private Integer rank;

    /**
     * 岗位编码
     */
    @Column(name = "`position_code`")
    @ApiModelProperty("岗位编码")
    private String positionCode;

    /**
     * 岗位实践阶段,字典表维护,1试用期 2实习期 3学习期 4定岗期 ,旧版数据库有 0:-1:
     */
    @Column(name = "`post_practice_stage`")
    @ApiModelProperty("岗位实践阶段,字典表维护,1试用期 2实习期 3学习期 4定岗期 ,旧版数据库有 0:-1:")
    private Integer postPracticeStage;

    /**
     * 细分岗位
     */
    @Column(name = "`subdivision_post`")
    @ApiModelProperty("细分岗位")
    private String subdivisionPost;

    /**
     * 职务等级,字典表维护,0职员 10主管 20科级 30部级 40经理级 50总裁级
     */
    @Column(name = "`duty_rank`")
    @ApiModelProperty("职务等级,字典表维护,0职员 10主管 20科级 30部级 40经理级 50总裁级")
    private Integer dutyRank;

    /**
     * 入厂日期
     */
    @Column(name = "`admission_date`")
    @ApiModelProperty("入厂日期")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date joinDate;

    /**
     * 人员状态,1:在职,2:离职
     */
    @Column(name = "`state`")
    @ApiModelProperty("人员状态,1:在职,2:离职")
    private Integer personnelStatus;

    /**
     * 直接领导id
     */
    @Column(name = "`direct_leader_id`")
    @ApiModelProperty("直接领导id")
    private Integer directLeaderId;

    /**
     * 考勤审核天数
     */
    @Column(name = "`approval_power_number`")
    @ApiModelProperty("考勤审核天数")
    private String approvalPowerNumber;

    /**
     * 银行卡号
     */
    @Column(name = "`bank_card`")
    @ApiModelProperty("银行卡号")
    private String bankCard;

    /**
     * 备注信息
     */
    @Column(name = "`remark`")
    @ApiModelProperty("备注信息")
    private String remark;

    /**
     * 个人照片在文件服务器的文件code
     */
    @Column(name = "`photo_file_code`")
    @ApiModelProperty("个人照片在文件服务器的文件code")
    private String photoFileCode;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date createTime;

    /**
     * 创建人id
     */
    @Column(name = "`create_user_id`")
    @ApiModelProperty("创建人id")
    private Integer createUserId;

    /**
     * 更新人id
     */
    @Column(name = "`update_user_id`")
    @ApiModelProperty("更新人id")
    private Integer updateUserId;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date updateTime;

    /**
     * 是否删除,0:否,1:是
     */
    @Column(name = "`is_delete`")
    @ApiModelProperty("是否删除,0:否,1:是")
    private Integer deleteFlag;

    /**
     * 人员备注,用于技术中心
     */
    @Column(name = "`person_remark`")
    @ApiModelProperty("人员备注,用于技术中心")
    private String personRemark;

    /**
     * 英语托业成绩,由托业考试成绩更新到这里
     */
    @Column(name = "`english_score`")
    @ApiModelProperty("英语托业成绩,由托业考试成绩更新到这里")
    private String englishScore;

    /**
     * 是否签订挑战者承诺书,0:不涉及,1:是,2:否
     */
    @Column(name = "`is_commitment`")
    @ApiModelProperty("是否签订挑战者承诺书,0:不涉及,1:是,2:否")
    private Integer isCommitment;

    /**
     * 创建人姓名
     */
    @Column(name = "`create_user_name`")
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /**
     * 员工所属机构(优才系统使用)
     */
    @Column(name = "`person_mechanism`")
    @ApiModelProperty("员工所属机构(优才系统使用)")
    private String personMechanism;

    /**
     * 招聘人(优才系统使用)
     */
    @Column(name = "`recruiter`")
    @ApiModelProperty("招聘人(优才系统使用)")
    private String recruiter;

    /**
     * 返校时间(优才系统使用)
     */
    @Column(name = "`back_school_time`")
    @ApiModelProperty("返校时间(优才系统使用)")
    private String backSchoolTime;

    /**
     * 英文名称
     */
    @Column(name = "`english_name`")
    @ApiModelProperty("英文名称")
    private String englishName;
}