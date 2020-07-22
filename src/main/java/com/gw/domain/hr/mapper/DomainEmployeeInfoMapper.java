package com.gw.domain.hr.mapper;

import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.entity.po.DomainEmpOrgRequestPO;
import com.gw.domain.hr.entity.po.DomainEmpOrgResponsePO;
import com.gw.domain.common.mapper.DomainBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zoujialiang
 */
@Mapper
@Component
public interface DomainEmployeeInfoMapper extends DomainBaseMapper<DomainEmployeeInfo> {

    /**
     * 获取员工列表
     * @param domainEmpOrgRequestPo
     * @return
     */
    List<DomainEmpOrgResponsePO> employeeList(DomainEmpOrgRequestPO domainEmpOrgRequestPo);

    /**
     * 获取员工列表
     * @param domainEmpOrgRequestPo
     * @return
     */
    List<DomainEmpOrgResponsePO> employeeListAll(DomainEmpOrgRequestPO domainEmpOrgRequestPo);

}




