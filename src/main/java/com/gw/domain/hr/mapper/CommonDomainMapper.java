package com.gw.domain.hr.mapper;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zoujialiang
 */
@Component
public interface CommonDomainMapper {

    /**
     * 入库最大创建时间和最大更新时间
     *
     * @param map
     * @return
     */
    int insertMaxTime(Map<String, Object> map);


}
