package com.gw.domain.hr.service;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainFileInfo;
import com.gw.domain.hr.entity.hrfile.FileInfo;
import com.gw.domain.hr.mapper.DomainFileInfoMapper;
import com.gw.domain.hr.common.mapper.DomainBaseMapper;
import com.gw.domain.hr.mapperhrfile.FileInfoMapper;
import com.gw.domain.hr.service.template.SyncAllTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author gwx
 * @description 同步全量员工头像信息服务
 * @date 2020-07-11
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SyncFileInfoAllService extends SyncAllTemplate<FileInfo, DomainFileInfo> {

    @Resource
    private FileInfoMapper fileInfoMapper;

    @Resource
    private DomainFileInfoMapper domainFileInfoMapper;

    @Override
    public BaseMapper getHrMapper() {
        return fileInfoMapper;
    }

    @Override
    public DomainBaseMapper getDomainMapper() {
        return domainFileInfoMapper;
    }
}
