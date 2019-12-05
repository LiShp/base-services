package com.gw.domain_hr.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain_hr.entity.DomainOrgStructure;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface DomainOrgStructureMapper extends BaseMapper<DomainOrgStructure> {

    List<Map<String, Object>> getGroupALL();

}




