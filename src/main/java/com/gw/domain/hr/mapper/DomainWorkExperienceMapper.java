package com.gw.domain.hr.mapper;

import com.gw.domain.hr.entity.DomainWorkExperience;
import com.gw.domain.hr.entity.po.DomainEmpOrgRequestPO;
import com.gw.domain.hr.entity.po.DomainWorkExperienceResponsePO;
import com.gw.domain.hr.common.mapper.DomainBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author weixin
 *
 */
@Mapper
@Component
public interface DomainWorkExperienceMapper extends DomainBaseMapper<DomainWorkExperience> {

    /**
     * 获取工作经历列表
     * @param domainEmpOrgRequestPo
     * @return
     */
    List<DomainWorkExperienceResponsePO> employeeWorkList(DomainEmpOrgRequestPO domainEmpOrgRequestPo);


}




