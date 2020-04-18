package com.gw.domain.hr.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zoujialiang
 */
@Mapper
@Component
public interface DomainEmployeeInfoMapper extends BaseMapper<DomainEmployeeInfo> {

    /**
     * 单条新增
     * @param domainEmployeeInfo
     * @return
     */
    int insertEmployeeInfoSingle(DomainEmployeeInfo domainEmployeeInfo);

    /**
     * 单条更新按工号
     * @param domainEmployeeInfo
     * @return
     */
    int updateEmployeeInfoSingleByPersonnelNo(DomainEmployeeInfo domainEmployeeInfo);

    /**
     * 全量入庫（入库前先清楚groupcode不为空的数据）
     * @param list
     * @return
     */
    int insertEmployeeInfoAll(List<Map<String, Object>> list);

    /**
     * 批量更新数据
     * @param list
     * @return
     */
    int updateEmployeeInfoAll(List<Map<String, Object>> list);

    /**
     * 全量删除Hr入库数据
     *
     * @return
     */
    int deleteEmployeeInfoAll();

}




