package com.gw.domain.hr.service;

import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.entity.hr.Person;
import com.gw.domain.hr.mapper.DomainEmployeeInfoMapper;
import com.gw.domain.hr.mapperhr.PersonMapper;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zoujialiang
 */
@Service
public class HrDataToDomainService {

    private GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    @Resource
    private DomainEmployeeInfoMapper domainEmployeeInfoMapper;

    @Resource
    private PersonMapper personMapper;


    /**
     * 增量导入数据 SQLserver表hr_Personnel到mysql表domain_employee_info表
     * 定时任务
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int personnelToEmployeeInfoAllUpdate(int limit, int updateLoop) {
        this.logger.info("从HR库中p_person获取增量数据开始");
        int numUpdate = 0;


        Example updateExample = new Example(Person.class);
        updateExample.orderBy("updateTime");
//        updateExample.createCriteria().andGreaterThan("updateTime", domainEmployeeInfoMapper.selectMaxUpdateTime());
        int updateCount = personMapper.selectCountByExample(updateExample);

        int pageSize = 1000;

        //int updateLoop = (updateCount%pageSize==0?updateCount/pageSize:updateCount/pageSize+1);
        for(int i=limit; i<updateLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<Person> fileInfoList = personMapper.selectByExampleAndRowBounds(updateExample, rowBounds);
            List<DomainEmployeeInfo> domainFileInfoList = DozerUtil.convert(fileInfoList, DomainEmployeeInfo.class);
            for(DomainEmployeeInfo employeeInfo : domainFileInfoList) {
                Example example = new Example(DomainEmployeeInfo.class);
                example.createCriteria().andEqualTo("personnelNo", employeeInfo.getPersonnelNo());
                int result = domainEmployeeInfoMapper.updateByExampleSelective(employeeInfo, example);
                if(result == 0){
                    domainEmployeeInfoMapper.insert(employeeInfo);
                }
            }
            this.logger.info("更新-已入表domain_employee_info共计:" + numUpdate + "条");
        }
        this.logger.info("从HR库中p_person获取增量数据结束");
        return numUpdate;
    }
}
