package com.gw.domain_hr.rest;

import com.gw.cloud.common.base.controller.BaseController;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain_hr.entity.DomainBasicInfo;
import com.gw.domain_hr.service.DomainBasicInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.MessageFormat;


@Api(value="/domainbasicinfo", description="")
@RestController
@RequestMapping("/domainbasicinfo")
public class DomainBasicInfoController extends BaseController<Long,DomainBasicInfo> {

    @Autowired
    private DomainBasicInfoService domainBasicInfoService;

    /**
    * 按创建时间的时间段查询
    * @param startDatetime 开始时间
    * @param endDatetime 结束时间
    * @param page  当前页码
    * @param rows  每页显示条数
    * @return
    */
    @ApiOperation(
        value = "按创建时间的时间段查询",
        notes = "按创建时间的时间段查询",
        httpMethod = "GET"
    )
    @GetMapping("/selectDomainBasicInfoByCreateTime")
    public JsonResult<QueryResult<DomainBasicInfo>> selectDomainBasicInfoByCreateTime(
                                                                                @ApiParam(name = "startDatetime",value = "开始时间")@RequestParam String startDatetime,
                                                                                @ApiParam(name = "endDatetime",value = "结束时间")@RequestParam String endDatetime,
                                                                                @ApiParam(name = "page",value = "页码（默认为1）") @RequestParam(value = "page",defaultValue = "1")Integer page,
                                                                                @ApiParam(name = "rows",value = "每页显示条数（默认为10）") @RequestParam(value = "rows",defaultValue = "10")Integer rows) {

        JsonResult jsonResult;
        try {
            QueryResult<DomainBasicInfo> pageResult = domainBasicInfoService.selectDomainBasicInfoByCreateTime(startDatetime, endDatetime, page, rows);
            jsonResult = JsonResultUtil.createSuccessJsonResult(pageResult);
        } catch (Exception e) {
            this.logger.error(MessageFormat.format("查询失败！ {0}", e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", e);
        }

            return jsonResult;
    }
    /**
    * 按更新时间的时间段查询
    * @param startDatetime 开始时间
    * @param endDatetime 结束时间
    * @param page  当前页码
    * @param rows  每页显示条数
    * @return
    */
    @ApiOperation(
        value = "按更新时间的时间段查询",
        notes = "按更新时间的时间段查询",
        httpMethod = "GET"
    )
    @GetMapping("/selectDomainBasicInfoByUpdateTime")
    public JsonResult<QueryResult<DomainBasicInfo>> selectDomainBasicInfoByUpdateTime(
                                                                                @ApiParam(name = "startDatetime",value = "开始时间")@RequestParam String startDatetime,
                                                                                @ApiParam(name = "endDatetime",value = "结束时间")@RequestParam String endDatetime,
                                                                                @ApiParam(name = "page",value = "页码（默认为1）") @RequestParam(value = "page",defaultValue = "1")Integer page,
                                                                                @ApiParam(name = "rows",value = "每页显示条数（默认为10）") @RequestParam(value = "rows",defaultValue = "10")Integer rows) {

        JsonResult jsonResult;
        try {
            QueryResult<DomainBasicInfo> pageResult = domainBasicInfoService.selectDomainBasicInfoByUpdateTime(startDatetime, endDatetime, page, rows);
            jsonResult = JsonResultUtil.createSuccessJsonResult(pageResult);
        } catch (Exception e) {
            this.logger.error(MessageFormat.format("查询失败！ {0}", e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", e);
        }

            return jsonResult;
    }
}




