package com.gw.domain_hr.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface DataInEmployeeInfoMapper {

    List<Map<String, Object>> getNameById();

}
