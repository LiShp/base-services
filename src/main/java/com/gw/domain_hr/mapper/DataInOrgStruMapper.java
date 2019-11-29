package com.gw.domain_hr.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface DataInOrgStruMapper {

    /**
     * 全量入庫（入库前先清楚groupcode不为空的数据）
     * @param list
     * @return
     */
    int insertOrgStruAll(List<Map<String, Object>> list);

    /**
     * 批量更新数据
     * @param list
     * @return
     */
    int updateOrgStruAll(List<Map<String, Object>> list);

    /**
     * 全量删除Hr入库数据
     *
     * @return
     */
    int deleteOrgStruAll();

    /**
     * 入库最大创建时间和最大更新时间
     * @param map
     * @return
     */
    int insertMaxTime(Map<String, Object> map);

    /**
     * 更新最大创建时间和最大更新时间
     * @param map
     * @return
     */
    int updateMaxTime(Map<String, Object> map);

    /**
     * 查询最大创建时间和最大更新时间
     * @return
     */
    Map<String, Object>  getMaxTime();

}
