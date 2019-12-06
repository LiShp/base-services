package com.gw.domain_hr.schedule;

import com.gw.domain_hr.service.HrDataToDomainService;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ScheduledTasks {

    private GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    @Resource
    private HrDataToDomainService hrDataToDomainService;

    /**
     * 每天早上10点15执行 0 15 10 * * ? *"
     * 每隔10秒执行一次
     * 每天下午16：10-16：30每隔一分钟执行一次0 10-30 16 * * ?
     */
    @Scheduled(cron = "0 00 10 * * ? ")
    public void scheduledBasic() {
        this.logger.info("定时任务(全量导入_基础信息表数据)开始执行");
        try {
            hrDataToDomainService.sysFieldValueToBasicInfoAll();
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库sys_FieldValue获取数据发生异常：" + e.getMessage());
        }
        this.logger.info("定时任务(全量导入_基础信息表数据)结束");
    }

    @Scheduled(cron = "0 10 16 * * ? ")
    public void scheduledOrg() {
        this.logger.info("定时任务(增量更新_组织架构表)开始执行");
        try {
            hrDataToDomainService.sysGroupToOrgStruNew();
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库sys_Group增量获取数据发生异常：" + e.getMessage());
        }
        this.logger.info("定时任务(增量更新_组织架构表)结束");
    }

    @Scheduled(cron = "0 20 16 * * ? ")
    public void scheduledEmployee() {
        this.logger.info("定时任务(增量更新_员工基本信息表)开始执行");
        try {
            hrDataToDomainService.personnelToEmployeeInfoNew();
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库hr_Personnel增量获取数据发生异常：" + e.getMessage());
        }
        this.logger.info("定时任务(增量更新_员工基本信息表)结束");
    }
}
