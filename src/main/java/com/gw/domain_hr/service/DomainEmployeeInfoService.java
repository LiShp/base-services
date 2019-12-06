package com.gw.domain_hr.service;

import com.gw.cloud.common.base.service.BaseService;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.domain_hr.mapper.DomainEmployeeInfoMapper;
import com.gw.domain_hr.entity.DomainEmployeeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
@Transactional
public class DomainEmployeeInfoService extends BaseService<Long,DomainEmployeeInfo> {

    @Autowired
    private DomainEmployeeInfoMapper domainEmployeeInfoMapper;
    public QueryResult<DomainEmployeeInfo> selectDomainEmployeeInfoByCreateTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainEmployeeInfo.class);
        example.createCriteria().andBetween("createTime", startDatetime,endDatetime);
        return this.paginateQueryResultByExample(example,page,rows);
    }
    public QueryResult<DomainEmployeeInfo> selectDomainEmployeeInfoByUpdateTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainEmployeeInfo.class);
        example.createCriteria().andBetween("updateTime", startDatetime,endDatetime);
        return this.paginateQueryResultByExample(example,page,rows);
    }

    public int insertEmployeeInfoSingle(DomainEmployeeInfo domainEmployeeInfo) {
        Date createTime = domainEmployeeInfo.getCreateTime();
        Date updateTime = domainEmployeeInfo.getUpdateTime();
        if (null == createTime) {
            domainEmployeeInfo.setCreateTime(new Date());
        }
        if (null == updateTime) {
            domainEmployeeInfo.setUpdateTime(new Date());
        }
        int num = domainEmployeeInfoMapper.insertEmployeeInfoSingle(domainEmployeeInfo);
        return num;
    }

    public int updateEmployeeInfoSingleByPersonnelNo(DomainEmployeeInfo domainEmployeeInfo) {
        Date updateTime = domainEmployeeInfo.getUpdateTime();
        if (null == updateTime) {
            domainEmployeeInfo.setUpdateTime(new Date());
        }
        int num = domainEmployeeInfoMapper.updateEmployeeInfoSingleByPersonnelNo(domainEmployeeInfo);
        return num;
    }

}




