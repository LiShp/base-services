package com.gw.domain.hr.rest;

import com.gw.domain.hr.service.DomainOrgStructureService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * DomainOrgStructureController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 12, 2019</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainOrgStructureControllerTest {

    @Autowired
    DomainOrgStructureController domainOrgStructureController;

    @Autowired
    DomainOrgStructureService domainOrgStructureService;


    @Autowired
    WebApplicationContext wac;
    MockMvc mvc;

    @Before
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @After
    public void after() throws Exception {
    }

    /**
     * Method: selectDomainOrgStructureByEnableTime(@ApiParam(name = "startDatetime", value = "开始时间") @RequestParam String startDatetime, @ApiParam(name = "endDatetime", value = "结束时间") @RequestParam String endDatetime, @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page, @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows)
     */
    @Test
    public void testSelectDomainOrgStructureByEnableTime() throws Exception {
        String startDatetime = "2019-12-13 12:12:30";
        String endDatetime = "2019-12-14 12:12:30";
        Integer page = 1;
        Integer rows = 10;
        domainOrgStructureController.selectDomainOrgStructureByEnableTime(startDatetime, endDatetime, page, rows);
    }

    /**
     * Method: selectDomainOrgStructureByCreateTime(@ApiParam(name = "startDatetime", value = "开始时间") @RequestParam String startDatetime, @ApiParam(name = "endDatetime", value = "结束时间") @RequestParam String endDatetime, @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page, @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows)
     */
    @Test
    public void testSelectDomainOrgStructureByCreateTime() throws Exception {
        String startDatetime = "2019-12-13 12:12:30";
        String endDatetime = "2019-12-14 12:12:30";
        Integer page = 1;
        Integer rows = 10;
        domainOrgStructureController.selectDomainOrgStructureByCreateTime(startDatetime, endDatetime, page, rows);
    }

    /**
     * Method: selectDomainOrgStructureByUpdateTime(@ApiParam(name = "startDatetime", value = "开始时间") @RequestParam String startDatetime, @ApiParam(name = "endDatetime", value = "结束时间") @RequestParam String endDatetime, @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page, @ApiParam(name = "rows", value = "每页显示条数（默认为10）") @RequestParam(value = "rows", defaultValue = "10") Integer rows)
     */
    @Test
    public void testSelectDomainOrgStructureByUpdateTime() throws Exception {
        String startDatetime = "2019-12-13 12:12:30";
        String endDatetime = "2019-12-14 12:12:30";
        Integer page = 1;
        Integer rows = 10;
        domainOrgStructureController.selectDomainOrgStructureByUpdateTime(startDatetime, endDatetime, page, rows);
    }

    /**
     * Method: getGroupById(@PathVariable("id") Integer id)
     */
    @Test
    public void testGetGroupById() throws Exception {
       Integer groupId = 5862;
        domainOrgStructureController.getGroupById(groupId);
    }


} 
