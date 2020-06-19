package com.gw.domain.hr.service;

import com.alibaba.fastjson.JSONObject;
import com.gw.cloud.common.base.service.BaseService;
import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.domain.hr.commonutils.HttpReturn;
import com.gw.domain.hr.commonutils.HttpUtil;
import com.gw.domain.hr.entity.po.DomainEmpOrgRequestPo;
import com.gw.domain.hr.entity.po.DomainEmpOrgResultPo;
import com.gw.domain.hr.entity.po.DomainWorkExperienceResultPo;
import com.gw.domain.hr.entity.vo.DomainEmployeeInfoVO;
import com.gw.domain.hr.entity.vo.DomainWorkExperienceVo;
import com.gw.domain.hr.mapper.DomainEmployeeInfoMapper;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.mapper.DomainWorkExperienceMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zoujialiang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DomainEmployeeInfoService extends BaseService<Long,DomainEmployeeInfo> {

    @Autowired
    private DomainEmployeeInfoMapper domainEmployeeInfoMapper;

    @Autowired
    private DomainWorkExperienceMapper domainWorkExperienceMapper;

    @Value("${oss.minio.userName}")
    private String userName;
    @Value("${oss.minio.paasword}")
    private String paasword;
    @Value("${oss.minio.host}")
    private String host;
    @Value("${oss.minio.uri}")
    private String uri;
    @Value("${oss.minio.bucketName}")
    private String bucketName;
    @Value("${oss.minio.expiry}")
    private int expiry;

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

    public String employeePhoto(String personnelNo){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", userName);
        jsonObject.put("file_name", personnelNo.toUpperCase()+".jpg");
        jsonObject.put("bucket_name", bucketName);
        jsonObject.put("expiry", expiry);
        HttpReturn httpReturn = HttpUtil.postJSON(host, uri, jsonObject.toJSONString(),userName, paasword);

        if( httpReturn.getStatus()== HttpStatus.OK.value()){
            JSONObject result = JSONObject.parseObject(httpReturn.getText());
            return "http://"+host+result.getJSONObject("result").getString("file_path");
        } else if(httpReturn.getStatus()== HttpStatus.NOT_FOUND.value()){
            return "该员工没有头像信息";
        } else {
            return "获取头像失败："+httpReturn.getStatus();
        }
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

    public QueryResult<DomainWorkExperienceVo> employeeWorkList(DomainEmpOrgRequestPo domainEmpOrgRequestPo, int page, int rows){

        domainEmpOrgRequestPo.setPage((page-1)*rows);
        domainEmpOrgRequestPo.setSize(rows);

        long totalRecords = domainWorkExperienceMapper.employeeWorkListCount(domainEmpOrgRequestPo);
        List<DomainWorkExperienceResultPo> domainWorkExperienceResultPoList = domainWorkExperienceMapper.employeeWorkList(domainEmpOrgRequestPo);
        List workExperienceVoList = null;
        if(!domainWorkExperienceResultPoList.isEmpty()) {
            workExperienceVoList = DozerUtil.convert(domainWorkExperienceResultPoList, DomainWorkExperienceVo.class);
        }
        QueryResult<DomainWorkExperienceVo> queryResult = new QueryResult(totalRecords, workExperienceVoList, page);
        return queryResult;
    }

}




