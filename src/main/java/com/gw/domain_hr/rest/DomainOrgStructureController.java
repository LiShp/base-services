package com.gw.domain_hr.rest;

import com.gw.cloud.common.base.controller.BaseController;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain_hr.entity.DomainOrgStructure;
import com.gw.domain_hr.service.DomainOrgStructureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;


@Api(value = "/domainorgstructure", description = "")
@RestController
@RequestMapping("/domainorgstructure")
public class DomainOrgStructureController extends BaseController<Long, DomainOrgStructure> {

    @Autowired
    private DomainOrgStructureService domainOrgStructureService;

    /**
     * 按启用时间的时间段查询
     *
     * @param startDatetime 开始时间
     * @param endDatetime   结束时间
     * @param page          当前页码
     * @param rows          每页显示条数
     * @return
     */
    @ApiOperation(
            value = "按启用时间的时间段查询",
            notes = "按启用时间的时间段查询",
            httpMethod = "GET"
    )
    @GetMapping("/selectDomainOrgStructureByEnableTime")
    public JsonResult<QueryResult<DomainOrgStructure>> selectDomainOrgStructureByEnableTime(
            @ApiParam(name = "startDatetime", value = "开始时间") @RequestParam String startDatetime,
            @ApiParam(name = "endDatetime", value = "结束时间") @RequestParam String endDatetime,
            @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows) {

        JsonResult jsonResult;
        try {
            QueryResult<DomainOrgStructure> pageResult = domainOrgStructureService.selectDomainOrgStructureByEnableTime(startDatetime, endDatetime, page, rows);
            jsonResult = JsonResultUtil.createSuccessJsonResult(pageResult);
        } catch (Exception e) {
            this.logger.error(MessageFormat.format("查询失败！ {0}", e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", e);
        }

        return jsonResult;
    }

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
    @GetMapping("/selectDomainOrgStructureByCreateTime")
    public JsonResult<QueryResult<DomainOrgStructure>> selectDomainOrgStructureByCreateTime(
            @ApiParam(name = "startDatetime", value = "开始时间") @RequestParam String startDatetime,
            @ApiParam(name = "endDatetime", value = "结束时间") @RequestParam String endDatetime,
            @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows) {

        JsonResult jsonResult;
        try {
            QueryResult<DomainOrgStructure> pageResult = domainOrgStructureService.selectDomainOrgStructureByCreateTime(startDatetime, endDatetime, page, rows);
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
    @GetMapping("/selectDomainOrgStructureByUpdateTime")
    public JsonResult<QueryResult<DomainOrgStructure>> selectDomainOrgStructureByUpdateTime(
            @ApiParam(name = "startDatetime", value = "开始时间") @RequestParam String startDatetime,
            @ApiParam(name = "endDatetime", value = "结束时间") @RequestParam String endDatetime,
            @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows) {

        JsonResult jsonResult;
        try {
            QueryResult<DomainOrgStructure> pageResult = domainOrgStructureService.selectDomainOrgStructureByUpdateTime(startDatetime, endDatetime, page, rows);
            jsonResult = JsonResultUtil.createSuccessJsonResult(pageResult);
        } catch (Exception e) {
            this.logger.error(MessageFormat.format("查询失败！ {0}", e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", e);
        }

        return jsonResult;
    }

    /**
     * 人员信息表 通过组织id获取树形结构 暂定只返回组织编码/组织名称/父级编码
     *
     * @param id
     * @return
     */
    @ApiOperation(
            value = "通过组织编码获取所有组织",
            notes = "通过组织编码获取所有组织",
            httpMethod = "GET"
    )
    @GetMapping(value = "/group/{id}")
    public JsonResult<Object> getGroupById(@PathVariable("id") Integer id) {
        this.logger.info("组织架构表_通过组织编码获取所有组织开始");
        JsonResult jsonResult;
        int groupCode = 0;
        if (id != null) {
            groupCode = id.intValue();
        }
        try {
            List<DomainOrgStructure> resultList = domainOrgStructureService.getGroupById(groupCode);
            jsonResult = JsonResultUtil.createSuccessJsonResult(resultList);
        } catch (Exception var4) {
            this.logger.error("组织架构表_通过组织编码获取所有组织发生异常" + var4.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", var4);
        }
        this.logger.info("组织架构表_通过组织编码获取所有组织结束");
        return jsonResult;
    }

}




