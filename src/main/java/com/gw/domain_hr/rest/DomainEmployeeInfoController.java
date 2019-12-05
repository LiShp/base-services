package com.gw.domain_hr.rest;

import com.alibaba.fastjson.JSONObject;
import com.gw.cloud.common.base.controller.BaseController;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain_hr.entity.DomainEmployeeInfo;
import com.gw.domain_hr.enums.ResultStatusEnum;
import com.gw.domain_hr.service.DomainEmployeeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.MessageFormat;


@Api(value = "/domainemployeeinfo", description = "")
@RestController
@RequestMapping("/domainemployeeinfo")
public class DomainEmployeeInfoController extends BaseController<Long, DomainEmployeeInfo> {

    @Autowired
    private DomainEmployeeInfoService domainEmployeeInfoService;

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


    /**
     * 人员信息表 单条插入数据 参数校验
     *
     * @param domainEmployeeInfo
     * @param errors
     * @return
     */
    @ApiOperation(
            value = "单条新增人员基础信息",
            notes = "单条新增人员基础信息",
            httpMethod = "POST"
    )
    @PostMapping(value = "/insertEmployeeInfoSingle")
    public JSONObject insertEmployeeInfoSingleByPersonnelNo(@Valid DomainEmployeeInfo domainEmployeeInfo, Errors errors) {
        this.logger.info("....................人员基础信息表_单条插入数据开始....................");
        //返回JSON
        JSONObject jsonObject = new JSONObject();
        String errorMessage = "";
        //先校验入参
        if (errors.hasErrors()) {
            errorMessage = errors.getFieldError().toString();
            jsonObject.put(ResultStatusEnum.STATUS_ERROR.getCode(), "入参格式错误:" + errorMessage);
            return jsonObject;
        }
        try {
            int num = domainEmployeeInfoService.insertEmployeeInfoSingle(domainEmployeeInfo);
            jsonObject.put(ResultStatusEnum.STATUS_SUCCESS.getCode(), "新增成功，条数 " + num + "条");
        } catch (Exception e) {
            this.logger.error("....................人员基础信息表_单条插入数据发生异常...................." + e.getMessage());
            jsonObject.put(ResultStatusEnum.STATUS_ERROR.getCode(), "单条插入数据发生异常" + e.getMessage());
            return jsonObject;
        }
        this.logger.info("....................人员基础信息表_单条插入数据结束....................");
        return jsonObject;
    }

    /**
     * 人员信息表 单条更新数据
     * @param domainEmployeeInfo
     * @return
     */
    @ApiOperation(
            value = "通过工号单条更新人员基础信息",
            notes = "通过工号单条更新人员基础信息",
            httpMethod = "POST"
    )
    @PostMapping(value = "/updateEmployeeInfoSingleByPersonnelNo")
    public JSONObject updateEmployeeInfoSingleByPersonnelNo(DomainEmployeeInfo domainEmployeeInfo) {
        this.logger.info("....................人员基础信息表_单条更新数据开始....................");
        //返回JSON
        JSONObject jsonObject = new JSONObject();
        String personnelNo = domainEmployeeInfo.getPersonnelNo();
        if(personnelNo.isEmpty()){
            jsonObject.put(ResultStatusEnum.STATUS_ERROR.getCode(), "工号personnelNo不能为空");
            return jsonObject;
        }
        try {
            int num = domainEmployeeInfoService.updateEmployeeInfoSingleByPersonnelNo(domainEmployeeInfo);
            jsonObject.put(ResultStatusEnum.STATUS_SUCCESS.getCode(), "更新成功，条数 " + num + "条");
        } catch (Exception e) {
            this.logger.error("....................人员基础信息表_单条更新数据发生异常...................." + e.getMessage());
            jsonObject.put(ResultStatusEnum.STATUS_ERROR.getCode(), "单条更新数据发生异常" + e.getMessage());
            return jsonObject;
        }
        this.logger.info("....................人员基础信息表_单条更新数据结束....................");
        return jsonObject;
    }
}




