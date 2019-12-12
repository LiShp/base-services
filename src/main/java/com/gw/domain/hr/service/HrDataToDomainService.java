package com.gw.domain.hr.service;

import com.gw.domain.hr.commonutils.CollectionUtil;
import com.gw.domain.hr.entity.DomainBasicInfo;
import com.gw.domain.hr.enums.TableNameEnum;
import com.gw.domain.hr.mapper.*;
import com.gw.domain.hr.mapperdata.DataToBasicInfoMapper;
import com.gw.domain.hr.mapperdata.DataToEmployeeInfoMapper;
import com.gw.domain.hr.mapperdata.DataToOrgStruMapper;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zoujialiang
 */
@Service
public class HrDataToDomainService {

    private GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    @Resource
    private CommonDomainMapper commonDomainMapper;

    @Resource
    private DataToOrgStruMapper dataToOrgStruMapper;
    @Resource
    private DomainOrgStructureMapper domainOrgStructureMapper;

    @Resource
    private DataToBasicInfoMapper dataToBasicInfoMapper;
    @Resource
    private DomainBasicInfoMapper domainBasicInfoMapper;

    @Resource
    private DataToEmployeeInfoMapper dataToEmployeeInfoMapper;
    @Resource
    private DomainEmployeeInfoMapper domainEmployeeInfoMapper;

    /**
     * 全量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     * 数据量9000多条，一次性任务
     */
    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int sysGroupToOrgStruAll() {
        this.logger.info("从Sqlserver中间库sys_Group获取数据开始");
        int num = 0;
        List<Map<String, Object>> list = dataToOrgStruMapper.getFromSysGroupAll();
        if (!CollectionUtils.isEmpty(list)) {
            Map<String, Object> map = new HashMap<>(16);
            String domainTableName = TableNameEnum.TABLE_NAME_ORGSTRUCTURE.getTableName();
            //操作主库主数据
            int delete = domainOrgStructureMapper.deleteOrgStruAll();
            this.logger.info("删除domain_org_structure中Hr数据共计:" + delete + "条");
            num = domainOrgStructureMapper.insertOrgStruAll(list);
            this.logger.info("入表domain_org_structure共计:" + num + "条");

            CollectionUtil.listSortDate(list, "create_time");
            map.put("maxCreateTime", list.get(list.size() - 1).get("create_time"));
            CollectionUtil.listSortDate(list, "update_time");
            map.put("maxUpdateTime", list.get(list.size() - 1).get("update_time"));
            map.put("tableName", domainTableName);
            this.insertMaxTime(map);

        } else {
            this.logger.info("Sqlserver中间库sys_Group表无新增数据");
        }
        this.logger.info("从Sqlserver中间库sys_Group获取数据结束");
        return num;
    }

    /**
     * 增量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     * 定时任务
     */
    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int sysGroupToOrgStruNew() {
        this.logger.info("从Sqlserver中间库sys_Group增量获取数据开始");
        int numCreate = 0;
        int numUpdate = 0;
        //第一步：获取最大创建时间 和 最大更新时间
        String domainTableName = TableNameEnum.TABLE_NAME_ORGSTRUCTURE.getTableName();
        Map<String, Object> map = commonDomainMapper.getMaxTime(domainTableName);

        //第二步：获取新增的数据 和 更新的数据
        List<Map<String, Object>> createlist = dataToOrgStruMapper.getFromSysGroupCreate(map);
        List<Map<String, Object>> updatelist = dataToOrgStruMapper.getFromSysGroupUpdate(map);

        //第三步：处理Hr新增数据
        Map<String, Object> mapTime = new HashMap<>(16);
        mapTime.put("tableName", domainTableName);
        if (!CollectionUtils.isEmpty(createlist)) {
            numCreate = domainOrgStructureMapper.insertOrgStruAll(createlist);
            this.logger.info("入表domain_org_structure共计:" + numCreate + "条");
            CollectionUtil.listSortDate(createlist, "create_time");
            mapTime.put("maxCreateTime", createlist.get(createlist.size() - 1).get("create_time"));
        } else {
            mapTime.put("maxCreateTime", "");
            this.logger.info("Sqlserver中间库sys_Group表无新增数据");
        }
        if (!CollectionUtils.isEmpty(updatelist)) {
            numUpdate = domainOrgStructureMapper.updateOrgStruAll(updatelist);
            this.logger.info("更新表domain_org_structure共计:" + numUpdate + "条");
            CollectionUtil.listSortDate(updatelist, "update_time");
            mapTime.put("maxUpdateTime", updatelist.get(updatelist.size() - 1).get("update_time"));
        } else {
            mapTime.put("maxUpdateTime", "");
            this.logger.info("Sqlserver中间库sys_Group表无新更新数据");
        }
        if (!CollectionUtils.isEmpty(createlist) || !CollectionUtils.isEmpty(updatelist)) {
            commonDomainMapper.updateMaxTime(mapTime);
        }
        this.logger.info("从Sqlserver中间库sys_Group增量获取数据结束");
        return numCreate + numUpdate;
    }

    /**
     * 全量导入数据 SQLserver表sys_FieldValue到mysql表domain_basic_info表
     * 定时任务 每天晚上00：00全量更新一次
     */
    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int sysFieldValueToBasicInfoAll() {
        this.logger.info("从Sqlserver中间库sys_FieldValue获取数据开始");
        int num = 0;
        List<DomainBasicInfo> list = dataToBasicInfoMapper.getFromSysFieldValueAll();
        this.logger.info("从Sqlserver中间库sys_FieldValue获取数据" + list.size() + "条");
        if (!CollectionUtils.isEmpty(list)) {
            num = domainBasicInfoMapper.insertBasicInfoAll(list);
            this.logger.info("入表domain_basic_info共计:" + num + "条");
        }
        this.logger.info("从Sqlserver中间库sys_FieldValue获取数据结束");
        return num;
    }

    /**
     * 全量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     * 一次性任务24万条数据
     */
    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int personnelToEmployeeInfoAll() {
        this.logger.info("从Sqlserver中间库hr_Personnel获取数据开始");
        int nums = 0;

        List<Map<String, Object>> list = dataToEmployeeInfoMapper.getFromHrPersonnelAll();
        if (!CollectionUtils.isEmpty(list)) {
            this.logger.info("从Sqlserver中间库hr_Personnel获取数据共计" + list.size() + "");
            Map<String, Object> map = new HashMap<>(16);
            String domainTableName = TableNameEnum.TABLE_NAME_EMPLOYEEINFO.getTableName();

            //分批次处理数据
            List<List<Map<String, Object>>> splitList = CollectionUtil.splitList(list);
            //操作主库主数据
            int delete = domainEmployeeInfoMapper.deleteEmployeeInfoAll();
            this.logger.info("删除domain_employee_info中Hr数据共计:" + delete + "条");
            for (int i = 0; i < splitList.size(); i++) {
                int num = domainEmployeeInfoMapper.insertEmployeeInfoAll(splitList.get(i));
                this.logger.info("第" + (i + 1) + "次入表domain_employee_info共计:" + num + "条");
                nums += num;
            }

            CollectionUtil.listSortDate(list, "create_time");
            map.put("maxCreateTime", list.get(list.size() - 1).get("create_time"));
            CollectionUtil.listSortDate(list, "update_time");
            map.put("maxUpdateTime", list.get(list.size() - 1).get("update_time"));
            map.put("tableName", domainTableName);
            this.insertMaxTime(map);

        } else {
            this.logger.info("Sqlserver中间库hr_Personnel表无新增数据");
        }
        this.logger.info("从Sqlserver中间库hr_Personnel获取数据结束");
        return nums;
    }

    /**
     * 增量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     * 定时任务
     */
    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int personnelToEmployeeInfoNew() {
        this.logger.info("从Sqlserver中间库hr_Personnel增量获取数据开始");
        int numCreate = 0;
        int numUpdate = 0;

        //第一步：获取最大创建时间 和 最大更新时间
        String domainTableName = TableNameEnum.TABLE_NAME_EMPLOYEEINFO.getTableName();
        Map<String, Object> map = commonDomainMapper.getMaxTime(domainTableName);

        //第二步：获取新增的数据 和 更新的数据
        List<Map<String, Object>> createlist = dataToEmployeeInfoMapper.getFromHrPersonnelCreate(map);
        List<Map<String, Object>> updatelist = dataToEmployeeInfoMapper.getFromHrPersonnelUpdate(map);

        this.logger.info("从Sqlserver中间库hr_Personnel增量查询数据结束");

        //第三步：处理Hr更新数据
        Map<String, Object> mapTime = new HashMap<>(16);
        mapTime.put("tableName", domainTableName);
        if (!CollectionUtils.isEmpty(createlist)) {
            numCreate = domainEmployeeInfoMapper.insertEmployeeInfoAll(createlist);
            this.logger.info("入表domain_org_structure共计:" + numCreate + "条");
            CollectionUtil.listSortDate(createlist, "create_time");
            mapTime.put("maxCreateTime", createlist.get(createlist.size() - 1).get("create_time"));
        } else {
            this.logger.info("Sqlserver中间库hr_Personnel表无新增数据");
        }
        if (!CollectionUtils.isEmpty(updatelist)) {
            numUpdate = domainEmployeeInfoMapper.updateEmployeeInfoAll(updatelist);
            this.logger.info("更新表domain_employee_info共计:" + numUpdate + "条");
            CollectionUtil.listSortDate(updatelist, "update_time");
            mapTime.put("maxUpdateTime", updatelist.get(updatelist.size() - 1).get("update_time"));
        } else {
            this.logger.info("Sqlserver中间库hr_Personnel表无新更新数据");
        }
        if (!CollectionUtils.isEmpty(createlist) || !CollectionUtils.isEmpty(updatelist)) {
            commonDomainMapper.updateMaxTime(mapTime);
        }
        this.logger.info("从Sqlserver中间库hr_Personnel增量获取数据结束");
        return numCreate + numUpdate;
    }

    private void insertMaxTime(Map<String, Object> map) {
        commonDomainMapper.insertMaxTime(map);
    }

}
