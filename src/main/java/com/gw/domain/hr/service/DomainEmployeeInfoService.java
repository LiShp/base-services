package com.gw.domain.hr.service;

import com.gw.cloud.common.base.service.BaseService;
import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.domain.hr.entity.po.DomainEmpOrgRequestPo;
import com.gw.domain.hr.entity.po.DomainEmpOrgResultPo;
import com.gw.domain.hr.entity.vo.DomainEmployeeInfoVO;
import com.gw.domain.hr.mapper.DomainEmployeeInfoMapper;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author zoujialiang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DomainEmployeeInfoService extends BaseService<Long,DomainEmployeeInfo> {

    @Autowired
    private DomainEmployeeInfoMapper domainEmployeeInfoMapper;
    public QueryResult<DomainEmployeeInfo> selectDomainEmployeeInfoByCreateTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainEmployeeInfo.class);
        example.createCriteria().andBetween("createTime", startDatetime,endDatetime);
        return this.paginateQueryResultByExample(example,page,rows);
    }
    public QueryResult<DomainEmployeeInfo> selectDomainEmployeeInfoByUpdateTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainEmployeeInfo.class);
        example.createCriteria().andBetween("updateTime", startDatetime,endDatetime);
        return this.paginateQueryResultByExample(example,page,rows);
    }

    public int insertEmployeeInfoSingle(DomainEmployeeInfo domainEmployeeInfo) {
        Date createTime = domainEmployeeInfo.getCreateTime();
        Date updateTime = domainEmployeeInfo.getUpdateTime();
        if (null == createTime) {
            domainEmployeeInfo.setCreateTime(new Date());
        }
        if (null == updateTime) {
            domainEmployeeInfo.setUpdateTime(new Date());
        }
        int num = domainEmployeeInfoMapper.insertEmployeeInfoSingle(domainEmployeeInfo);
        return num;
    }

    public int updateEmployeeInfoSingleByPersonnelNo(DomainEmployeeInfo domainEmployeeInfo) {
        Date updateTime = domainEmployeeInfo.getUpdateTime();
        if (null == updateTime) {
            domainEmployeeInfo.setUpdateTime(new Date());
        }
        int num = domainEmployeeInfoMapper.updateEmployeeInfoSingleByPersonnelNo(domainEmployeeInfo);
        return num;
    }

    public DomainEmployeeInfoVO employee(DomainEmpOrgRequestPo domainEmpOrgRequestPo){
        domainEmpOrgRequestPo.setPage(-1);
        List<DomainEmpOrgResultPo> domainEmpOrgResultPoList = domainEmployeeInfoMapper.employeeList(domainEmpOrgRequestPo);
        List<DomainEmployeeInfoVO> employeeVOList = null;
        if(!domainEmpOrgResultPoList.isEmpty()) {
            employeeVOList = DozerUtil.convert(domainEmpOrgResultPoList, DomainEmployeeInfoVO.class);
            return employeeVOList.get(0);
        }
        return null;
    }

    public QueryResult employeeList(DomainEmpOrgRequestPo domainEmpOrgRequestPo, int page, int rows, Class clazz){

        domainEmpOrgRequestPo.setPage((page-1)*rows);
        domainEmpOrgRequestPo.setSize(rows);

        long totalRecords = domainEmployeeInfoMapper.employeeListCount(domainEmpOrgRequestPo);
        List<DomainEmpOrgResultPo> domainEmpOrgResultPoList = domainEmployeeInfoMapper.employeeList(domainEmpOrgRequestPo);
        List employeeVOList = null;
        if(!domainEmpOrgResultPoList.isEmpty()) {
            employeeVOList = DozerUtil.convert(domainEmpOrgResultPoList, clazz);
        }
        QueryResult queryResult = new QueryResult(totalRecords, employeeVOList, page);
        return queryResult;
    }

}




