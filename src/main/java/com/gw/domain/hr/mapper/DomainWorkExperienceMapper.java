package com.gw.domain.hr.mapper;

import com.gw.domain.hr.entity.DomainWorkExperience;
import com.gw.domain.hr.entity.po.DomainEmpOrgRequestPo;
import com.gw.domain.hr.entity.po.DomainWorkExperienceResultPo;
import com.gw.domain.hr.common.mapper.DomainBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DomainWorkExperienceMapper extends DomainBaseMapper<DomainWorkExperience> {

    List<DomainWorkExperienceResultPo> employeeWorkList(DomainEmpOrgRequestPo domainEmpOrgRequestPo);


}




