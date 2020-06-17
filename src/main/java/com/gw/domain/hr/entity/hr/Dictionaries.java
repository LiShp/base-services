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
 * 表名：o_dictionaries
*/
@Getter
@Setter
@ToString
@ApiModel("")
@Table(name = "`o_dictionaries`")
public class Dictionaries  {

    @Id
    @Column(name = "`value_id`")
    private Long id;


    @Column(name = "`language`")
    @ApiModelProperty("")
    private String language;

    /**
     * 数据值编号
     */
    @Column(name = "`value_no`")
    @ApiModelProperty("数据值编号")
    private Integer valueNo;

    /**
     * 数据值内容
     */
    @Column(name = "`value_content`")
    @ApiModelProperty("数据值内容")
    private String valueContent;

    /**
     * 数据类型
     */
    @Column(name = "`value_type`")
    @ApiModelProperty("数据类型")
    private String valueType;

    /**
     * 数据类型名称
     */
    @Column(name = "`value_type_name`")
    @ApiModelProperty("数据类型名称")
    private String valueTypeName;

    /**
     * 父节点id
     */
    @Column(name = "`parent_id`")
    @ApiModelProperty("父节点id")
    private Integer parentId;

    /**
     * 是否删除
     */
    @Column(name = "`is_delete`")
    @ApiModelProperty("是否删除")
    private Integer isDelete;

    /**
     * 备注信息
     */
    @Column(name = "`remark`")
    @ApiModelProperty("备注信息")
    private String remark;

    /**
     * 是否it维护（0：it维护  1用户维护）
     */
    @Column(name = "`is_it_user`")
    @ApiModelProperty("是否it维护（0：it维护  1用户维护）")
    private Integer isItUser;

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
     * 修改人
     */
    @Column(name = "`update_user`")
    @ApiModelProperty("修改人")
    private String updateUser;

    /**
     * 修改日期
     */
    @Column(name = "`update_time`")
    @ApiModelProperty("修改日期")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    private Date updateTime;
}