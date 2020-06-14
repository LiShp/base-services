package com.gw.domain.hr.service;

import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.domain.hr.commonutils.CollectionUtil;
import com.gw.domain.hr.entity.DomainBasicInfo;
import com.gw.domain.hr.entity.DomainFileInfo;
import com.gw.domain.hr.entity.DomainPosition;
import com.gw.domain.hr.entity.DomainWorkExperience;
import com.gw.domain.hr.entity.hr.WorkExperience;
import com.gw.domain.hr.entity.hrfile.FileInfo;
import com.gw.domain.hr.enums.TableNameEnum;
import com.gw.domain.hr.mapper.*;
import com.gw.domain.hr.mapperdata.DataToBasicInfoMapper;
import com.gw.domain.hr.mapperdata.DataToEmployeeInfoMapper;
import com.gw.domain.hr.mapperdata.DataToOrgStruMapper;
import com.gw.domain.hr.mapperdata.DataToPositionMapper;
import com.gw.domain.hr.mapperhr.WorkExperienceMapper;
import com.gw.domain.hr.mapperhrfile.FileInfoMapper;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

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
    @Resource
    private DataToPositionMapper dataToPositionMapper;
    @Resource
    private DomainPositionMapper domainPositionMapper;

    @Resource
    private FileInfoMapper fileInfoMapper;
    @Resource
    private DomainFileInfoMapper domainFileInfoMapper;

    @Resource
    private WorkExperienceMapper workExperienceMapper;
    @Resource
    private DomainWorkExperienceMapper domainWorkExperienceMapper;



    /**
     * 全量导入数据 SQLserver表HR_Position到mysql表domain_position表
     * 数据量5万多条，一次性任务 只能晚上更新
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int hrPositongToAll() {
        this.logger.info("从Sqlserver中间库HR_Position获取数据开始");
        int nums = 0;
        List<DomainPosition> list = dataToPositionMapper.getFromHrPositionAll();
        if (!CollectionUtils.isEmpty(list)) {
            this.logger.info("从Sqlserver中间库HR_Position获取数据" + list.size() + "条");
            //删除表数据
            int deleteNum = domainPositionMapper.deletePositionAll();
            this.logger.info("从领域服务删除职位信息" + deleteNum + "条");
            //分批次处理数据
            List<List<DomainPosition>> splitList = CollectionUtil.splitList(list);
            for (int i = 0; i < splitList.size(); i++) {
                int num = domainPositionMapper.insertPositionAll(splitList.get(i));
                this.logger.info("第" + (i + 1) + "次入表domain_position共计:" + num + "条");
                nums += num;
            }
            this.logger.info("入表domain_position共计:" + nums + "条");
        }
        this.logger.info("从Sqlserver中间库HR_Position获取数据结束");
        return nums;
    }

    /**
     * 全量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     * 数据量9000多条，一次性任务
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int sysGroupToOrgStruAll() {
        this.logger.info("从Sqlserver中间库sys_Group获取数据开始");
        int nums = 0;
        List<Map<String, Object>> list = dataToOrgStruMapper.getFromSysGroupAll();
        if (!CollectionUtils.isEmpty(list)) {
            Map<String, Object> map = new HashMap<>(16);
            String domainTableName = TableNameEnum.TABLE_NAME_ORGSTRUCTURE.getTableName();
            //操作主库主数据
            int delete = domainOrgStructureMapper.deleteOrgStruAll();
            this.logger.info("删除domain_org_structure中Hr数据共计:" + delete + "条");

            //分批次处理数据
            List<List<Map<String, Object>>> splitList = CollectionUtil.splitList(list);
            for (int i = 0; i < splitList.size(); i++) {
                int num = domainOrgStructureMapper.insertOrgStruAll(splitList.get(i));
                this.logger.info("第" + (i + 1) + "次入表domain_employee_info共计:" + num + "条");
                nums += num;
            }
            this.logger.info("入表domain_org_structure共计:" + nums + "条");

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
        return nums;
    }

    /**
     * 增量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     * 定时任务
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int personnelToEmployeeInfoNew() {
        this.logger.info("从Sqlserver中间库hr_Personnel增量获取数据开始");
        int numCreate = 0;
        int numUpdate = 0;

        //第一步：获取最大创建时间 和 最大更新时间
        String domainTableName = TableNameEnum.TABLE_NAME_EMPLOYEEINFO.getTableName();
        Map<String, Object> map = commonDomainMapper.getMaxTime(domainTableName);
        this.logger.info("1.map-getMaxTime:"+map);
        //第二步：获取新增的数据 和 更新的数据
        List<Map<String, Object>> createlist = dataToEmployeeInfoMapper.getFromHrPersonnelCreate(map);
        List<Map<String, Object>> updatelist = dataToEmployeeInfoMapper.getFromHrPersonnelUpdate(map);
        this.logger.info("2.createlist.size():"+createlist.size());
        this.logger.info("2.updatelist.size():"+updatelist.size());

        this.logger.info("从Sqlserver中间库hr_Personnel增量查询数据结束");

        //第三步：处理Hr更新数据
        Map<String, Object> mapTime = new HashMap<>(16);
        mapTime.put("tableName", domainTableName);

        if (!CollectionUtils.isEmpty(createlist)) {
            int loopsize = createlist.size()%100==0?createlist.size()/100:(createlist.size()/100)+1;
            for(int i =0;i<loopsize;i++) {
                int start = i * 100;
                int end = (i + 1) * 100;
                end = end > createlist.size() ? createlist.size() : end;
                List<Map<String, Object>> createlistTemp = createlist.subList(start, end);
                numCreate += domainEmployeeInfoMapper.insertEmployeeInfoAll(createlistTemp);
                this.logger.info("已入表domain_org_structure共计:" + numCreate + "条");
            }
            this.logger.info("入表domain_org_structure共计:" + numCreate + "条");
            CollectionUtil.listSortDate(createlist, "create_time");
            mapTime.put("maxCreateTime", createlist.get(createlist.size() - 1).get("create_time"));
        } else {
            this.logger.info("Sqlserver中间库hr_Personnel表无新增数据");
        }
        if (!CollectionUtils.isEmpty(updatelist)) {
            int loopsize = updatelist.size()%100==0?updatelist.size()/100:(updatelist.size()/100)+1;
            for(int i =0;i<loopsize;i++){
                int start = i*100;
                int end = (i+1)*100;
                end = end>updatelist.size()?updatelist.size():end;
                List<Map<String, Object>> updatelistTemp = updatelist.subList(start, end);
                numUpdate += domainEmployeeInfoMapper.updateEmployeeInfoAll(updatelistTemp);
                this.logger.info("已更新表domain_employee_info共计:" + numUpdate + "条");
            }
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

    /**
     * 头像信息同步-全量
     * 定时任务
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int fileInfoSyncAll() {
        this.logger.info("从HR库中file_info获取全量数据开始");
        int numCreate = 0;
        int deleteCount = domainFileInfoMapper.deleteByExample(new Example(DomainFileInfo.class));
        this.logger.info("删除全量数据：" + deleteCount);

        Example countExample = new Example(FileInfo.class);
        countExample.orderBy("createtime");
        int count = fileInfoMapper.selectCountByExample(countExample);
        int pageSize = 1000;
        int loop = (count%pageSize==0?count/pageSize:count/pageSize+1);
        for(int i=0; i<loop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<FileInfo> fileInfoList = fileInfoMapper.selectByExampleAndRowBounds(countExample, rowBounds);
            List<DomainFileInfo> domainFileInfoList = DozerUtil.convert(fileInfoList, DomainFileInfo.class);
            numCreate += domainFileInfoMapper.insertList(domainFileInfoList);
            this.logger.info("已入表file_info共计:" + numCreate + "条");
        }
        if(count>0){
            Map<String, Object> mapTime = new HashMap<>(16);
            mapTime.put("tableName", TableNameEnum.TABLE_NAME_FILE.getTableName());
            mapTime.put("maxCreateTime", domainFileInfoMapper.selectMaxCreateTime());
            mapTime.put("maxUpdateTime", domainFileInfoMapper.selectMaxUpdateTime());
            commonDomainMapper.insertMaxTime(mapTime);
        }
        this.logger.info("从HR库中file_info获取全量数据开始结束");
        return numCreate;
    }

    /**
     * 头像信息同步-增量
     * 定时任务
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int fileInfoSyncNew() {
        this.logger.info("从HR库中file_info获取增量数据开始");
        int numCreate = 0;
        int numUpdate = 0;

        Example createExample = new Example(FileInfo.class);
        createExample.orderBy("createtime");
        createExample.createCriteria().andGreaterThan("createtime", domainFileInfoMapper.selectMaxCreateTime());
        int createCount = fileInfoMapper.selectCountByExample(createExample);
        int pageSize = 1000;
        int careteLoop = (createCount%pageSize==0?createCount/pageSize:createCount/pageSize+1);
        for(int i=0; i<careteLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<FileInfo> fileInfoList = fileInfoMapper.selectByExampleAndRowBounds(createExample, rowBounds);
            List<DomainFileInfo> domainFileInfoList = DozerUtil.convert(fileInfoList, DomainFileInfo.class);
            numCreate += domainFileInfoMapper.insertList(domainFileInfoList);
            this.logger.info("新增-已入表domain_file_info共计:" + numCreate + "条");
        }

        Example updateExample = new Example(FileInfo.class);
        updateExample.orderBy("updatetime");
        updateExample.createCriteria().andGreaterThan("updatetime", domainFileInfoMapper.selectMaxUpdateTime());
        int updateCount = fileInfoMapper.selectCountByExample(updateExample);
        int updateLoop = (updateCount%pageSize==0?updateCount/pageSize:updateCount/pageSize+1);
        for(int i=0; i<updateLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<FileInfo> fileInfoList = fileInfoMapper.selectByExampleAndRowBounds(updateExample, rowBounds);
            List<DomainFileInfo> domainFileInfoList = DozerUtil.convert(fileInfoList, DomainFileInfo.class);
            for(DomainFileInfo domainFileInfo : domainFileInfoList) {
                Example example = new Example(DomainWorkExperience.class);
                example.createCriteria().andEqualTo("code", domainFileInfo.getCode());
                numUpdate += domainFileInfoMapper.updateByExampleSelective(domainFileInfo, example);
            }
            this.logger.info("更新-已入表domain_file_info共计:" + numCreate + "条");
        }
        if(createCount > 0 || updateCount > 0){
            Map<String, Object> mapTime = new HashMap<>(16);
            mapTime.put("tableName", TableNameEnum.TABLE_NAME_FILE.getTableName());
            mapTime.put("maxCreateTime", domainFileInfoMapper.selectMaxCreateTime());
            mapTime.put("maxUpdateTime", domainFileInfoMapper.selectMaxCreateTime());
            commonDomainMapper.insertMaxTime(mapTime);
        }
        this.logger.info("从HR库中file_info获取增量数据结束");
        return numCreate + numUpdate;
    }


    /**
     * 工作经历信息同步-全量
     * 定时任务
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int workExperienceSyncAll() {
        this.logger.info("从HR库中p_work_experience获取全量数据开始");
        int numCreate = 0;
        int deleteCount = domainWorkExperienceMapper.deleteByExample(new Example(DomainWorkExperience.class));
        this.logger.info("删除全量数据：" + deleteCount);

        Example countExample = new Example(WorkExperience.class);
        countExample.orderBy("createTime");
        int count = workExperienceMapper.selectCountByExample(countExample);
        int pageSize = 1000;
        int loop = (count%pageSize==0?count/pageSize:count/pageSize+1);
        for(int i=0; i<loop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<WorkExperience> workExperienceList = workExperienceMapper.selectByExampleAndRowBounds(countExample, rowBounds);
            List<DomainWorkExperience> domainWorkExperienceList = DozerUtil.convert(workExperienceList, DomainWorkExperience.class);
            numCreate += domainWorkExperienceMapper.insertList(domainWorkExperienceList);
            this.logger.info("已入表p_work_experience共计:" + numCreate + "条");
        }
        if(count>0){
            Map<String, Object> mapTime = new HashMap<>(16);
            mapTime.put("tableName", TableNameEnum.TABLE_NAME_WORK.getTableName());
            mapTime.put("maxCreateTime", domainWorkExperienceMapper.selectMaxCreateTime());
            mapTime.put("maxUpdateTime", domainWorkExperienceMapper.selectMaxUpdateTime());
            commonDomainMapper.insertMaxTime(mapTime);
        }
        this.logger.info("从HR库中p_work_experience获取全量数据结束");
        return numCreate;
    }

    /**
     * 工作经历信息同步-增量
     * 定时任务
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int workExperienceSyncNew() {
        this.logger.info("从HR库中p_work_experience获取增量数据开始");
        int numCreate = 0;
        int numUpdate = 0;

        Example createExample = new Example(WorkExperience.class);
        createExample.orderBy("createTime");
        createExample.createCriteria().andGreaterThan("createTime", domainWorkExperienceMapper.selectMaxCreateTime());
        int createCount = workExperienceMapper.selectCountByExample(createExample);
        int pageSize = 1000;
        int careteLoop = (createCount%pageSize==0?createCount/pageSize:createCount/pageSize+1);
        for(int i=0; i<careteLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<WorkExperience> workExperienceList = workExperienceMapper.selectByExampleAndRowBounds(createExample, rowBounds);
            List<DomainWorkExperience> domainWorkExperienceList = DozerUtil.convert(workExperienceList, DomainWorkExperience.class);
            numCreate += domainWorkExperienceMapper.insertList(domainWorkExperienceList);
            this.logger.info("新增-已入表domain_work_experience共计:" + numCreate + "条");
        }

        Example updateExample = new Example(WorkExperience.class);
        updateExample.orderBy("updateTime");
        updateExample.createCriteria().andGreaterThan("updateTime", domainFileInfoMapper.selectMaxUpdateTime());
        int updateCount = workExperienceMapper.selectCountByExample(updateExample);
        int updateLoop = (updateCount%pageSize==0?updateCount/pageSize:updateCount/pageSize+1);
        for(int i=0; i<updateLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<WorkExperience> workExperienceList = workExperienceMapper.selectByExampleAndRowBounds(updateExample, rowBounds);
            List<DomainWorkExperience> domainWorkExperienceList = DozerUtil.convert(workExperienceList, DomainWorkExperience.class);
            for(DomainWorkExperience domainWorkExperience : domainWorkExperienceList) {
                Example example = new Example(DomainWorkExperience.class);
                example.createCriteria().andEqualTo("personId", domainWorkExperience.getPersonId());
                numUpdate += domainWorkExperienceMapper.updateByExampleSelective(domainWorkExperience, example);
            }
            this.logger.info("更新-已入表domain_work_experience共计:" + numCreate + "条");
        }
        if(createCount > 0 || updateCount > 0){
            Map<String, Object> mapTime = new HashMap<>(16);
            mapTime.put("tableName", TableNameEnum.TABLE_NAME_WORK.getTableName());
            mapTime.put("maxCreateTime", domainWorkExperienceMapper.selectMaxCreateTime());
            mapTime.put("maxUpdateTime", domainWorkExperienceMapper.selectMaxCreateTime());
            commonDomainMapper.insertMaxTime(mapTime);
        }
        this.logger.info("从HR库中p_work_experience获取增量数据结束");
        return numCreate + numUpdate;
    }
    private void insertMaxTime(Map<String, Object> map) {
        commonDomainMapper.insertMaxTime(map);
    }
}
