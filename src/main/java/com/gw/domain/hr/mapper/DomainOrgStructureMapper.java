package com.gw.domain.hr.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainOrgStructure;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author zoujialiang
 */
@Mapper
@Component
public interface DomainOrgStructureMapper extends BaseMapper<DomainOrgStructure> {

    String selectMaxCreateTime();

    String selectMaxUpdateTime();

}




