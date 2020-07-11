package com.gw.domain.hr.service;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainWorkExperience;
import com.gw.domain.hr.entity.hr.WorkExperience;
import com.gw.domain.hr.mapper.DomainWorkExperienceMapper;
import com.gw.domain.hr.common.mapper.DomainBaseMapper;
import com.gw.domain.hr.mapperhr.WorkExperienceMapper;
import com.gw.domain.hr.service.template.SyncAllTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author gwx
 * @description 同步全量工作经历服务
 * @date 2020-07-11
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SyncWorkAllService extends SyncAllTemplate<WorkExperience, DomainWorkExperience> {

    @Resource
    private WorkExperienceMapper workExperienceMapper;

    @Resource
    private DomainWorkExperienceMapper domainWorkExperienceMapper;

    @Override
    public BaseMapper getHrMapper() {
        return workExperienceMapper;
    }

    @Override
    public DomainBaseMapper getDomainMapper() {
        return domainWorkExperienceMapper;
    }
}
