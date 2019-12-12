package com.gw.domain.hr.mapper;

import org.apache.ibatis.annotations.Param;
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

    /**
     * 入库最大创建时间和最大更新时间
     *
     * @param map
     * @return
     */
    int deleteMaxTime(Map<String, Object> map);


    /**
     * 更新最大创建时间
     *
     * @param map
     * @return
     */
    int updateMaxTime(Map<String, Object> map);

    /**
     * 最大更新时间
     *
     * @param map
     * @return
     */
    int updateMaxUpdateTime(Map<String, Object> map);

    /**
     * 查询最大创建时间和最大更新时间
     * @param tableName
     * @return
     */
    Map<String, Object> getMaxTime(@Param("tableName") String tableName);

}
