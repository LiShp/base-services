package com.gw.domain_hr.rest;

import com.gw.domain_hr.service.HrDataToDomainService;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class HrDataToDomainController {

    private GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    @Resource
    private HrDataToDomainService hrDataToDomainService;

    /**
     * 全量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     * @return
     */
    @RequestMapping(value = "/sysGroupToOrgStruAll")
    public String sysGroupToOrgStruAll() {
        try {
            int num = hrDataToDomainService.sysGroupToOrgStruAll();
            return Integer.toString(num);
        } catch (Exception e) {
            this.logger.error("..........从Sqlserver中间库sys_Group获取全量数据发生异常：" + e.getMessage());
            return "从Sqlserver中间库sys_Group获取数据发生异常";
        }
    }

    /**
     * 增量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     * @return
     */
    @RequestMapping(value = "/sysGroupToOrgStruNew")
    public String sysGroupToOrgStruNew() {
        try {
            int num = hrDataToDomainService.sysGroupToOrgStruNew();
            return Integer.toString(num);
        } catch (Exception e) {
            this.logger.error("..........从Sqlserver中间库sys_Group增量获取数据发生异常：" + e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 全量导入数据 SQLserver表sys_FieldValue到mysql表domain_basic_info表
     * @return
     */
    @RequestMapping(value = "/sysFieldValueToBasicInfoAll")
    public String sysFieldValueToBasicInfoAll() {
        try {
            int num = hrDataToDomainService.sysFieldValueToBasicInfoAll();
            return Integer.toString(num);
        } catch (Exception e) {
            this.logger.error("..........从Sqlserver中间库sys_FieldValue获取数据发生异常：" + e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 全量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     * @return
     */
    @RequestMapping(value = "/personnelToEmployeeInfoAll")
    public String personnelToEmployeeInfoAll() {
        try {
            int num = hrDataToDomainService.personnelToEmployeeInfoAll();
            return Integer.toString(num);
        } catch (Exception e) {
            this.logger.error("..........从Sqlserver中间库hr_Personnel获取全量数据发生异常：" + e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 增量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     * @return
     */
    @RequestMapping(value = "/personnelToEmployeeInfoNew")
    public String personnelToEmployeeInfoNew() {
        try {
            int num = hrDataToDomainService.personnelToEmployeeInfoNew();
            return Integer.toString(num);
        } catch (Exception e) {
            this.logger.error("..........从Sqlserver中间库hr_Personnel获取增量数据发生异常：" + e.getMessage());
            return e.getMessage();
        }
    }

}




