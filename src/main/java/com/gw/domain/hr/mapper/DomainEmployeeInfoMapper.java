package com.gw.domain.hr.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.entity.po.DomainEmpOrgRequestPo;
import com.gw.domain.hr.entity.po.DomainEmpOrgResultPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zoujialiang
 */
@Mapper
@Component
public interface DomainEmployeeInfoMapper extends BaseMapper<DomainEmployeeInfo> {

    /**
     * 单条新增
     * @param domainEmployeeInfo
     * @return
     */
    int insertEmployeeInfoSingle(DomainEmployeeInfo domainEmployeeInfo);

    /**
     * 单条更新按工号
     * @param domainEmployeeInfo
     * @return
     */
    int updateEmployeeInfoSingleByPersonnelNo(DomainEmployeeInfo domainEmployeeInfo);


    int employeeListCount(DomainEmpOrgRequestPo domainEmpOrgRequestPo);

    List<DomainEmpOrgResultPo> employeeList(DomainEmpOrgRequestPo domainEmpOrgRequestPo);



    String selectMaxCreateTime();

    String selectMaxUpdateTime();
}




