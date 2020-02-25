package com.gw.domain.hr.rest;

import com.gw.cloud.common.base.controller.BaseController;
import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.entity.DomainOrgStructure;
import com.gw.domain.hr.entity.vo.DomainEmployeeInfoVO;
import com.gw.domain.hr.entity.vo.EmployeeVO;
import com.gw.domain.hr.enums.ResultStatusEnum;
import com.gw.domain.hr.service.DomainEmployeeInfoService;
import com.gw.domain.hr.service.DomainOrgStructureService;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author zoujialiang
 */
@Api(value = "/domainemployeeinfo", description = "")
@RestController
@RequestMapping("/domainemployeeinfo")
public class DomainEmployeeInfoController extends BaseController<Long, DomainEmployeeInfo> {

    @Autowired
    private DomainEmployeeInfoService domainEmployeeInfoService;

    @Autowired
    private DomainOrgStructureService domainOrgStructureService;

    /**
     * 按创建时间的时间段查询
     *
     * @param startDatetime 开始时间
     * @param endDatetime   结束时间
     * @param page          当前页码
     * @param rows          每页显示条数
     * @return
     */
    @ApiOperation(
            value = "按创建时间的时间段查询",
            notes = "按创建时间的时间段查询",
            httpMethod = "GET"
    )
    @GetMapping("/selectDomainEmployeeInfoByCreateTime")
    public JsonResult<QueryResult<DomainEmployeeInfo>> selectDomainEmployeeInfoByCreateTime(
            @ApiParam(name = "startDatetime", value = "开始时间") @RequestParam String startDatetime,
            @ApiParam(name = "endDatetime", value = "结束时间") @RequestParam String endDatetime,
            @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows) {

        JsonResult jsonResult;
        try {
            QueryResult<DomainEmployeeInfo> pageResult = domainEmployeeInfoService.selectDomainEmployeeInfoByCreateTime(startDatetime, endDatetime, page, rows);
            jsonResult = JsonResultUtil.createSuccessJsonResult(pageResult);
        } catch (Exception e) {
            this.logger.error(MessageFormat.format("查询失败！ {0}", e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", e);
        }

        return jsonResult;
    }

    /**
     * 按更新时间的时间段查询
     *
     * @param startDatetime 开始时间
     * @param endDatetime   结束时间
     * @param page          当前页码
     * @param rows          每页显示条数
     * @return
     */
    @ApiOperation(
            value = "按更新时间的时间段查询",
            notes = "按更新时间的时间段查询",
            httpMethod = "GET"
    )
    @GetMapping("/selectDomainEmployeeInfoByUpdateTime")
    public JsonResult<QueryResult<DomainEmployeeInfo>> selectDomainEmployeeInfoByUpdateTime(
            @ApiParam(name = "startDatetime", value = "开始时间") @RequestParam String startDatetime,
            @ApiParam(name = "endDatetime", value = "结束时间") @RequestParam String endDatetime,
            @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows) {

        JsonResult jsonResult;
        try {
            QueryResult<DomainEmployeeInfo> pageResult = domainEmployeeInfoService.selectDomainEmployeeInfoByUpdateTime(startDatetime, endDatetime, page, rows);
            jsonResult = JsonResultUtil.createSuccessJsonResult(pageResult);
        } catch (Exception e) {
            this.logger.error(MessageFormat.format("查询失败！ {0}", e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", e);
        }

        return jsonResult;
    }

    @ApiOperation(
            value = "单条新增人员基础信息",
            notes = "单条新增人员基础信息",
            httpMethod = "POST"
    )
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

    @ApiOperation(
            value = "通过工号单条更新人员基础信息",
            notes = "通过工号单条更新人员基础信息",
            httpMethod = "PUT"
    )
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
            value = "通过工号查询人员基础信息",
            notes = "通过工号查询人员基础信息",
            httpMethod = "GET"
    )
    @GetMapping(value = "/employee/{personnelNo}")
    public JsonResult<DomainEmployeeInfoVO> selectEmployeeInfoSingleByPersonnelNo(@PathVariable("personnelNo") String personnelNo) {
        JsonResult jsonResult;
        try {
            Example example = new Example(DomainEmployeeInfo.class);
            example.createCriteria().andEqualTo("personnelNo", personnelNo)
            .andEqualTo("personnelStatus",1);

            DomainEmployeeInfo domainEmployeeInfo = domainEmployeeInfoService.selectOneByExample(example);
            DomainEmployeeInfoVO domainEmployeeInfoVO = DozerUtil.convert(domainEmployeeInfo, DomainEmployeeInfoVO.class);
            if(domainEmployeeInfo!=null) {
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
            value = "根据科室ID查询员工信息",
            notes = "根据科室ID查询员工信息",
            httpMethod = "GET"
    )
    @GetMapping(value = "/employees/{groupId}")
    public JsonResult<QueryResult<EmployeeVO>> employeesByGroup(@PathVariable("groupId") Integer groupId){
        JsonResult jsonResult;
        try {
            DomainEmployeeInfo domainEmployeeInfo = new DomainEmployeeInfo();
            domainEmployeeInfo.setDeleteFlag(Boolean.FALSE);
            domainEmployeeInfo.setGroupId(groupId);
            List<DomainEmployeeInfo> domainEmployeeInfoList = super.baseService.selectList(domainEmployeeInfo);
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

}




