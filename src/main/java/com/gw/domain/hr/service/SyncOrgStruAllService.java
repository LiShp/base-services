package com.gw.domain.hr.service;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainOrgStructure;
import com.gw.domain.hr.entity.hr.Group;
import com.gw.domain.hr.mapper.DomainOrgStructureMapper;
import com.gw.domain.hr.common.mapper.DomainBaseMapper;
import com.gw.domain.hr.mapperhr.GroupMapper;
import com.gw.domain.hr.service.template.SyncAllTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author gwx
 * @description 同步全量组织结构服务
 * @date 2020-07-11
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SyncOrgStruAllService extends SyncAllTemplate<Group, DomainOrgStructure> {

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private DomainOrgStructureMapper domainOrgStructureMapper;

    @Override
    public BaseMapper getHrMapper() {
        return groupMapper;
    }

    @Override
    public DomainBaseMapper getDomainMapper() {
        return domainOrgStructureMapper;
    }
}
