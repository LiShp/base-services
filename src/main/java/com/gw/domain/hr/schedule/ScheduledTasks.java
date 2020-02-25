package com.gw.domain.hr.schedule;

import com.gw.domain.hr.service.HrDataToDomainService;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zoujialiang
 */
@Component
public class ScheduledTasks {

    private GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    @Resource
    private HrDataToDomainService hrDataToDomainService;

    /**
     * 每天早上凌晨1点更新一次
     * 每月每天凌晨1点0分0秒执行一次
     */
    @Scheduled(cron = "0 00 01 * * ?")
    public void scheduledBasic() {
        this.logger.info("定时任务(全量导入_基础信息表数据)开始执行");
        try {
            hrDataToDomainService.sysFieldValueToBasicInfoAll();
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库sys_FieldValue获取数据发生异常：" + e.getMessage());
        }
        this.logger.info("定时任务(全量导入_基础信息表数据)结束");
    }

    /**
     * 每天早上凌晨1点30分更新一次
     * 每月每天凌晨1点30分0秒执行一次
     */
//    @Scheduled(cron = "0 30 01 * * ?")
    public void scheduledPosition() {
        this.logger.info("定时任务(全量导入_职位信息表数据)开始执行");
        try {
            hrDataToDomainService.hrPositongToAll();
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库hr_position获取数据发生异常：" + e.getMessage());
        }
        this.logger.info("定时任务(全量导入_职位信息表数据)结束");
    }

    /**
     * 每月每天早上8到20点更新 每隔4个小时更新一次
     * 0 0 8,12,16,20 * * ?
     */
    @Scheduled(cron = "0 0 8,12,16,20 * * ?")
    public void scheduledOrg() {
        this.logger.info("定时任务(增量更新_组织架构表)开始执行");
        try {
            hrDataToDomainService.sysGroupToOrgStruNew();
        } catch (Exception e) {
            this.logger.error("从Sqlserver中间库sys_Group增量获取数据发生异常：" + e.getMessage());
        }
        this.logger.info("定时任务(增量更新_组织架构表)结束");
    }

    /**
     * 每月每天早上8到20点更新 每隔5分钟更新一次
     * 0 0/5 8-20 * * ?
     */
    @Scheduled(cron = "0 0/5 8-20 * * ?")
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
