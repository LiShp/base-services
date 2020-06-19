package com.gw.domain.hr.rest;

import com.alibaba.fastjson.JSON;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.service.DomainEmployeeInfoService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * DomainEmployeeInfoController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮ���� 9, 2019</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainEmployeeInfoControllerTest {

    @Autowired
    DomainEmployeeInfoController domainEmployeeInfoController;

    @Autowired
    DomainEmployeeInfoService domainEmployeeInfoService;

    @Autowired
    WebApplicationContext wac;
    MockMvc mvc;

    @Before
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    /**
     * Method: selectDomainEmployeeInfoByCreateTime(@ApiParam(name = "startDatetime", value = "��ʼʱ��") @RequestParam String startDatetime, @ApiParam(name = "endDatetime", value = "����ʱ��") @RequestParam String endDatetime, @ApiParam(name = "page", value = "ҳ�루Ĭ��Ϊ1��") @RequestParam(value = "page", defaultValue = "1") Integer page, @ApiParam(name = "rows", value = "ÿҳ��ʾ������Ĭ��Ϊ10��") @RequestParam(value = "rows", defaultValue = "10") Integer rows)
     */
    @Test
    public void testSelectDomainEmployeeInfoByCreateTime() throws Exception {
        String startDatetime = "2019-12-13 12:12:30";
        String endDatetime = "2019-12-14 12:12:30";
        Integer page = 1;
        Integer rows = 10;
        domainEmployeeInfoController.selectDomainEmployeeInfoByCreateTime(startDatetime, endDatetime, page, rows);
    }

    /**
     * Method: selectDomainEmployeeInfoByUpdateTime(@ApiParam(name = "startDatetime", value = "��ʼʱ��") @RequestParam String startDatetime, @ApiParam(name = "endDatetime", value = "����ʱ��") @RequestParam String endDatetime, @ApiParam(name = "page", value = "ҳ�루Ĭ��Ϊ1��") @RequestParam(value = "page", defaultValue = "1") Integer page, @ApiParam(name = "rows", value = "ÿҳ��ʾ������Ĭ��Ϊ10��") @RequestParam(value = "rows", defaultValue = "10") Integer rows)
     */
    @Test
    public void testSelectDomainEmployeeInfoByUpdateTime() throws Exception {
        String startDatetime = "2019-12-13 12:12:30";
        String endDatetime = "2019-12-14 12:12:30";
        Integer page = 1;
        Integer rows = 10;
        domainEmployeeInfoController.selectDomainEmployeeInfoByUpdateTime(startDatetime, endDatetime, page, rows);
    }

    /**
     * Method: insertEmployeeInfoSingleByPersonnelNo(@RequestBody @Valid DomainEmployeeInfo domainEmployeeInfo, Errors errors)
     */
    @Test
    @Transactional
    public void testInsertEmployeeInfoSingleByPersonnelNo() throws Exception {
        DomainEmployeeInfo domainEmployeeInfo = new DomainEmployeeInfo();
        domainEmployeeInfo.setName("刘备");
        domainEmployeeInfo.setPersonnelNo("GGGGGGGG12");
        domainEmployeeInfo.setSex(1);
        domainEmployeeInfo.setRegisterResidence("546456564");
        domainEmployeeInfo.setBloodType(1);
        domainEmployeeInfo.setCardName("dfs");
        domainEmployeeInfo.setCardNo("130630198754785646");
        domainEmployeeInfo.setNativePlace("11");
        domainEmployeeInfo.setComputerAbility(1);
        domainEmployeeInfo.setMarriageStatus(4);
        domainEmployeeInfo.setBirthDate(new Date());
        domainEmployeeInfo.setDwellingType(1);
        domainEmployeeInfo.setDwelling("SS");
        domainEmployeeInfo.setHeight("75");
        domainEmployeeInfo.setWeight("154");
        domainEmployeeInfo.setPolity(1);
        domainEmployeeInfo.setNatives(1);
        domainEmployeeInfo.setDwelling("DD");
        domainEmployeeInfo.setDwellingType(1);
        domainEmployeeInfo.setProfessional("11");
        domainEmployeeInfo.setGraduationSchool("5");
        domainEmployeeInfo.setGraduationDate(new Date());
        domainEmployeeInfo.setEnglishAbility(2);
        domainEmployeeInfo.setComputerAbility(2);
        domainEmployeeInfo.setMobile("13865478956");
        domainEmployeeInfo.setHomePhone("15646");
        domainEmployeeInfo.setWorkType(2);
        domainEmployeeInfo.setPersonnelStatus(5);
        domainEmployeeInfo.setCreatePersonnelNo("232");
        domainEmployeeInfo.setUpdatePersonnelNo("156");
        MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/domainemployeeinfo/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(domainEmployeeInfo))
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        domainEmployeeInfo.setPersonnelStatus(1);
        MvcResult response1 = mvc.perform(MockMvcRequestBuilders.post("/domainemployeeinfo/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(domainEmployeeInfo))
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
//        System.out.println("" + response.getResponse().getContentAsString());
    }


    /**
     * Method: updateEmployeeInfoSingleByPersonnelNo(@RequestBody DomainEmployeeInfo domainEmployeeInfo, @PathVariable("personnelno") String personnelno)
     */
    @Test
    @Transactional
    public void testUpdateEmployeeInfoSingleByPersonnelNo() throws Exception {
        //第一种方法：mock模拟单元测试
        DomainEmployeeInfo domainEmployeeInfo = new DomainEmployeeInfo();
        domainEmployeeInfo.setName("刘备");
        mvc.perform(MockMvcRequestBuilders.put("/domainemployeeinfo/employee/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(domainEmployeeInfo))
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print());
//        MockHttpServletResponse response = mvcResult.getResponse();
//        int sta = response.getStatus();
//        String result = response.getContentAsString();
        //第二种方法：普通单元测试
//        DomainEmployeeInfo domainEmployeeInfo = new DomainEmployeeInfo();
//        domainEmployeeInfo.setName("邹艺一");
    }
} 

