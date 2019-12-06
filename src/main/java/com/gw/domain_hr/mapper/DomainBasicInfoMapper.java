package com.gw.domain_hr.mapper;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.domain_hr.entity.DomainBasicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface DomainBasicInfoMapper extends BaseMapper<DomainBasicInfo> {

    /**
     * 全量入庫
     *
     * @param list
     * @return
     */
    int insertBasicInfoAll(List<Map<String, Object>> list);

    /**
     * 全量删除Hr入库数据
     *
     * @return
     */
    int deleteBasicInfoAll();

}




