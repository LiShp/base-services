package com.gw.domain.common.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain.hr.entity.vo.DomainOrgStructureResponseVO;

import java.util.List;

/**
 * @author gwx
 * @date 2020-07-11
 */
public interface DomainBaseMapper<T> extends BaseMapper<T> {

    /**
     * 获取表的最大创建时间
     * @return
     */
    String selectMaxCreateTime();

    /**
     * 获取表的最大更新时间
     * @return
     */
    String selectMaxUpdateTime();

}




