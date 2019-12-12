package com.gw.domain.hr.rest;
import com.gw.domain.hr.service.DomainBasicInfoService;
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
* DomainBasicInfoController Tester. 
* 
* @author <Authors name> 
* @since <pre>十二月 12, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainBasicInfoControllerTest {

    @Autowired
    DomainBasicInfoController domainBasicInfoController;

    @Autowired
    DomainBasicInfoService domainBasicInfoService;

    @Autowired
    WebApplicationContext wac;
    MockMvc mvc;

    @Before
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

/** 
* 
* Method: selectDomainBasicInfoByCreateTime(@ApiParam(name = "startDatetime",value = "开始时间")@RequestParam String startDatetime, @ApiParam(name = "endDatetime",value = "结束时间")@RequestParam String endDatetime, @ApiParam(name = "page",value = "页码（默认为1）") @RequestParam(value = "page",defaultValue = "1")Integer page, @ApiParam(name = "rows",value = "每页显示条数（默认为10）") @RequestParam(value = "rows",defaultValue = "10")Integer rows) 
* 
*/ 
@Test
public void testSelectDomainBasicInfoByCreateTime() throws Exception {
    String startDatetime = "2019-12-13 12:12:30";
    String endDatetime = "2019-12-14 12:12:30";
    Integer page = 1;
    Integer rows = 10;
    domainBasicInfoController.selectDomainBasicInfoByCreateTime(startDatetime, endDatetime, page, rows);
} 

/** 
* 
* Method: selectDomainBasicInfoByUpdateTime(@ApiParam(name = "startDatetime",value = "开始时间")@RequestParam String startDatetime, @ApiParam(name = "endDatetime",value = "结束时间")@RequestParam String endDatetime, @ApiParam(name = "page",value = "页码（默认为1）") @RequestParam(value = "page",defaultValue = "1")Integer page, @ApiParam(name = "rows",value = "每页显示条数（默认为10）") @RequestParam(value = "rows",defaultValue = "10")Integer rows) 
* 
*/ 
@Test
public void testSelectDomainBasicInfoByUpdateTime() throws Exception {
    String startDatetime = "2019-12-13 12:12:30";
    String endDatetime = "2019-12-14 12:12:30";
    Integer page = 1;
    Integer rows = 10;
    domainBasicInfoController.selectDomainBasicInfoByUpdateTime(startDatetime, endDatetime, page, rows);
} 


} 
