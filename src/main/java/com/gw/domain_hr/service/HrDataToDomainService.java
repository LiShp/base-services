package com.gw.domain_hr.service;

import com.gw.domain_hr.commonUtils.CollectionUtil;
import com.gw.domain_hr.mapper.DataInBasicInfoMapper;
import com.gw.domain_hr.mapper.DataInOrgStruMapper;
import com.gw.domain_hr.mapperdata.DataToBasicInfoMapper;
import com.gw.domain_hr.mapperdata.DataToOrgStruMapper;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class HrDataToDomainService {

    protected GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    @Autowired
    CollectionUtil collectionUtil;

    @Autowired
    private DataToOrgStruMapper dataToOrgStruMapper;
    @Autowired
    private DataInOrgStruMapper dataInOrgStruMapper;

    @Autowired
    private DataToBasicInfoMapper dataToBasicInfoMapper;
    @Autowired
    private DataInBasicInfoMapper dataInBasicInfoMapper;

    /**
     * 全量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     * 数据量9000多条，一次性任务
     */
    @Transactional
    public int sysGroupToOrgStruAll() {
        this.logger.info("..........从Sqlserver中间库sys_Group获取数据开始..........");
        int num = 0;
        try {
            List<Map<String, Object>> list = dataToOrgStruMapper.getFromSysGroupAll();
            if (!CollectionUtils.isEmpty(list)) {
                //获取最大创建时间/最大更新时间
                Map<String,Object> map = dataToOrgStruMapper.getMaxTime();
                //最大创建时间/最大更新时间如入数据库
                int maxNum =dataInOrgStruMapper.insertMaxTime(map);

                //操作主库主数据
                int delete = dataInOrgStruMapper.deleteOrgStruAll();
                this.logger.info("..........删除domain_org_structure中Hr数据共计:" + delete + "条..........");
                num = dataInOrgStruMapper.insertOrgStruAll(list);
                this.logger.info("..........入表domain_org_structure共计:" + num + "条..........");
            }else{
                this.logger.info("..........Sqlserver中间库sys_Group表无新增数据..........");
            }
            this.logger.info("..........从Sqlserver中间库sys_Group获取数据结束..........");
        } catch (Exception e) {
            this.logger.error("..........从Sqlserver中间库sys_Group获取数据发生异常：" + e.getMessage());
        }
        return num;
    }

    /**
     * 增量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     * 定时任务
     */
    @Transactional
    public int sysGroupToOrgStruNew() {
        this.logger.info("..........从Sqlserver中间库sys_Group增量获取数据开始..........");
        int numCreate = 0;
        int numUpdate = 0;
        try {
            //第一步：获取最大创建时间 和 最大更新时间
            Map<String,Object> map = dataInOrgStruMapper.getMaxTime();
            //第二步：获取新增的数据 和 更新的数据
            List<Map<String, Object>> createlist = dataToOrgStruMapper.getFromSysGroupCreate(map);
            List<Map<String, Object>> updatelist = dataToOrgStruMapper.getFromSysGroupUpdate(map);
            //第三步：更新最大时间
            if(!CollectionUtils.isEmpty(createlist) || !CollectionUtils.isEmpty(updatelist)){
                //获取最大创建时间/最大更新时间
                Map<String,Object> mapTime = dataToOrgStruMapper.getMaxTime();
                //最大创建时间/最大更新时间如入数据库
                int maxNum =dataInOrgStruMapper.updateMaxTime(mapTime);
            }
            //第四步：处理Hr新增数据
            if (!CollectionUtils.isEmpty(createlist)) {
                numCreate = dataInOrgStruMapper.insertOrgStruAll(createlist);
                this.logger.info("..........入表domain_org_structure共计:" + numCreate + "条..........");
            }else{
                this.logger.info("..........Sqlserver中间库sys_Group表无新增数据..........");
            }
            //第四步：处理hr更新数据
            if (!CollectionUtils.isEmpty(updatelist)) {
                numUpdate = dataInOrgStruMapper.updateOrgStruAll(updatelist);
                this.logger.info("..........更新表domain_org_structure共计:" + numUpdate + "条..........");
            }else{
                this.logger.info("..........Sqlserver中间库sys_Group表无新更新数据..........");
            }
            this.logger.info("..........从Sqlserver中间库sys_Group增量获取数据结束..........");
        } catch (Exception e) {
            this.logger.error("..........从Sqlserver中间库sys_Group增量获取数据发生异常：" + e.getMessage());
        }
        return numCreate;
    }

    /**
     * 全量导入数据 SQLserver表sys_FieldValue到mysql表domain_basic_info表
     * 定时任务 每天晚上00：00全量更新一次
     */
    @Transactional
    public int sysFieldValueToBasicInfoAll() {
        this.logger.info("..........从Sqlserver中间库sys_FieldValue获取数据开始..........");
        int num = 0;
        try {
            List<Map<String, Object>> list = dataToBasicInfoMapper.getFromSysFieldValueAll();
            if (!CollectionUtils.isEmpty(list)) {
                int delete = dataInBasicInfoMapper.deleteBasicInfoAll();
                this.logger.info("..........删除domain_basic_info中Hr数据共计:" + delete + "条..........");
                num = dataInBasicInfoMapper.insertBasicInfoAll(list);
                this.logger.info("..........入表domain_basic_info共计:" + num + "条..........");
            }
            this.logger.info("..........从Sqlserver中间库sys_FieldValue获取数据结束..........");
        } catch (Exception e) {
            this.logger.error("..........从Sqlserver中间库sys_FieldValue获取数据发生异常：" + e.getMessage());
        }
        return num;
    }
}
