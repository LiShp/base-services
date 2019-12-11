package com.gw.domain.hr.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainOrgStructureNode;
import com.gw.domain.hr.entity.DomainOrgStructure;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zoujialiang
 */
@Mapper
@Component
public interface DomainOrgStructureMapper extends BaseMapper<DomainOrgStructure> {

    /**
     * 全量获取组织机构节点
     * @return
     */
    List<DomainOrgStructureNode> getGroupAll();

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




