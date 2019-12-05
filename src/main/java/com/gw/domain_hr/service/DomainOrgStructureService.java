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
import java.util.Map;

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
    public List<Map<String, Object>> getGroupById(int groupCode) {
        List<Map<String, Object>> queryList = domainOrgStructureMapper.getGroupALL();//获取所有的节点信息
        List<Map<String, Object>> parentList = new ArrayList<>();//父节点
        for (Map<String, Object> map : queryList) {
            if ((int) map.get("id") == groupCode) {
                parentList.add(map);
            }
        }
        getChildren(parentList, queryList);
        return parentList;
    }

    /**
     * 递归获取子节点
     * @param parentList
     * @param queryList
     */
    public static void getChildren(List<Map<String, Object>> parentList, List<Map<String, Object>> queryList) {
        for (Map<String, Object> parentMap : parentList) {
            List<Map<String, Object>> childrenlist = new ArrayList<>();
            for (Map<String, Object> allMap : queryList) {
                if ((int) parentMap.get("id") == (int) allMap.get("parent_id")) {
                    childrenlist.add(allMap);
                }
            }
            if (!CollectionUtils.isEmpty(childrenlist)) {
                parentMap.put("children", childrenlist);
                getChildren(childrenlist, queryList);
            }
        }
    }
}




