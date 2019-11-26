package com.gw.domain_hr.service;

import com.gw.cloud.common.base.service.BaseService;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.domain_hr.mapper.DomainOrgStructureMapper;
import com.gw.domain_hr.entity.DomainOrgStructure;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class DomainOrgStructureService extends BaseService<Long,DomainOrgStructure> {

    @Autowired
    private DomainOrgStructureMapper domainOrgStructureMapper;
    public QueryResult<DomainOrgStructure> selectDomainOrgStructureByEnableTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainOrgStructure.class);
        example.createCriteria().andBetween("enableTime", startDatetime,endDatetime);
        return this.paginateQueryResultByExample(example,page,rows);
    }
    public QueryResult<DomainOrgStructure> selectDomainOrgStructureByCreateTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainOrgStructure.class);
        example.createCriteria().andBetween("createTime", startDatetime,endDatetime);
        return this.paginateQueryResultByExample(example,page,rows);
    }
    public QueryResult<DomainOrgStructure> selectDomainOrgStructureByUpdateTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainOrgStructure.class);
        example.createCriteria().andBetween("updateTime", startDatetime,endDatetime);
        return this.paginateQueryResultByExample(example,page,rows);
    }

}




