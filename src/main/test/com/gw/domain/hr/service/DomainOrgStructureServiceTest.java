package com.gw.domain.hr.service;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * DomainOrgStructureService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 12, 2019</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainOrgStructureServiceTest {

    @Autowired
    DomainOrgStructureService domainOrgStructureService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: selectDomainOrgStructureByEnableTime(String startDatetime, String endDatetime, int page, int rows)
     */
    @Test
    public void testSelectDomainOrgStructureByEnableTime() throws Exception {
        String startDatetime = "2019-09-05 12:12:30";
        String endDatetime = "2019-09-05 12:14:30";
        int page = 1;
        int rows = 10;
        domainOrgStructureService.selectDomainOrgStructureByEnableTime(startDatetime,endDatetime,page,rows);
    }

    /**
     * Method: selectDomainOrgStructureByCreateTime(String startDatetime, String endDatetime, int page, int rows)
     */
    @Test
    public void testSelectDomainOrgStructureByCreateTime() throws Exception {
        String startDatetime = "2019-09-05 12:12:30";
        String endDatetime = "2019-09-05 12:14:30";
        int page = 1;
        int rows = 10;
        domainOrgStructureService.selectDomainOrgStructureByCreateTime(startDatetime,endDatetime,page,rows);
    }

    /**
     * Method: selectDomainOrgStructureByUpdateTime(String startDatetime, String endDatetime, int page, int rows)
     */
    @Test
    public void testSelectDomainOrgStructureByUpdateTime() throws Exception {
        String startDatetime = "2019-09-05 12:12:30";
        String endDatetime = "2019-09-05 12:14:30";
        int page = 1;
        int rows = 10;
        domainOrgStructureService.selectDomainOrgStructureByUpdateTime(startDatetime,endDatetime,page,rows);
    }

    /**
     * Method: getGroupById(int groupCode)
     */
    @Test
    public void testGetGroupById() throws Exception {
        Integer groupId = 201;
        domainOrgStructureService.getGroupById(groupId);
    }


} 
