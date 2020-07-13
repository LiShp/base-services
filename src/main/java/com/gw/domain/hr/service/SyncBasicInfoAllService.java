package com.gw.domain.hr.service;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainBasicInfo;
import com.gw.domain.hr.entity.hr.Dictionaries;
import com.gw.domain.hr.mapper.DomainBasicInfoMapper;
import com.gw.domain.hr.mapperhr.DictionariesMapper;
import com.gw.domain.hr.service.template.AbstractSyncAllTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author gwx
 * @description 同步全量基础数据服务
 * @date 2020-07-11
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SyncBasicInfoAllService extends AbstractSyncAllTemplate<Dictionaries, DomainBasicInfo> {

    @Resource
    private DomainBasicInfoMapper domainBasicInfoMapper;

    @Resource
    private DictionariesMapper dictionariesMapper;

    @Override
    public BaseMapper getHrMapper() {
        return dictionariesMapper;
    }

    @Override
    public BaseMapper getDomainMapper() {
        return domainBasicInfoMapper;
    }
}
