package com.gw.domain.hr.rest;

import com.gw.domain.hr.service.DomainBasicInfoService;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zoujialiang
 */
@Api(value="/domainbasicinfo", description="")
@RestController
@RequestMapping("/domainbasicinfo")
public class DomainBasicInfoController {

    protected GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    @Autowired
    private DomainBasicInfoService domainBasicInfoService;


}




