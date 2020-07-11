package com.gw.domain.hr.service;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainFileInfo;
import com.gw.domain.hr.entity.hrfile.FileInfo;
import com.gw.domain.hr.mapper.DomainFileInfoMapper;
import com.gw.domain.hr.common.mapper.DomainBaseMapper;
import com.gw.domain.hr.mapperhrfile.FileInfoMapper;
import com.gw.domain.hr.service.template.SyncNewTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author gwx
 * @description 同步增量头像服务
 * @date 2020-07-11
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SyncFileInfoNewService extends SyncNewTemplate<FileInfo, DomainFileInfo> {

    @Resource
    private DomainFileInfoMapper domainFileInfoMapper;

    @Resource
    private FileInfoMapper fileInfoMapper;

    @Override
    public BaseMapper getHrMapper() {
        return fileInfoMapper;
    }

    @Override
    public DomainBaseMapper getDomainMapper() {
        return domainFileInfoMapper;
    }
}
