package com.gw.domain_hr.service;

import com.gw.cloud.common.base.service.BaseService;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.domain_hr.mapper.DomainOrgStructureMapper;
import com.gw.domain_hr.entity.DomainOrgStructure;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DomainOrgStructureService extends BaseService<Long, DomainOrgStructure> {

    @Autowired
    private DomainOrgStructureMapper domainOrgStructureMapper;

    public QueryResult<DomainOrgStructure> selectDomainOrgStructureByEnableTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainOrgStructure.class);
        example.createCriteria().andBetween("enableTime", startDatetime, endDatetime);
        return this.paginateQueryResultByExample(example, page, rows);
    }

    public QueryResult<DomainOrgStructure> selectDomainOrgStructureByCreateTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainOrgStructure.class);
        example.createCriteria().andBetween("createTime", startDatetime, endDatetime);
        return this.paginateQueryResultByExample(example, page, rows);
    }

    public QueryResult<DomainOrgStructure> selectDomainOrgStructureByUpdateTime(String startDatetime, String endDatetime, int page, int rows) {

        Example example = new Example(DomainOrgStructure.class);
        example.createCriteria().andBetween("updateTime", startDatetime, endDatetime);
        return this.paginateQueryResultByExample(example, page, rows);
    }


    /**
     * 通过组织编码获取所有组织
     *
     * @param groupCode
     * @return
     */
    public List<DomainOrgStructure> getGroupById(int groupCode) {
        List<DomainOrgStructure> queryList = domainOrgStructureMapper.getGroupALL();//获取所有的节点信息
        List<DomainOrgStructure> parentList = new ArrayList<>();//父节点
        for (DomainOrgStructure map : queryList) {
            if (map.getId().intValue() == groupCode) {
                parentList.add(map);
            }
        }
        getChildren(parentList, queryList);
        return parentList;
    }

    /**
     * 递归获取子节点
     *
     * @param parentList
     * @param queryList
     */
    public static void getChildren(List<DomainOrgStructure> parentList, List<DomainOrgStructure> queryList) {
        for (DomainOrgStructure parentMap : parentList) {
            List<DomainOrgStructure> childrenlist = new ArrayList<>();
            for (DomainOrgStructure allMap : queryList) {
                if (parentMap.getId().intValue() == allMap.getParentId()) {
                    childrenlist.add(allMap);
                }
            }
            if (!CollectionUtils.isEmpty(childrenlist)) {
                parentMap.setList(childrenlist);
                getChildren(childrenlist, queryList);
            }
        }
    }
}




