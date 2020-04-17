package com.gw.domain.hr.rest;

import com.gw.cloud.common.base.controller.BaseController;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain.hr.entity.DomainOrgStructureNode;
import com.gw.domain.hr.entity.DomainOrgStructure;
import com.gw.domain.hr.entity.vo.DomainOrgStructureVO;
import com.gw.domain.hr.entity.vo.EmployeeOrgVO;
import com.gw.domain.hr.entity.vo.NodeVO;
import com.gw.domain.hr.service.DomainOrgStructureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zoujialiang
 */
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
     * @param groupId
     * @return
     */
    @ApiOperation(
            value = "【自定义】- 通过组织编码查询子集组织名称和ID",
            notes = "【自定义】- 通过组织编码查询子集组织名称和ID",
            httpMethod = "GET"
    )
    @GetMapping(value = "/group/{groupId}")
    public JsonResult<List<DomainOrgStructureVO>> getGroupById(@PathVariable("groupId") Integer groupId) {
        this.logger.info("组织架构表_通过组织编码获取所有组织开始");
        JsonResult jsonResult;
        try {
            List<DomainOrgStructureVO> resultList = domainOrgStructureService.getGroupById(groupId);
            jsonResult = JsonResultUtil.createSuccessJsonResult(resultList);
        } catch (Exception var4) {
            this.logger.error("组织架构表_通过组织编码获取所有组织发生异常" + var4.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", var4);
        }
        this.logger.info("组织架构表_通过组织编码获取所有组织结束");
        return jsonResult;
    }

    /**
     * 人员信息表 通过组织id获取树形结构 暂定只返回组织编码/组织名称/父级编码
     *
     * @param groupId
     * @return
     */
    @ApiOperation(
            value = "【自定义】- 通过组织编码查询子集组织名称和ID以及组织下的员工信息",
            notes = "【自定义】- 通过组织编码查询子集组织名称和ID以及组织下的员工信息",
            httpMethod = "GET"
    )
    @GetMapping(value = "/depthgroup/{groupId}")
    public JsonResult<NodeVO> getDepthGroupById(@PathVariable("groupId") Long groupId) {
        this.logger.info("组织架构表_通过组织编码获取所有组织开始");
        JsonResult jsonResult;
        try {

            NodeVO resultList = domainOrgStructureService.getDepthGroupById(groupId);
            jsonResult = JsonResultUtil.createSuccessJsonResult(resultList);
        } catch (Exception var4) {
            this.logger.error("组织架构表_通过组织编码获取所有组织发生异常" + var4.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", var4);
        }
        this.logger.info("组织架构表_通过组织编码获取所有组织结束");
        return jsonResult;
    }

    /**
     * 人员信息表 通过组织id获取列表结构 返回人员ID、工号、姓名、科室名称、二级部名称
     *
     * @param groupId
     * @return
     */
    @ApiOperation(
            value = "【自定义】- 通过组织编码查询子级的员工列表",
            notes = "【自定义】- 通过组织编码查询子级的员工列表",
            httpMethod = "GET"
    )
    @GetMapping(value = "/depthgrouplist/{groupId}")
    public JsonResult<List<List<String>>> getDepthGroupListById(@PathVariable("groupId") Long groupId) {
        this.logger.info("通过组织编码查询子级的员工列表开始");
        JsonResult jsonResult;
        try {

            List<List<String>> resultList = domainOrgStructureService.getDepthGroupListById(1416L);
            jsonResult = JsonResultUtil.createSuccessJsonResult(resultList);
        } catch (Exception var4) {
            this.logger.error("通过组织编码查询子级的员工列表发生异常" , var4);
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", var4);
        }
        this.logger.info("通过组织编码查询子级的员工列表结束");
        return jsonResult;
    }

}




