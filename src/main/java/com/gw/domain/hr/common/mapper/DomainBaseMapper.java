package com.gw.domain.hr.common.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;

/**
 * @author gwx
 * @date 2020-07-11
 */
public interface DomainBaseMapper<T> extends BaseMapper<T> {

    String selectMaxCreateTime();

    String selectMaxUpdateTime();
}




