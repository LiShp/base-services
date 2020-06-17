package com.gw.domain.hr.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainBasicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author zoujialiang
 */
@Mapper
@Component
public interface DomainBasicInfoMapper extends BaseMapper<DomainBasicInfo> {

    String selectMaxCreateTime();

    String selectMaxUpdateTime();

}




