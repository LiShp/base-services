package com.gw.domain.hr.service;

import com.gw.domain.hr.DomainHrApplication;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


/** 
* DomainEmployeeInfoService Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 9, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DomainHrApplication.class)
public class DomainEmployeeInfoServiceTest {

    @Autowired
    DomainEmployeeInfoService domainEmployeeInfoService;

/**
*
* Method: selectDomainEmployeeInfoByCreateTime(String startDatetime, String endDatetime, int page, int rows)
*
*/
@Test
public void testSelectDomainEmployeeInfoByCreateTime() throws Exception {
    String startDatetime = "2019-09-05 12:12:30";
    String endDatetime = "2019-09-05 12:14:30";
    int page = 1;
    int rows = 10;
    domainEmployeeInfoService.selectDomainEmployeeInfoByCreateTime(startDatetime,endDatetime,page,rows);
}

/**
*
* Method: selectDomainEmployeeInfoByUpdateTime(String startDatetime, String endDatetime, int page, int rows)
*
*/
@Test
public void testSelectDomainEmployeeInfoByUpdateTime() throws Exception {
    String startDatetime = "2019-09-05 12:12:30";
    String endDatetime = "2019-09-05 12:14:30";
    int page = 1;
    int rows = 10;
    domainEmployeeInfoService.selectDomainEmployeeInfoByUpdateTime(startDatetime,endDatetime,page,rows);
}

/**
*
* Method: insertEmployeeInfoSingle(DomainEmployeeInfo domainEmployeeInfo)
*
*/
@Test
@Transactional
public void testInsertEmployeeInfoSingle() throws Exception {
    DomainEmployeeInfo domainEmployeeInfo = new DomainEmployeeInfo();
    domainEmployeeInfoService.insertEmployeeInfoSingle(domainEmployeeInfo);
}

/** 
* 
* Method: updateEmployeeInfoSingleByPersonnelNo(DomainEmployeeInfo domainEmployeeInfo) 
* 
*/ 
@Test
@Transactional
public void testUpdateEmployeeInfoSingleByPersonnelNo() throws Exception {
    DomainEmployeeInfo domainEmployeeInfo = new DomainEmployeeInfo();
    domainEmployeeInfoService.updateEmployeeInfoSingleByPersonnelNo(domainEmployeeInfo);
} 

} 
