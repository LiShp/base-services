package com.gw.domain_hr.mapperdata;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CommonHrMapper {

    /**
     * 获取最大时间
     *
     * @return
     */
    Map<String, Object> getMaxTime(@Param("tableName") String tableName);

    /**
     * 获取最大时间
     *
     * @return
     */
    Map<String, Object> getPersonnelMaxTime(@Param("tableName") String tableName);

}


