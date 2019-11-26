package com.gw.domain-hr.service;

import com.gw.cloud.common.base.service.BaseService;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.domain-hr.mapper.DomainBasicInfoMapper;
import com.gw.domain-hr.entity.DomainBasicInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class DomainBasicInfoService extends BaseService<Long,DomainBasicInfo> {

    @Autowired
    private DomainBasicInfoMapper domainBasicInfoMapper;
    public QueryResult<DomainBasicInfo> selectDomainBasicInfoByCreateTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainBasicInfo.class);
        example.createCriteria().andBetween("createTime", startDatetime,endDatetime);
        return this.paginateQueryResultByExample(example,page,rows);
    }
    public QueryResult<DomainBasicInfo> selectDomainBasicInfoByUpdateTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainBasicInfo.class);
        example.createCriteria().andBetween("updateTime", startDatetime,endDatetime);
        return this.paginateQueryResultByExample(example,page,rows);
    }

}




