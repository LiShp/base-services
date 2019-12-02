package com.gw.domain_hr.rest;

import com.gw.cloud.common.base.controller.BaseController;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain_hr.entity.DomainOrgStructure;
import com.gw.domain_hr.service.DomainOrgStructureService;
import com.gw.domain_hr.service.HrDataToDomainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;


@RestController
public class HrDataToDomainController {

    @Autowired
    private HrDataToDomainService hrDataToDomainService;

    /**
     * 全量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     *
     * @return
     */
    @RequestMapping(value = "/sysGroupToOrgStruAll")
    public String sysGroupToOrgStruAll() {
        int num = hrDataToDomainService.sysGroupToOrgStruAll();
        return Integer.toString(num);
    }

    /**
     * 增量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     *
     * @return
     */
    @RequestMapping(value = "/sysGroupToOrgStruNew")
    public String sysGroupToOrgStruNew() {
        int num = hrDataToDomainService.sysGroupToOrgStruNew();
        return Integer.toString(num);
    }

    /**
     * 全量导入数据 SQLserver表sys_FieldValue到mysql表domain_basic_info表
     *
     * @return
     */
    @RequestMapping(value = "/sysFieldValueToBasicInfoAll")
    public String sysFieldValueToBasicInfoAll() {
        int num = hrDataToDomainService.sysFieldValueToBasicInfoAll();
        return Integer.toString(num);
    }

    /**
     * 全量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     *
     * @return
     */
    @RequestMapping(value = "/personnelToEmployeeInfoAll")
    public String personnelToEmployeeInfoAll() {
        int num = hrDataToDomainService.personnelToEmployeeInfoAll();
        return Integer.toString(num);
    }

    /**
     * 增量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     *
     * @return
     */
    @RequestMapping(value = "/personnelToEmployeeInfoNew")
    public String personnelToEmployeeInfoNew() {
        int num = hrDataToDomainService.personnelToEmployeeInfoNew();
        return Integer.toString(num);
    }

}




