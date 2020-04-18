package com.gw.domain.hr.rest;

import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain.hr.service.HrDataToDomainService;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zoujialiang
 */
@RestController
public class HrDataToDomainController {

    private GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    @Resource
    private HrDataToDomainService hrDataToDomainService;

    /**
     * 全量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     *
     * @return
     */
    @GetMapping(value = "/sysGroupToOrgStruAll")
    public JsonResult<Object> sysGroupToOrgStruAll() {
        JsonResult jsonResult;
        try {
            int num = hrDataToDomainService.sysGroupToOrgStruAll();
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("..........从Sqlserver中间库sys_Group获取全量数据发生异常：" + e.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }

    /**
     * 增量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     *
     * @return
     */
    @GetMapping(value = "/sysGroupToOrgStruNew")
    public JsonResult<Object> sysGroupToOrgStruNew() {
        JsonResult jsonResult;
        try {
            int num = hrDataToDomainService.sysGroupToOrgStruNew();
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库sys_Group增量获取数据发生异常：" + e.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }

    /**
     * 全量导入数据 SQLserver表sys_FieldValue到mysql表domain_basic_info表
     *
     * @return
     */
    @GetMapping(value = "/sysFieldValueToBasicInfoAll")
    public JsonResult<Object> sysFieldValueToBasicInfoAll() {
        JsonResult jsonResult;
        try {
            int num = hrDataToDomainService.sysFieldValueToBasicInfoAll();
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库sys_FieldValue获取数据发生异常：" + e.getMessage());
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }
    /**
     * 全量导入数据 SQLserver表HR_Position到mysql表domain_position表
     *
     * @return
     */
    @GetMapping(value = "/hrPositongToAll")
    public JsonResult<Object> hrPositongToAll() {
        JsonResult jsonResult;
        try {
            int num = hrDataToDomainService.hrPositongToAll();
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库HR_Position获取数据发生异常：" + e.getMessage());
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
            int num = hrDataToDomainService.personnelToEmployeeInfoAll();
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
    @GetMapping(value = "/personnelToEmployeeInfoNew")
    public JsonResult<Object> personnelToEmployeeInfoNew() {
        JsonResult jsonResult;
        try {
            int num = hrDataToDomainService.personnelToEmployeeInfoNew();
            jsonResult = JsonResultUtil.createSuccessJsonResult(num);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库hr_Personnel获取增量数据发生异常：" , e);
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }

    @GetMapping(value = "/dif")
    public JsonResult<List<Long>> dif(){
        JsonResult jsonResult;
        try {
            List<Long> list = hrDataToDomainService.dif();
            jsonResult = JsonResultUtil.createSuccessJsonResult(list);
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库hr_Personnel获取增量数据发生异常：" , e);
            jsonResult = JsonResultUtil.createFailureJsonResult("创建失败！ {0}", e);
        }
        return jsonResult;
    }

}




