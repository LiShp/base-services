package com.gw.domain.hr.mapper;

import com.gw.domain.hr.entity.DomainOrgStructure;
import com.gw.domain.common.mapper.DomainBaseMapper;
import com.gw.domain.hr.entity.vo.DomainOrgStructureResponseVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zoujialiang
 */
@Mapper
@Component
public interface DomainOrgStructureMapper extends DomainBaseMapper<DomainOrgStructure> {


    List<DomainOrgStructureResponseVO> getGroupByTree(DomainOrgStructure domainOrgStructure);

    DomainOrgStructureResponseVO selectById(Integer groupId);

    List<DomainOrgStructureResponseVO> getGroupByName(String groupName);

    DomainOrgStructureResponseVO selectByParentId(Long id);
}




