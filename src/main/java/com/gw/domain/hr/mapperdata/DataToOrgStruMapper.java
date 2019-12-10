package com.gw.domain.hr.mapperdata;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zoujialiang
 */
@Component
public interface DataToOrgStruMapper {

    /**
     * sys_group获取全量数据
     * @return
     */
    List<Map<String, Object>> getFromSysGroupAll();

    /**
     * sys_group获取增量数据 新增
     * @param map
     * @return
     */
    List<Map<String, Object>> getFromSysGroupCreate(Map<String, Object> map);

    /**
     * sys_group获取增量数据 更新
     * @param map
     * @return
     */
    List<Map<String, Object>> getFromSysGroupUpdate(Map<String, Object> map);

}
