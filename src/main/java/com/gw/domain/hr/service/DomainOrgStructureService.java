package com.gw.domain.hr.service;

import com.gw.cloud.common.base.service.BaseService;
import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.domain.hr.entity.DomainOrgStructureNode;
import com.gw.domain.hr.entity.vo.DomainOrgStructureVO;
import com.gw.domain.hr.entity.vo.EmployeeVO;
import com.gw.domain.hr.mapper.DomainOrgStructureMapper;
import com.gw.domain.hr.entity.DomainOrgStructure;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zoujialiang
 */
@Service
@Transactional(rollbackFor = Exception.class)
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
    /*public List<DomainOrgStructureNode> getGroupById(int groupCode) {
        //获取所有的节点信息
        List<DomainOrgStructureNode> queryList = domainOrgStructureMapper.getGroupAll();
        //父节点
        List<DomainOrgStructureNode> parentList = new ArrayList<>();
        int size = queryList.size();
        for (int i = 0; i < size; i++) {
            if (queryList.get(i).getId().intValue() == groupCode) {
                parentList.add(queryList.get(i));
            }
        }
        getChildren(parentList, queryList);
        return parentList;
    }*/
    public List<DomainOrgStructureVO> getGroupById(int groupId) {
        //获取所有的节点信息
        Example example = new Example(DomainOrgStructure.class);
        example.createCriteria().andEqualTo("deleteFlag", "0")
                .andEqualTo("parentId", groupId);
        List<DomainOrgStructure> orgStructureList = domainOrgStructureMapper.selectByExample(example);
        List<DomainOrgStructureVO> employeeVOList = DozerUtil.convert(orgStructureList, DomainOrgStructureVO.class);
        return employeeVOList;
    }

    /**
     * 递归获取子节点
     *
     * @param parentList
     * @param queryList
     */
    public static void getChildren(List<DomainOrgStructureNode> parentList, List<DomainOrgStructureNode> queryList) {
        for (DomainOrgStructureNode parentMap : parentList) {
            List<DomainOrgStructureNode> childrenlist = new ArrayList<>();
            int size = queryList.size();
            for (int i = 0; i < size; i++) {
                if (parentMap.getId().intValue() == queryList.get(i).getParentId()) {
                    childrenlist.add(queryList.get(i));
                }
            }
            if (!CollectionUtils.isEmpty(childrenlist)) {
                parentMap.setList(childrenlist);
                getChildren(childrenlist, queryList);
            }
        }
    }

}




