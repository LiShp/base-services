package com.gw.domain.hr.rest;

import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain.hr.service.*;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zoujialiang
 */
@RestController
public class HrDataToDomainController {

    private GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    @Resource
    private HrDataToDomainService hrDataToDomainService;

    @Autowired
    private SyncOrgStruAllService syncOrgStruAllService;

    @Autowired
    private SyncBasicInfoAllService syncBasicInfoAllService;

    @Autowired
    private SyncEmployeeAllService syncEmployeeAllService;

    @Autowired
    private SyncFileInfoAllService syncFileInfoAllService;

    @Autowired
    private SyncWorkAllService syncWorkAllService;

    @Autowired
    private SyncEmployeeNewService syncEmployeeNewService;

    @Autowired
    private SyncFileInfoNewService syncFileInfoNewService;

    @Autowired
    private SyncWorkNewService syncWorkNewService;

    /**
     * 全量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     *
     * @return
     */
    @PostMapping(value = "/sync-org-all")
    public JsonResult<Object> sysGroupToOrgStruAll() {
        JsonResult jsonResult;
        try {
            int num = syncOrgStruAllService.syncAll();
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("..........从HR库中o_group获取全量数据发生异常：" + e.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }


    /**
     * 全量导入数据 SQLserver表sys_FieldValue到mysql表domain_basic_info表
     *
     * @return
     */
    @PostMapping(value = "/sync-basic-all")
    public JsonResult<Object> sysFieldValueToBasicInfoAll() {
        JsonResult jsonResult;
        try {
            int num = syncBasicInfoAllService.syncAll();
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库sys_FieldValue获取数据发生异常：" + e.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }

    /**
     * 全量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     *
     * @return
     */
    @GetMapping(value = "/personnelToEmployeeInfoAll")
    public JsonResult<Object> personnelToEmployeeInfoAll() {
        JsonResult jsonResult;
        try {
            int num = syncEmployeeAllService.syncAll();
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库hr_Personnel获取全量数据发生异常：" + e.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }

    /**
     * 增量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     *
     * @return
     */
    @PostMapping(value = "/sync-employee-all-update/{limit}/{updateLoop}")
    public JsonResult<Object> personnelToEmployeeInfoAllUpdate(@PathVariable(name = "limit") int limit, @PathVariable(name = "updateLoop") int updateLoop) {
        JsonResult jsonResult;
        try {
            int num = hrDataToDomainService.personnelToEmployeeInfoAllUpdate(limit, updateLoop);
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库hr_Personnel获取增量数据发生异常：" , e);
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }


    /**
     * 增量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     *
     * @return
     */
    @PostMapping(value = "/sync-employee-new")
    public JsonResult<Object> personnelToEmployeeInfoNew() {
        JsonResult jsonResult;
        try {
            int num = syncEmployeeNewService.syncNew("personnelNo");
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库hr_Personnel获取增量数据发生异常：" , e);
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }

    /**
     * 头像信息同步-全量
     * @return
     */
    @PostMapping(value = "/sync-file-all")
    public JsonResult<Object> fileInfoSyncAll() {
        JsonResult jsonResult;
        try {
            int num = syncFileInfoAllService.syncAll();
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库hr_Personnel获取增量数据发生异常：" , e);
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }

    /**
     * 头像信息同步-增量
     * @return
     */
    //@PostMapping(value = "/sync-file-new")
    public JsonResult<Object> fileInfoSyncNew() {
        JsonResult jsonResult;
        try {
            int num = syncFileInfoNewService.syncNew("code");
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库hr_Personnel获取增量数据发生异常：" , e);
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }

    /**
     * 工作经历同步-全量
     * @return
     */
    @GetMapping(value = "/workExperienceSyncAll")
    public JsonResult<Object> workExperienceSyncAll() {
        JsonResult jsonResult;
        try {
            int num = syncWorkAllService.syncAll();
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库hr_Personnel获取增量数据发生异常：" , e);
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }

    /**
     * 工作经历同步-增量
     * @return
     */
    @PostMapping(value = "/sync-work-new")
    public JsonResult<Object> workExperienceSyncNew() {
        JsonResult jsonResult;
        try {
            int num = syncWorkNewService.syncNew("workExperienceId");
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库hr_Personnel获取增量数据发生异常：" , e);
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }

}




