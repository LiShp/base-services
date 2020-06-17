package com.gw.domain.hr.mapperhr;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.hr.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PersonMapper extends BaseMapper<Person> {

}




