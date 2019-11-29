package com.gw.domain_hr.mapperdata;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface DataToOrgStruMapper {

    /**
     * sys_group获取全量数据
     *
     * @return
     */
    List<Map<String, Object>> getFromSysGroupAll();

    /**
     * sys_group获取增量数据 新增
     */
    List<Map<String, Object>> getFromSysGroupCreate(Map<String, Object> map);

    /**
     * sys_group获取增量数据 更新
     */
    List<Map<String, Object>> getFromSysGroupUpdate(Map<String, Object> map);

    /**
     * 获取最大时间
     *
     * @return
     */
    Map<String, Object> getMaxTime();

}
