package com.gw.domain.hr.rest;

import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain.common.util.DateUtil;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.entity.DomainOrgStructure;
import com.gw.domain.hr.entity.po.DomainEmpOrgRequestPO;
import com.gw.domain.hr.entity.vo.*;
import com.gw.domain.common.enums.EmployeeTypeEnum;
import com.gw.domain.hr.mapper.DomainOrgStructureMapper;
import com.gw.domain.hr.service.DomainEmployeeInfoService;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author zoujialiang
 */
@Api(value = "/domainemployeeinfo", tags = "员工信息", description = "员工信息")
@RestController
@RequestMapping("/domainemployeeinfo")
public class DomainEmployeeInfoController {

    protected GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    @Autowired
    private DomainEmployeeInfoService domainEmployeeInfoService;

    @Autowired
    private DomainOrgStructureMapper domainOrgStructureMapper;


    @ApiOperation(
            value = "【自定义】- 通过工号查询人员基础信息",
            notes = "【自定义】- 通过工号查询人员基础信息",
            httpMethod = "GET"
    )
    @GetMapping(value = "/employee/{personnelNo}")
    public JsonResult<DomainEmployeeInfoResponseVO> selectEmployeeInfoSingleByPersonnelNo(@PathVariable("personnelNo") String personnelNo) {
        JsonResult jsonResult;
        try {
            DomainEmpOrgRequestPO domainEmpOrgRequestPo = new DomainEmpOrgRequestPO();
            domainEmpOrgRequestPo.setPersonnelNo(personnelNo);
            domainEmpOrgRequestPo.setPersonnelStatus(1);

            DomainEmployeeInfoResponseVO domainEmployeeInfoResponseVO = domainEmployeeInfoService.employee(domainEmpOrgRequestPo);
            if(domainEmployeeInfoResponseVO !=null) {
                Example orgExample = new Example(DomainOrgStructure.class);
                orgExample.createCriteria().andEqualTo("id", domainEmployeeInfoResponseVO.getGroupId())
                .andEqualTo("deleteFlag", 0);
                DomainOrgStructure domainOrgStructure = domainOrgStructureMapper.selectOneByExample(orgExample);
                domainEmployeeInfoResponseVO.setParentId(domainOrgStructure.getParentId());
                domainEmployeeInfoResponseVO.setParentName(domainOrgStructure.getTeamName());
            }
            jsonResult = JsonResultUtil.createSuccessJsonResult(domainEmployeeInfoResponseVO);
        } catch (Exception var4) {
            this.logger.error("通过工号查询人员基础信息异常，" ,var4);
            jsonResult = JsonResultUtil.createFailureJsonResult("通过工号查询人员基础信息异常！ {0}", var4);
        }
        return jsonResult;
    }

    @ApiOperation(
            value = "【自定义】- 通过工号查询员工照片",
            notes = "【自定义】- 通过工号查询员工照片",
            httpMethod = "GET"
    )
    @GetMapping(value = "/employee/photo/{personnelNo}")
    public JsonResult<String> selectEmployeePhotoByPersonnelNo(@PathVariable("personnelNo") String personnelNo) {
        JsonResult jsonResult;
        try {
            String photoUrl = domainEmployeeInfoService.employeePhoto(personnelNo);
            jsonResult = JsonResultUtil.createSuccessJsonResult(photoUrl);
        } catch (Exception var4) {
            this.logger.error("通过工号查询员工照片，" ,var4);
            jsonResult = JsonResultUtil.createFailureJsonResult("通过工号查询员工照片！ {0}", var4);
        }
        return jsonResult;
    }

    @ApiOperation(
            value = "【自定义】- 通过工号列表批量查询人员基础信息",
            notes = "【自定义】- 通过工号列表批量查询人员基础信息",
            httpMethod = "POST"
    )
    @PostMapping(value = "/employees")
    public JsonResult<List<DomainEmployeeInfoResponseVO>> selectEmployeeInfoByPersonnelNos(@RequestBody @NotEmpty(message = "工号数组不能为空") List<String> personnelNo) {
        JsonResult jsonResult;
        try {
            Example example = new Example(DomainEmployeeInfo.class);
            example.createCriteria().andIn("personnelNo", personnelNo)
                    .andEqualTo("personnelStatus",1);

            List<DomainEmployeeInfo> domainEmployeeInfo = domainEmployeeInfoService.selectListByExample(example);
            List<DomainEmployeeInfoResponseVO> domainEmployeeInfoResponseVOList = DozerUtil.convert(domainEmployeeInfo, DomainEmployeeInfoResponseVO.class);
            for(DomainEmployeeInfoResponseVO info : domainEmployeeInfoResponseVOList) {
                Example orgExample = new Example(DomainOrgStructure.class);
                orgExample.createCriteria().andEqualTo("id", info.getGroupId())
                        .andEqualTo("deleteFlag", 0);
                DomainOrgStructure domainOrgStructure = domainOrgStructureMapper.selectOneByExample(orgExample);
                if(domainOrgStructure.getLevel()==5) {
                    Example parentExample = new Example(DomainOrgStructure.class);
                    parentExample.createCriteria().andEqualTo("id", domainOrgStructure.getParentId())
                            .andEqualTo("deleteFlag", 0);
                    domainOrgStructure = domainOrgStructureMapper.selectOneByExample(parentExample);
                }
                info.setParentId(domainOrgStructure.getTeamId());
                info.setParentName(domainOrgStructure.getGroupName());
            }
            jsonResult = JsonResultUtil.createSuccessJsonResult(domainEmployeeInfoResponseVOList);
        } catch (Exception var4) {
            this.logger.error("通过工号查询人员基础信息异常，" ,var4);
            jsonResult = JsonResultUtil.createFailureJsonResult("通过工号查询人员基础信息异常！ {0}", var4);
        }
        return jsonResult;
    }

    @ApiOperation(
            value = "【自定义】- 根据科室ID查询员工信息",
            notes = "【自定义】- 根据科室ID查询员工信息",
            httpMethod = "GET"
    )
    @GetMapping(value = "/employees/{groupId}")
    public JsonResult<QueryResult<EmployeeResponseVO>> employeesByGroup(@PathVariable("groupId") Integer groupId){
        JsonResult jsonResult;
        try {
            DomainEmployeeInfo domainEmployeeInfo = new DomainEmployeeInfo();
            domainEmployeeInfo.setDeleteFlag(Boolean.FALSE);
            domainEmployeeInfo.setPersonnelStatus(1);
            domainEmployeeInfo.setGroupId(groupId);
            domainEmployeeInfo.setIsFormal(EmployeeTypeEnum.FORMAL.getCode());
            List<DomainEmployeeInfo> domainEmployeeInfoList = domainEmployeeInfoService.selectList(domainEmployeeInfo);
            if(domainEmployeeInfoList.isEmpty()) {
                jsonResult = JsonResultUtil.createSuccessJsonResult(domainEmployeeInfoList);
            }else{
                List<EmployeeResponseVO> employeeResponseVOList = DozerUtil.convert(domainEmployeeInfoList, EmployeeResponseVO.class);
                jsonResult = JsonResultUtil.createSuccessJsonResult(employeeResponseVOList);
            }

        } catch (Exception var4) {
            this.logger.error("根据groupID查询员工信息" + var4.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("根据groupID查询员工信息{0}", var4);
        }
        return jsonResult;
    }

    @ApiOperation(
            value = "【自定义】- 查询员工信息列表",
            notes = "【自定义】- 查询员工信息列表",
            httpMethod = "GET"
    )
    @GetMapping(value = "/employees")
    public JsonResult<QueryResult<EmployeeOrgResponseVO>> employees(
            @ApiParam(name = "nameLike", value = "员工姓名") @RequestParam(required = false) String nameLike,
            @ApiParam(name = "groupId", value = "组织ID") @RequestParam(required = false) Integer groupId,
            @ApiParam(name = "groupName", value = "组织名称") @RequestParam(required = false) String groupName,
            @ApiParam(name = "unitId", value = "单位ID") @RequestParam(required = false) Integer unitId,
            @ApiParam(name = "unitName", value = "单位名称") @RequestParam(required = false) String unitName,
            @ApiParam(name = "departmentId", value = "部门ID") @RequestParam(required = false) Integer departmentId,
            @ApiParam(name = "departmentName", value = "部门名称") @RequestParam(required = false) String departmentName,
            @ApiParam(name = "personnelStatus", value = "是否在职,1在职、2离职") @RequestParam(required = false) Integer personnelStatus,
            @ApiParam(name = "createTime", value = "大于此创建时间的数据") @RequestParam(required = false)
            @Valid @Pattern(regexp= "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}:[0-9]{3}", message = "时间格式必须为2020-06-01 00:00:00:000格式")
                    String createTime,
            @ApiParam(name = "updateTime", value = "大于此更新时间的数据") @RequestParam(required = false) String updateTime,
            @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows){
        JsonResult jsonResult;
        try {

            DomainEmpOrgRequestPO domainEmpOrgRequestPo = new DomainEmpOrgRequestPO();
            domainEmpOrgRequestPo.setNameLike(nameLike);
            domainEmpOrgRequestPo.setGroupId(groupId);
            domainEmpOrgRequestPo.setGroupName(groupName);
            domainEmpOrgRequestPo.setUnitId(unitId);
            domainEmpOrgRequestPo.setUnitName(unitName);
            domainEmpOrgRequestPo.setDepartmentId(departmentId);
            domainEmpOrgRequestPo.setPersonnelStatus(personnelStatus);
            domainEmpOrgRequestPo.setDepartmentName(departmentName);

            domainEmpOrgRequestPo.setCreateTime(DateUtil.dateStrToDate(createTime, DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR));
            domainEmpOrgRequestPo.setUpdateTime(DateUtil.dateStrToDate(updateTime, DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR));

            QueryResult<EmployeeOrgResponseVO>  queryResult = domainEmployeeInfoService.employeeList(domainEmpOrgRequestPo,page, rows, EmployeeOrgResponseVO.class);

            return JsonResultUtil.createSuccessJsonResult(queryResult);
        } catch (Exception var4) {
            this.logger.error("根据groupID查询员工信息" + var4.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("根据groupID查询员工信息{0}", var4);
        }
        return jsonResult;
    }

    @ApiOperation(
            value = "【自定义】- 查询员工信息列表(全量)",
            notes = "【自定义】- 查询员工信息列表(全量)",
            httpMethod = "GET"
    )
    @GetMapping(value = "/employees_temp")
    public JsonResult<QueryResult<EmployeeResponseVO>> employeesAll(
            @ApiParam(name = "personnelStatus", value = "是否在职,1在职、2离职") @RequestParam(required = false) Integer personnelStatus,
            @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows){
        JsonResult jsonResult;
        try {

            DomainEmpOrgRequestPO domainEmpOrgRequestPo = new DomainEmpOrgRequestPO();
            domainEmpOrgRequestPo.setPersonnelStatus(personnelStatus);
            QueryResult<EmployeeResponseVO>  queryResult = domainEmployeeInfoService.employeeListAll(domainEmpOrgRequestPo,page, rows);

            return JsonResultUtil.createSuccessJsonResult(queryResult);
        } catch (Exception var4) {
            this.logger.error("根据groupID查询员工信息" + var4.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("根据groupID查询员工信息{0}", var4);
        }
        return jsonResult;
    }


    @ApiOperation(
            value = "【自定义】- 查询员工信息列表（包含身高/体重/学历信息）",
            notes = "【自定义】- 查询员工信息列表（包含身高/体重/学历信息）",
            httpMethod = "GET"
    )
    @GetMapping(value = "/employees/privacy")
    public JsonResult<QueryResult<EmployeeOrgPrivacyResponseResponseVO>> employeesPrivacy(
            @ApiParam(name = "nameLike", value = "员工姓名") @RequestParam(required = false) String nameLike,
            @ApiParam(name = "groupId", value = "组织ID") @RequestParam(required = false) Integer groupId,
            @ApiParam(name = "groupName", value = "组织名称") @RequestParam(required = false) String groupName,
            @ApiParam(name = "unitId", value = "单位ID") @RequestParam(required = false) Integer unitId,
            @ApiParam(name = "unitName", value = "单位名称") @RequestParam(required = false) String unitName,
            @ApiParam(name = "departmentId", value = "部门ID") @RequestParam(required = false) Integer departmentId,
            @ApiParam(name = "departmentName", value = "部门名称") @RequestParam(required = false) String departmentName,
            @ApiParam(name = "personnelStatus", value = "是否在职,1在职、2离职") @RequestParam(required = false) Integer personnelStatus,
            @ApiParam(name = "createTime", value = "大于此创建时间的数据") @RequestParam(required = false)
            @Valid @Pattern(regexp= "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}:[0-9]{3}", message = "时间格式必须为2020-06-01 00:00:00:000格式")
                    String createTime,
            @ApiParam(name = "updateTime", value = "大于此更新时间的数据") @RequestParam(required = false) String updateTime,
            @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows){
        JsonResult jsonResult;
        try {

            DomainEmpOrgRequestPO domainEmpOrgRequestPo = new DomainEmpOrgRequestPO();
            domainEmpOrgRequestPo.setNameLike(nameLike);
            domainEmpOrgRequestPo.setGroupId(groupId);
            domainEmpOrgRequestPo.setGroupName(groupName);
            domainEmpOrgRequestPo.setUnitId(unitId);
            domainEmpOrgRequestPo.setUnitName(unitName);
            domainEmpOrgRequestPo.setDepartmentId(departmentId);
            domainEmpOrgRequestPo.setPersonnelStatus(personnelStatus);
            domainEmpOrgRequestPo.setDepartmentName(departmentName);

            domainEmpOrgRequestPo.setCreateTime(DateUtil.dateStrToDate(createTime, DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR));
            domainEmpOrgRequestPo.setUpdateTime(DateUtil.dateStrToDate(updateTime, DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR));

            QueryResult<EmployeeOrgPrivacyResponseResponseVO>  queryResult = domainEmployeeInfoService.employeeList(domainEmpOrgRequestPo,page, rows, EmployeeOrgPrivacyResponseResponseVO.class);

            return JsonResultUtil.createSuccessJsonResult(queryResult);
        } catch (Exception var4) {
            this.logger.error("根据groupID查询员工信息" + var4.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("根据groupID查询员工信息{0}", var4);
        }
        return jsonResult;
    }

    @ApiOperation(
            value = "【自定义】- 查询员工工作经历信息列表",
            notes = "【自定义】- 查询员工工作经历信息列表",
            httpMethod = "GET"
    )
    @GetMapping(value = "/employees/work")
    public JsonResult<QueryResult<DomainWorkExperienceResponseVO>> employeesWork(
            @ApiParam(name = "nameLike", value = "员工姓名") @RequestParam(required = false) String nameLike,
            @ApiParam(name = "groupId", value = "组织ID") @RequestParam(required = false) Integer groupId,
            @ApiParam(name = "groupName", value = "组织名称") @RequestParam(required = false) String groupName,
            @ApiParam(name = "unitId", value = "单位ID") @RequestParam(required = false) Integer unitId,
            @ApiParam(name = "unitName", value = "单位名称") @RequestParam(required = false) String unitName,
            @ApiParam(name = "departmentId", value = "部门ID") @RequestParam(required = false) Integer departmentId,
            @ApiParam(name = "departmentName", value = "部门名称") @RequestParam(required = false) String departmentName,
            @ApiParam(name = "personnelStatus", value = "是否在职,1在职、2离职") @RequestParam(required = false) Integer personnelStatus,
            @ApiParam(name = "createTime", value = "大于此创建时间的数据") @RequestParam(required = false) String createTime,
            @ApiParam(name = "updateTime", value = "大于此更新时间的数据") @RequestParam(required = false) String updateTime,
            @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows){
        JsonResult jsonResult;
        try {

            DomainEmpOrgRequestPO domainEmpOrgRequestPo = new DomainEmpOrgRequestPO();
            domainEmpOrgRequestPo.setNameLike(nameLike);
            domainEmpOrgRequestPo.setGroupId(groupId);
            domainEmpOrgRequestPo.setGroupName(groupName);
            domainEmpOrgRequestPo.setUnitId(unitId);
            domainEmpOrgRequestPo.setUnitName(unitName);
            domainEmpOrgRequestPo.setDepartmentId(departmentId);
            domainEmpOrgRequestPo.setPersonnelStatus(personnelStatus);
            domainEmpOrgRequestPo.setDepartmentName(departmentName);

            domainEmpOrgRequestPo.setCreateTime(DateUtil.dateStrToDate(createTime, DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR));
            domainEmpOrgRequestPo.setUpdateTime(DateUtil.dateStrToDate(updateTime, DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME_MICR));

            QueryResult<DomainWorkExperienceResponseVO>  queryResult = domainEmployeeInfoService.employeeWorkList(domainEmpOrgRequestPo,page, rows);

            return JsonResultUtil.createSuccessJsonResult(queryResult);
        } catch (Exception var4) {
            this.logger.error("根据groupID查询员工信息" + var4.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("根据groupID查询员工信息{0}", var4);
        }
        return jsonResult;
    }
}




