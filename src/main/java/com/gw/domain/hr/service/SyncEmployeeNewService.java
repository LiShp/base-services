package com.gw.domain.hr.service;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.entity.hr.Person;
import com.gw.domain.hr.mapper.DomainEmployeeInfoMapper;
import com.gw.domain.hr.common.mapper.DomainBaseMapper;
import com.gw.domain.hr.mapperhr.PersonMapper;
import com.gw.domain.hr.service.template.AbstractSyncNewTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author gwx
 * @description 同步增量员工信息服务
 * @date 2020-07-11
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SyncEmployeeNewService extends AbstractSyncNewTemplate<Person, DomainEmployeeInfo> {

    @Resource
    private DomainEmployeeInfoMapper domainEmployeeInfoMapper;

    @Resource
    private PersonMapper personMapper;

    @Override
    public BaseMapper getHrMapper() {
        return personMapper;
    }

    @Override
    public DomainBaseMapper getDomainMapper() {
        return domainEmployeeInfoMapper;
    }
}
