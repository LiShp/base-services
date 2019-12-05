package com.gw.domain_hr.rest;

import com.alibaba.fastjson.JSONObject;
import com.gw.cloud.common.base.controller.BaseController;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain_hr.entity.DomainOrgStructure;
import com.gw.domain_hr.enums.ResultStatusEnum;
import com.gw.domain_hr.service.DomainOrgStructureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;


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
     * @param id
     * @return
     */
    @ApiOperation(
            value = "通过组织编码获取所有组织",
            notes = "通过组织编码获取所有组织",
            httpMethod = "GET"
    )
    @GetMapping(value = "/getGroup/{id}")
    public JSONObject getGroupById(@PathVariable("id") Integer id) {
        this.logger.info("....................组织架构表_通过组织编码获取所有组织开始....................");
        //返回JSON
        JSONObject jsonObject = new JSONObject();
        int groupCode = 0;
        if (id != null) {
            groupCode = id.intValue();
        }
        try {
            List<Map<String, Object>> resultList = domainOrgStructureService.getGroupById(groupCode);
            jsonObject.put("JSONArray", resultList);
            jsonObject.put(ResultStatusEnum.STATUS_SUCCESS.getCode(), "查询成功");
        } catch (Exception e) {
            this.logger.error("....................组织架构表_通过组织编码获取所有组织发生异常...................." + e.getMessage());
            jsonObject.put(ResultStatusEnum.STATUS_ERROR.getCode(), "组织架构表_通过组织编码获取所有组织发生异常" + e.getMessage());
            return jsonObject;
        }
        this.logger.info("....................组织架构表_通过组织编码获取所有组织结束....................");
        return jsonObject;
    }

}




