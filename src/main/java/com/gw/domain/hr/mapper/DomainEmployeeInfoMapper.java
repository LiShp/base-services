package com.gw.domain.hr.mapper;

import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.entity.po.DomainEmpOrgRequestPo;
import com.gw.domain.hr.entity.po.DomainEmpOrgResultPo;
import com.gw.domain.hr.common.mapper.DomainBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zoujialiang
 */
@Mapper
@Component
public interface DomainEmployeeInfoMapper extends DomainBaseMapper<DomainEmployeeInfo> {

    List<DomainEmpOrgResultPo> employeeList(DomainEmpOrgRequestPo domainEmpOrgRequestPo);

}




