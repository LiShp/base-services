package com.gw.domain.hr.service;

import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.domain.hr.entity.*;
import com.gw.domain.hr.entity.hr.Dictionaries;
import com.gw.domain.hr.entity.hr.Group;
import com.gw.domain.hr.entity.hr.Person;
import com.gw.domain.hr.entity.hr.WorkExperience;
import com.gw.domain.hr.entity.hrfile.FileInfo;
import com.gw.domain.hr.enums.TableNameEnum;
import com.gw.domain.hr.mapper.*;
import com.gw.domain.hr.mapperhr.DictionariesMapper;
import com.gw.domain.hr.mapperhr.GroupMapper;
import com.gw.domain.hr.mapperhr.PersonMapper;
import com.gw.domain.hr.mapperhr.WorkExperienceMapper;
import com.gw.domain.hr.mapperhrfile.FileInfoMapper;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    private DomainOrgStructureMapper domainOrgStructureMapper;

    @Resource
    private DomainBasicInfoMapper domainBasicInfoMapper;

    @Resource
    private DomainEmployeeInfoMapper domainEmployeeInfoMapper;

    @Resource
    private FileInfoMapper fileInfoMapper;

    @Resource
    private DomainFileInfoMapper domainFileInfoMapper;

    @Resource
    private WorkExperienceMapper workExperienceMapper;

    @Resource
    private DomainWorkExperienceMapper domainWorkExperienceMapper;

    @Resource
    private DictionariesMapper dictionariesMapper;

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private PersonMapper personMapper;


    /**
     * 全量导入数据 SQLserver表sys_Group到mysql表domain_org_structure表
     * 数据量9000多条，一次性任务
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int sysGroupToOrgStruAll() {
        this.logger.info("从HR库中o_group获取全量数据开始");
        int numCreate = 0;
        int deleteCount = domainOrgStructureMapper.deleteByExample(new Example(DomainOrgStructure.class));
        this.logger.info("删除全量数据：" + deleteCount);

        Example countExample = new Example(Group.class);
        countExample.orderBy("createTime");
        int count = groupMapper.selectCountByExample(countExample);
        int pageSize = 1000;
        int loop = (count%pageSize==0?count/pageSize:count/pageSize+1);
        for(int i=0; i<loop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<Group> groupList = groupMapper.selectByExampleAndRowBounds(countExample, rowBounds);
            List<DomainOrgStructure> domainOrgStructureList = DozerUtil.convert(groupList, DomainOrgStructure.class);
            numCreate += domainOrgStructureMapper.insertList(domainOrgStructureList);
            this.logger.info("已入表domain_org_structure共计:" + numCreate + "条");
        }
        if(count>0){
            Map<String, Object> mapTime = new HashMap<>(16);
            mapTime.put("tableName", TableNameEnum.TABLE_NAME_ORGSTRUCTURE.getTableName());
            mapTime.put("maxCreateTime", domainOrgStructureMapper.selectMaxCreateTime());
            mapTime.put("maxUpdateTime", domainOrgStructureMapper.selectMaxUpdateTime());
            commonDomainMapper.insertMaxTime(mapTime);
        }
        this.logger.info("从HR库中o_group获取全量数据结束");
        return numCreate;
    }


    /**
     * 全量导入数据 SQLserver表sys_FieldValue到mysql表domain_basic_info表
     * 定时任务 每天晚上00：00全量更新一次
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int sysFieldValueToBasicInfoAll() {
        this.logger.info("从HR库中o_dictionaries获取全量数据开始");
        int numCreate = 0;
        int deleteCount = domainBasicInfoMapper.deleteByExample(new Example(DomainBasicInfo.class));
        this.logger.info("删除全量数据：" + deleteCount);

        Example countExample = new Example(Dictionaries.class);
        countExample.orderBy("createTime");
        int count = dictionariesMapper.selectCountByExample(countExample);
        int pageSize = 1000;
        int loop = (count%pageSize==0?count/pageSize:count/pageSize+1);
        for(int i=0; i<loop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<Dictionaries> dictionariesList = dictionariesMapper.selectByExampleAndRowBounds(countExample, rowBounds);
            List<DomainBasicInfo> domainBasicInfoList = DozerUtil.convert(dictionariesList, DomainBasicInfo.class);
            numCreate += domainBasicInfoMapper.insertList(domainBasicInfoList);
            this.logger.info("已入表domain_basic_info共计:" + numCreate + "条");
        }
        if(count>0){
            Map<String, Object> mapTime = new HashMap<>(16);
            mapTime.put("tableName", TableNameEnum.TABLE_NAME_BASICINFO.getTableName());
            mapTime.put("maxCreateTime", domainBasicInfoMapper.selectMaxCreateTime());
            mapTime.put("maxUpdateTime", domainBasicInfoMapper.selectMaxUpdateTime());
            commonDomainMapper.insertMaxTime(mapTime);
        }
        this.logger.info("从HR库中o_dictionaries获取全量数据结束");
        return numCreate;
    }

    /**
     * 全量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     * 一次性任务24万条数据
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int personnelToEmployeeInfoAll() {
        this.logger.info("从HR库中p_person获取全量数据开始");
        int numCreate = 0;
        int deleteCount = domainEmployeeInfoMapper.deleteByExample(new Example(DomainEmployeeInfo.class));
        this.logger.info("删除全量数据：" + deleteCount);

        Example countExample = new Example(Person.class);
        countExample.orderBy("createTime");
        int count = personMapper.selectCountByExample(countExample);
        int pageSize = 1000;
        int loop = (count%pageSize==0?count/pageSize:count/pageSize+1);
        for(int i=0; i<loop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<Person> groupList = personMapper.selectByExampleAndRowBounds(countExample, rowBounds);
            List<DomainEmployeeInfo> domainOrgStructureList = DozerUtil.convert(groupList, DomainEmployeeInfo.class);
            numCreate += domainEmployeeInfoMapper.insertList(domainOrgStructureList);
            this.logger.info("已入表domain_employee_info共计:" + numCreate + "条");
        }
        if(count>0){
            Map<String, Object> mapTime = new HashMap<>(16);
            mapTime.put("tableName", TableNameEnum.TABLE_NAME_EMPLOYEEINFO.getTableName());
            mapTime.put("maxCreateTime", domainEmployeeInfoMapper.selectMaxCreateTime());
            mapTime.put("maxUpdateTime", domainEmployeeInfoMapper.selectMaxUpdateTime());
            commonDomainMapper.insertMaxTime(mapTime);
        }
        this.logger.info("从HR库p_person中获取全量数据结束");
        return numCreate;
    }
    /**
     * 增量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     * 定时任务
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int personnelToEmployeeInfoAllUpdate() {
        this.logger.info("从HR库中p_person获取增量数据开始");
        int numUpdate = 0;


        Example updateExample = new Example(Person.class);
        updateExample.orderBy("updateTime");
//        updateExample.createCriteria().andGreaterThan("updateTime", domainEmployeeInfoMapper.selectMaxUpdateTime());
        int updateCount = personMapper.selectCountByExample(updateExample);

        int pageSize = 1000;

        int updateLoop = (updateCount%pageSize==0?updateCount/pageSize:updateCount/pageSize+1);
        for(int i=0; i<updateLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<Person> fileInfoList = personMapper.selectByExampleAndRowBounds(updateExample, rowBounds);
            List<DomainEmployeeInfo> domainFileInfoList = DozerUtil.convert(fileInfoList, DomainEmployeeInfo.class);
            for(DomainEmployeeInfo employeeInfo : domainFileInfoList) {
                Example example = new Example(DomainEmployeeInfo.class);
                example.createCriteria().andEqualTo("personnelNo", employeeInfo.getPersonnelNo());
                numUpdate += domainEmployeeInfoMapper.updateByExampleSelective(employeeInfo, example);
            }
            this.logger.info("更新-已入表domain_employee_info共计:" + numUpdate + "条");
        }
        this.logger.info("从HR库中p_person获取增量数据结束");
        return numUpdate;
    }
    /**
     * 增量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     * 定时任务
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int personnelToEmployeeInfoNew() {
        this.logger.info("从HR库中p_person获取增量数据开始");
        int numCreate = 0;
        int numUpdate = 0;

        Example createExample = new Example(Person.class);
        createExample.orderBy("createTime");
        createExample.createCriteria().andGreaterThan("createTime", domainEmployeeInfoMapper.selectMaxCreateTime());
        int createCount = personMapper.selectCountByExample(createExample);

        Example updateExample = new Example(Person.class);
        updateExample.orderBy("updateTime");
        updateExample.createCriteria().andGreaterThan("updateTime", domainEmployeeInfoMapper.selectMaxUpdateTime());
        int updateCount = personMapper.selectCountByExample(updateExample);

        int pageSize = 1000;
        int careteLoop = (createCount%pageSize==0?createCount/pageSize:createCount/pageSize+1);
        for(int i=0; i<careteLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<Person> fileInfoList = personMapper.selectByExampleAndRowBounds(createExample, rowBounds);
            List<DomainEmployeeInfo> domainEmployeeInfoList = DozerUtil.convert(fileInfoList, DomainEmployeeInfo.class);
            numCreate += domainEmployeeInfoMapper.insertList(domainEmployeeInfoList);
            this.logger.info("新增-已入表domain_employee_info共计:" + numCreate + "条");
        }

        int updateLoop = (updateCount%pageSize==0?updateCount/pageSize:updateCount/pageSize+1);
        for(int i=0; i<updateLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<Person> fileInfoList = personMapper.selectByExampleAndRowBounds(updateExample, rowBounds);
            List<DomainEmployeeInfo> domainFileInfoList = DozerUtil.convert(fileInfoList, DomainEmployeeInfo.class);
            for(DomainEmployeeInfo employeeInfo : domainFileInfoList) {
                Example example = new Example(DomainEmployeeInfo.class);
                example.createCriteria().andEqualTo("personnelNo", employeeInfo.getPersonnelNo());
                numUpdate += domainEmployeeInfoMapper.updateByExampleSelective(employeeInfo, example);
            }
            this.logger.info("更新-已入表domain_employee_info共计:" + numUpdate + "条");
        }
        if(createCount > 0 || updateCount > 0){
            Map<String, Object> mapTime = new HashMap<>(16);
            mapTime.put("tableName", TableNameEnum.TABLE_NAME_EMPLOYEEINFO.getTableName());
            mapTime.put("maxCreateTime", domainEmployeeInfoMapper.selectMaxCreateTime());
            mapTime.put("maxUpdateTime", domainEmployeeInfoMapper.selectMaxCreateTime());
            commonDomainMapper.insertMaxTime(mapTime);
        }
        this.logger.info("从HR库中p_person获取增量数据结束");
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

        Example updateExample = new Example(FileInfo.class);
        updateExample.orderBy("updatetime");
        updateExample.createCriteria().andGreaterThan("updatetime", domainFileInfoMapper.selectMaxUpdateTime());
        int updateCount = fileInfoMapper.selectCountByExample(updateExample);

        int pageSize = 1000;
        int careteLoop = (createCount%pageSize==0?createCount/pageSize:createCount/pageSize+1);
        for(int i=0; i<careteLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<FileInfo> fileInfoList = fileInfoMapper.selectByExampleAndRowBounds(createExample, rowBounds);
            List<DomainFileInfo> domainFileInfoList = DozerUtil.convert(fileInfoList, DomainFileInfo.class);
            numCreate += domainFileInfoMapper.insertList(domainFileInfoList);
            this.logger.info("新增-已入表domain_file_info共计:" + numCreate + "条");
        }

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
            this.logger.info("已入表domain_work_experience共计:" + numCreate + "条");
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

        Example updateExample = new Example(WorkExperience.class);
        updateExample.orderBy("updateTime");
        updateExample.createCriteria().andGreaterThan("updateTime", domainFileInfoMapper.selectMaxUpdateTime());
        int updateCount = workExperienceMapper.selectCountByExample(updateExample);

        int pageSize = 1000;
        int createLoop = (createCount%pageSize==0?createCount/pageSize:createCount/pageSize+1);
        for(int i=0; i<createLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<WorkExperience> workExperienceList = workExperienceMapper.selectByExampleAndRowBounds(createExample, rowBounds);
            List<DomainWorkExperience> domainWorkExperienceList = DozerUtil.convert(workExperienceList, DomainWorkExperience.class);
            numCreate += domainWorkExperienceMapper.insertList(domainWorkExperienceList);
            this.logger.info("新增-已入表domain_work_experience共计:" + numCreate + "条");
        }


        int updateLoop = (updateCount%pageSize==0?updateCount/pageSize:updateCount/pageSize+1);
        for(int i=0; i<updateLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<WorkExperience> workExperienceList = workExperienceMapper.selectByExampleAndRowBounds(updateExample, rowBounds);
            List<DomainWorkExperience> domainWorkExperienceList = DozerUtil.convert(workExperienceList, DomainWorkExperience.class);
            for(DomainWorkExperience domainWorkExperience : domainWorkExperienceList) {
                Example example = new Example(DomainWorkExperience.class);
                example.createCriteria().andEqualTo("workExperienceId", domainWorkExperience.getWorkExperienceId());
                numUpdate += domainWorkExperienceMapper.updateByExampleSelective(domainWorkExperience, example);
            }
            this.logger.info("更新-已入表domain_work_experience共计:" + numUpdate + "条");
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
