package com.gw.domain.hr.mapperdata;

import com.gw.domain.hr.entity.DomainBasicInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zoujialiang
 */
@Component
public interface DataToBasicInfoMapper {

    /**
     * sys_FieldValue获取全量数据
     * @return
     */
    List<DomainBasicInfo> getFromSysFieldValueAll();
}
