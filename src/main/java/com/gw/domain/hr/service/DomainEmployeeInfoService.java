package com.gw.domain.hr.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gw.cloud.common.base.service.BaseService;
import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.domain.common.util.HttpReturn;
import com.gw.domain.common.util.HttpUtil;
import com.gw.domain.hr.entity.DomainFileInfo;
import com.gw.domain.hr.entity.po.DomainEmpOrgRequestPO;
import com.gw.domain.hr.entity.po.DomainEmpOrgResponsePO;
import com.gw.domain.hr.entity.po.DomainWorkExperienceResponsePO;
import com.gw.domain.hr.entity.vo.DomainEmployeeInfoResponseVO;
import com.gw.domain.hr.entity.vo.DomainWorkExperienceResponseVO;
import com.gw.domain.common.enums.EmployeeTypeEnum;
import com.gw.domain.hr.mapper.DomainEmployeeInfoMapper;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.mapper.DomainFileInfoMapper;
import com.gw.domain.hr.mapper.DomainWorkExperienceMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

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

    @Autowired
    private DomainFileInfoMapper domainFileInfoMapper;

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

    public DomainEmployeeInfoResponseVO employee(DomainEmpOrgRequestPO domainEmpOrgRequestPo){
        List<DomainEmpOrgResponsePO> domainEmpOrgResponsePoList = domainEmployeeInfoMapper.employeeList(domainEmpOrgRequestPo);
        List<DomainEmployeeInfoResponseVO> employeeVOList = null;
        if(!domainEmpOrgResponsePoList.isEmpty()) {
            employeeVOList = DozerUtil.convert(domainEmpOrgResponsePoList, DomainEmployeeInfoResponseVO.class);
            return employeeVOList.get(0);
        }
        return null;
    }

    public String employeePhoto(String personnelNo){

        Example employeeExample = new Example(DomainEmployeeInfo.class);
        employeeExample.createCriteria()
                .andEqualTo("personnelNo", personnelNo);
        DomainEmployeeInfo domainEmployeeInfo = domainEmployeeInfoMapper.selectOneByExample(employeeExample);
        Assert.notNull(domainEmployeeInfo, "员工信息不存在");



        Example fileExample = new Example(DomainFileInfo.class);
        fileExample.createCriteria()
                .andEqualTo("code", domainEmployeeInfo.getPhotoFileCode());
        DomainFileInfo domainFileInfo = domainFileInfoMapper.selectOneByExample(fileExample);

        Assert.notNull(domainFileInfo, "该员工不存在头像信息。");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", userName);
        jsonObject.put("file_name", domainFileInfo.getName());
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

    public QueryResult employeeList(DomainEmpOrgRequestPO domainEmpOrgRequestPo, int page, int rows, Class clazz){
        domainEmpOrgRequestPo.setIsFormal(EmployeeTypeEnum.FORMAL.getCode());
        PageHelper.startPage(page, rows);
        List<DomainEmpOrgResponsePO> domainEmpOrgResponsePoList = domainEmployeeInfoMapper.employeeList(domainEmpOrgRequestPo);
        PageInfo<DomainEmpOrgResponsePO> pageInfo = new PageInfo<>(domainEmpOrgResponsePoList);
        List employeeVOList = null;
        if(!domainEmpOrgResponsePoList.isEmpty()) {
            employeeVOList = DozerUtil.convert(domainEmpOrgResponsePoList, clazz);
        }
        QueryResult queryResult = new QueryResult(pageInfo.getTotal(), employeeVOList, page);
        return queryResult;
    }

    public QueryResult<DomainWorkExperienceResponseVO> employeeWorkList(DomainEmpOrgRequestPO domainEmpOrgRequestPo, int page, int rows){
        domainEmpOrgRequestPo.setIsFormal(EmployeeTypeEnum.FORMAL.getCode());
        PageHelper.startPage(page, rows);
        List<DomainWorkExperienceResponsePO> domainWorkExperienceResponsePOList = domainWorkExperienceMapper.employeeWorkList(domainEmpOrgRequestPo);
        PageInfo<DomainWorkExperienceResponsePO> pageInfo = new PageInfo<>(domainWorkExperienceResponsePOList);
        List workExperienceVoList = null;
        if(!domainWorkExperienceResponsePOList.isEmpty()) {
            workExperienceVoList = DozerUtil.convert(domainWorkExperienceResponsePOList, DomainWorkExperienceResponseVO.class);
        }
        QueryResult<DomainWorkExperienceResponseVO> queryResult = new QueryResult(pageInfo.getTotal(), workExperienceVoList, page);
        return queryResult;
    }

}




