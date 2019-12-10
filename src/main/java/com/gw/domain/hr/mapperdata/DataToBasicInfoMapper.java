package com.gw.domain.hr.mapperdata;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zoujialiang
 */
@Component
public interface DataToBasicInfoMapper {

    /**
     * sys_FieldValue获取全量数据
     * @return
     */
    List<Map<String, Object>> getFromSysFieldValueAll();
}
