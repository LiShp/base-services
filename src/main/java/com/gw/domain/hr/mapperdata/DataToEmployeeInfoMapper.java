package com.gw.domain.hr.mapperdata;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zoujialiang
 */
@Component
public interface DataToEmployeeInfoMapper {

    /**
     * hr_Personnel获取全量数据
     * @return
     */
    List<Map<String, Object>> getFromHrPersonnelAll();

    /**
     * hr_Personnel获取增量数据 新增
     * @param map
     * @return
     */
    List<Map<String, Object>> getFromHrPersonnelCreate(Map<String, Object> map);

    /**
     * hr_Personnel获取增量数据 更新
     * @param map
     * @return
     */
    List<Map<String, Object>> getFromHrPersonnelUpdate(Map<String, Object> map);

}
