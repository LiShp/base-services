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

}
