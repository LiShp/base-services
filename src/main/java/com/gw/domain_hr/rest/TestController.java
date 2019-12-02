package com.gw.domain_hr.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @RequestMapping(value = "/urlTest")
    public String urlTest() {
        return "测试探测页面成功";
    }
}
