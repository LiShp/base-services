package com.gw.domain_hr.schedule;

import com.gw.domain_hr.service.HrDataToDomainService;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    protected GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());
    private static final SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private HrDataToDomainService hrDataToDomainService;

    /**
     * 每天早上10点15执行 0 15 10 * * ? *"
     * 每隔10秒执行一次
     * 每天下午16：10-16：30每隔一分钟执行一次0 10-30 16 * * ?
     */
    @Scheduled(cron = "0 00 09 * * ? ")
    public void scheduledBasic() {
        this.logger.info("..........定时任务(全量导入_基础信息表数据)开始执行..........");
        hrDataToDomainService.sysFieldValueToBasicInfoAll();
        this.logger.info("..........定时任务(全量导入_基础信息表数据)结束..........");
    }

    @Scheduled(cron = "0 00 09 * * ? ")
    public void scheduledOrg() {
        this.logger.info("..........定时任务(增量更新_组织架构表)开始执行..........");
        hrDataToDomainService.sysGroupToOrgStruNew();
        this.logger.info("..........定时任务(增量更新_组织架构表)结束..........");
    }
}
