package com.gw.domain.hr.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainWorkExperience;
import com.gw.domain.hr.entity.po.DomainEmpOrgRequestPo;
import com.gw.domain.hr.entity.po.DomainWorkExperienceResultPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DomainWorkExperienceMapper extends BaseMapper<DomainWorkExperience> {

    String selectMaxCreateTime();

    String selectMaxUpdateTime();

    int employeeWorkListCount(DomainEmpOrgRequestPo domainEmpOrgRequestPo);

    List<DomainWorkExperienceResultPo> employeeWorkList(DomainEmpOrgRequestPo domainEmpOrgRequestPo);


}




