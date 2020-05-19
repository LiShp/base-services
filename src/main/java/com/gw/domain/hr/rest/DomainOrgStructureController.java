package com.gw.domain.hr.rest;

import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain.hr.entity.vo.DomainOrgStructureVO;
import com.gw.domain.hr.entity.vo.NodeVO;
import com.gw.domain.hr.service.DomainOrgStructureService;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zoujialiang
 */
@Api(value = "/domainorgstructure", tags = "组织信息", description = "组织信息")
@RestController
@RequestMapping("/domainorgstructure")
public class DomainOrgStructureController {

    protected GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    @Autowired
    private DomainOrgStructureService domainOrgStructureService;


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




