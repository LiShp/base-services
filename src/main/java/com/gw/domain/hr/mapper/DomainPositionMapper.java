package com.gw.domain.hr.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.DomainPosition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zoujialiang
 */
@Mapper
@Component
public interface DomainPositionMapper extends BaseMapper<DomainPosition> {

    /**
     * 全量入庫
     * 替换表数据
     * @param list
     * @return
     */
    int insertPositionAll(List<DomainPosition> list);

    /**
     * 全量删除Hr入库数据
     *
     * @return
     */
    int deletePositionAll();

}




