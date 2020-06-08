package com.gw.domain.hr.rest;

import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain.hr.commonutils.DateUtil;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.entity.DomainOrgStructure;
import com.gw.domain.hr.entity.po.DomainEmpOrgRequestPo;
import com.gw.domain.hr.entity.vo.DomainEmployeeInfoVO;
import com.gw.domain.hr.entity.vo.EmployeeOrgPrivacyVO;
import com.gw.domain.hr.entity.vo.EmployeeOrgVO;
import com.gw.domain.hr.entity.vo.EmployeeVO;
import com.gw.domain.hr.service.DomainEmployeeInfoService;
import com.gw.domain.hr.service.DomainOrgStructureService;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
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
    private DomainOrgStructureService domainOrgStructureService;

    /*@ApiOperation(
            value = "【自定义】- 单条新增人员基础信息",
            notes = "【自定义】- 单条新增人员基础信息",
            httpMethod = "POST"
    )*/
    @PostMapping(value = "/employee")
    public JsonResult<Object> insertEmployeeInfoSingleByPersonnelNo(@RequestBody @Valid DomainEmployeeInfo domainEmployeeInfo, Errors errors) {
        this.logger.info("人员基础信息表_单条插入数据开始");
        JsonResult jsonResult;
        //先校验入参
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().toString();
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", errorMessage);
            return jsonResult;
        }
        try {
            int num = domainEmployeeInfoService.insertEmployeeInfoSingle(domainEmployeeInfo);
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception var4) {
            this.logger.error("人员基础信息表_单条插入数据发生异常" + var4.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", var4);
        }
        this.logger.info("人员基础信息表_单条插入数据结束");
        return jsonResult;
    }

    /*@ApiOperation(
            value = "【自定义】- 通过工号单条更新人员基础信息",
            notes = "【自定义】- 通过工号单条更新人员基础信息",
            httpMethod = "PUT"
    )*/
    @PutMapping(value = "/employee/{personnelno}")
    public JsonResult<Integer> updateEmployeeInfoSingleByPersonnelNo(@RequestBody DomainEmployeeInfo domainEmployeeInfo, @PathVariable("personnelno") String personnelno) {
        JsonResult jsonResult;
        try {
            domainEmployeeInfo.setPersonnelNo(personnelno);
            int num = domainEmployeeInfoService.updateEmployeeInfoSingleByPersonnelNo(domainEmployeeInfo);
            jsonResult = JsonResultUtil.createSuccessJsonResult("更新成功，条数 " + num + "条");
        } catch (Exception var4) {
            this.logger.error("人员基础信息表_单条更新数据发生异常" + var4.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("更新失败！ {0}", var4);
        }
        return jsonResult;
    }

    @ApiOperation(
            value = "【自定义】- 通过工号查询人员基础信息",
            notes = "【自定义】- 通过工号查询人员基础信息",
            httpMethod = "GET"
    )
    @GetMapping(value = "/employee/{personnelNo}")
    public JsonResult<DomainEmployeeInfoVO> selectEmployeeInfoSingleByPersonnelNo(@PathVariable("personnelNo") String personnelNo) {
        JsonResult jsonResult;
        try {
            Example example = new Example(DomainEmployeeInfo.class);
            example.createCriteria().andEqualTo("personnelNo", personnelNo)
            .andEqualTo("personnelStatus",1);

            DomainEmpOrgRequestPo domainEmpOrgRequestPo = new DomainEmpOrgRequestPo();
            domainEmpOrgRequestPo.setPersonnelNo(personnelNo);
            domainEmpOrgRequestPo.setPersonnelStatus(1);

            DomainEmployeeInfoVO domainEmployeeInfoVO = domainEmployeeInfoService.employee(domainEmpOrgRequestPo);
            if(domainEmployeeInfoVO!=null) {
                Example orgExample = new Example(DomainOrgStructure.class);
                orgExample.createCriteria().andEqualTo("id", domainEmployeeInfoVO.getGroupId())
                .andEqualTo("deleteFlag", 0);
                DomainOrgStructure domainOrgStructure = domainOrgStructureService.selectOneByExample(orgExample);
                domainEmployeeInfoVO.setParentId(domainOrgStructure.getParentId());
            }
            jsonResult = JsonResultUtil.createSuccessJsonResult(domainEmployeeInfoVO);
        } catch (Exception var4) {
            this.logger.error("通过工号查询人员基础信息异常，" ,var4);
            jsonResult = JsonResultUtil.createFailureJsonResult("通过工号查询人员基础信息异常！ {0}", var4);
        }
        return jsonResult;
    }

    @ApiOperation(
            value = "【自定义】- 通过工号列表批量查询人员基础信息",
            notes = "【自定义】- 通过工号列表批量查询人员基础信息",
            httpMethod = "POST"
    )
    @PostMapping(value = "/employees")
    public JsonResult<List<DomainEmployeeInfoVO>> selectEmployeeInfoByPersonnelNos(@RequestBody @NotEmpty(message = "工号数组不能为空") List<String> personnelNo) {
        JsonResult jsonResult;
        try {
            Example example = new Example(DomainEmployeeInfo.class);
            example.createCriteria().andIn("personnelNo", personnelNo)
                    .andEqualTo("personnelStatus",1);

            List<DomainEmployeeInfo> domainEmployeeInfo = domainEmployeeInfoService.selectListByExample(example);
            List<DomainEmployeeInfoVO> domainEmployeeInfoVOList = DozerUtil.convert(domainEmployeeInfo, DomainEmployeeInfoVO.class);
            for(DomainEmployeeInfoVO info : domainEmployeeInfoVOList) {
                Example orgExample = new Example(DomainOrgStructure.class);
                orgExample.createCriteria().andEqualTo("id", info.getGroupId())
                        .andEqualTo("deleteFlag", 0);
                DomainOrgStructure domainOrgStructure = domainOrgStructureService.selectOneByExample(orgExample);
                if(domainOrgStructure.getLevel()==5) {
                    Example parentExample = new Example(DomainOrgStructure.class);
                    parentExample.createCriteria().andEqualTo("id", domainOrgStructure.getParentId())
                            .andEqualTo("deleteFlag", 0);
                    domainOrgStructure = domainOrgStructureService.selectOneByExample(parentExample);
                }
                info.setParentId(domainOrgStructure.getParentId());
                info.setParentName(domainOrgStructure.getGroupName());
            }
            jsonResult = JsonResultUtil.createSuccessJsonResult(domainEmployeeInfoVOList);
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
    public JsonResult<QueryResult<EmployeeVO>> employeesByGroup(@PathVariable("groupId") Integer groupId){
        JsonResult jsonResult;
        try {
            DomainEmployeeInfo domainEmployeeInfo = new DomainEmployeeInfo();
            domainEmployeeInfo.setDeleteFlag(Boolean.FALSE);
            domainEmployeeInfo.setPersonnelStatus(1);
            domainEmployeeInfo.setGroupId(groupId);
            List<DomainEmployeeInfo> domainEmployeeInfoList = domainEmployeeInfoService.selectList(domainEmployeeInfo);
            if(domainEmployeeInfoList.isEmpty()) {
                jsonResult = JsonResultUtil.createSuccessJsonResult(domainEmployeeInfoList);
            }else{
                List<EmployeeVO> employeeVOList = DozerUtil.convert(domainEmployeeInfoList, EmployeeVO.class);
                jsonResult = JsonResultUtil.createSuccessJsonResult(employeeVOList);
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
    public JsonResult<QueryResult<EmployeeOrgVO>> employees(
            @ApiParam(name = "nameLike", value = "员工姓名") @RequestParam(required = false) String nameLike,
            @ApiParam(name = "groupId", value = "组织ID") @RequestParam(required = false) Integer groupId,
            @ApiParam(name = "groupName", value = "组织名称") @RequestParam(required = false) String groupName,
            @ApiParam(name = "unitId", value = "单位ID") @RequestParam(required = false) Integer unitId,
            @ApiParam(name = "unitName", value = "单位名称") @RequestParam(required = false) String unitName,
            @ApiParam(name = "departmentId", value = "部门ID") @RequestParam(required = false) Integer departmentId,
            @ApiParam(name = "departmentName", value = "部门名称") @RequestParam(required = false) String departmentName,
            @ApiParam(name = "personnelStatus", value = "是否在职,1在职、2离职") @RequestParam(required = false) Integer personnelStatus,
            @ApiParam(name = "createTime", value = "开始时间") @RequestParam(required = false)
            @Valid @Pattern(regexp= "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}:[0-9]{3}", message = "时间格式必须为2020-06-01 00:00:00:000格式")
                    String createTime,
            @ApiParam(name = "updateTime", value = "更新时间") @RequestParam(required = false) String updateTime,
            @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows){
        JsonResult jsonResult;
        try {

            DomainEmpOrgRequestPo domainEmpOrgRequestPo = new DomainEmpOrgRequestPo();
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

            QueryResult<EmployeeOrgVO>  queryResult = domainEmployeeInfoService.employeeList(domainEmpOrgRequestPo,page, rows, EmployeeOrgVO.class);

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
    public JsonResult<QueryResult<EmployeeOrgPrivacyVO>> employeesPrivacy(
            @ApiParam(name = "nameLike", value = "员工姓名") @RequestParam(required = false) String nameLike,
            @ApiParam(name = "groupId", value = "组织ID") @RequestParam(required = false) Integer groupId,
            @ApiParam(name = "groupName", value = "组织名称") @RequestParam(required = false) String groupName,
            @ApiParam(name = "unitId", value = "单位ID") @RequestParam(required = false) Integer unitId,
            @ApiParam(name = "unitName", value = "单位名称") @RequestParam(required = false) String unitName,
            @ApiParam(name = "departmentId", value = "部门ID") @RequestParam(required = false) Integer departmentId,
            @ApiParam(name = "departmentName", value = "部门名称") @RequestParam(required = false) String departmentName,
            @ApiParam(name = "personnelStatus", value = "是否在职,1在职、2离职") @RequestParam(required = false) Integer personnelStatus,
            @ApiParam(name = "createTime", value = "开始时间") @RequestParam(required = false)
            @Valid @Pattern(regexp= "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}:[0-9]{3}", message = "时间格式必须为2020-06-01 00:00:00:000格式")
                    String createTime,
            @ApiParam(name = "updateTime", value = "更新时间") @RequestParam(required = false) String updateTime,
            @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows){
        JsonResult jsonResult;
        try {

            DomainEmpOrgRequestPo domainEmpOrgRequestPo = new DomainEmpOrgRequestPo();
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

            QueryResult<EmployeeOrgPrivacyVO>  queryResult = domainEmployeeInfoService.employeeList(domainEmpOrgRequestPo,page, rows, EmployeeOrgPrivacyVO.class);

            return JsonResultUtil.createSuccessJsonResult(queryResult);
        } catch (Exception var4) {
            this.logger.error("根据groupID查询员工信息" + var4.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("根据groupID查询员工信息{0}", var4);
        }
        return jsonResult;
    }
}




