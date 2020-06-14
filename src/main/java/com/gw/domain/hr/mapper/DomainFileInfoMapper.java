package com.gw.domain.hr.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainFileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DomainFileInfoMapper extends BaseMapper<DomainFileInfo> {


    String selectMaxCreateTime();

    String selectMaxUpdateTime();

}




