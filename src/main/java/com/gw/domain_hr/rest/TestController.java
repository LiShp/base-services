package com.gw.domain_hr.rest;

import com.alibaba.fastjson.JSONObject;
import com.gw.domain_hr.mapper.DataInBasicInfoMapper;
import com.gw.domain_hr.mapperdata.DataToBasicInfoMapper;
import com.gw.domain_hr.mapperdata.DataToOrgStruMapper;
import com.gw.domain_hr.service.HrDataToDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    DataToBasicInfoMapper dataToBasicInfoMapper;
    @Autowired
    DataToOrgStruMapper dataToOrgStruMapper;

    @Autowired
    DataInBasicInfoMapper dataInBasicInfoMapper;
    @Autowired
    HrDataToDomainService hrDataToDomainService;

    /**
     * 测试sqlserver连接
     *
     * @return
     */
    @RequestMapping(value = "/testSqlserverConnect")
    public String testSqlserverConnect() {
        JSONObject jsonObject = new JSONObject();
        List<Map<String, Object>> list = dataToBasicInfoMapper.getFromSysFieldValueAll();
        jsonObject.put("testList", list);
        return jsonObject.toJSONString();
    }
    @RequestMapping(value = "/getFromSysGroupAll")
    public String getFromSysGroupAll() {
        JSONObject jsonObject = new JSONObject();
        List<Map<String, Object>> list = dataToOrgStruMapper.getFromSysGroupAll();
        jsonObject.put("testList", list);
        return jsonObject.toJSONString();
    }


    /**
     * 测试mysql连接
     *
     * @return
     */
    @RequestMapping(value = "/testMysqlConnect")
    public String testMysqlConnect() {
        JSONObject jsonObject = new JSONObject();
        List<Map<String, Object>> list = new ArrayList<>();
        jsonObject.put("testMysqlList", list);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/testString")
    public String testString() {
        return "测试探测页面";
    }
}
