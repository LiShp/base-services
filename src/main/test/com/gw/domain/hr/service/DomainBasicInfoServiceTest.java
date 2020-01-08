package com.gw.domain.hr.service;

import com.gw.domain.hr.DomainHrApplication;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * DomainBasicInfoService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 12, 2019</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainBasicInfoServiceTest {

    @Autowired
    DomainBasicInfoService domainBasicInfoService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: selectDomainBasicInfoByCreateTime(String startDatetime, String endDatetime, int page, int rows)
     */
    @Test
    public void testSelectDomainBasicInfoByCreateTime() throws Exception {
        String startDatetime = "2019-09-05 12:12:30";
        String endDatetime = "2019-09-05 12:14:30";
        int page = 1;
        int rows = 10;
        domainBasicInfoService.selectDomainBasicInfoByCreateTime(startDatetime,endDatetime,page,rows);
    }

    /**
     * Method: selectDomainBasicInfoByUpdateTime(String startDatetime, String endDatetime, int page, int rows)
     */
    @Test
    public void testSelectDomainBasicInfoByUpdateTime() throws Exception {
        String startDatetime = "2019-09-05 12:12:30";
        String endDatetime = "2019-09-05 12:14:30";
        int page = 1;
        int rows = 10;
        domainBasicInfoService.selectDomainBasicInfoByUpdateTime(startDatetime,endDatetime,page,rows);
    }


} 
