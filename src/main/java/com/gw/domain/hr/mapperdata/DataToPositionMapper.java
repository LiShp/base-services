package com.gw.domain.hr.mapperdata;

import com.gw.domain.hr.entity.DomainPosition;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zoujialiang
 */
@Component
public interface DataToPositionMapper {

    /**
     * HR_Position获取全量数据
     * @return
     */
    List<DomainPosition> getFromHrPositionAll();

}
