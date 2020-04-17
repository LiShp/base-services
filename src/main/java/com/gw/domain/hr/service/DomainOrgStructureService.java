package com.gw.domain.hr.service;

import com.gw.cloud.common.base.service.BaseService;
import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.entity.DomainOrgStructureNode;
import com.gw.domain.hr.entity.vo.DomainOrgStructureVO;
import com.gw.domain.hr.entity.vo.EmployeeOrgVO;
import com.gw.domain.hr.entity.vo.EmployeeVO;
import com.gw.domain.hr.entity.vo.NodeVO;
import com.gw.domain.hr.mapper.DomainOrgStructureMapper;
import com.gw.domain.hr.entity.DomainOrgStructure;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zoujialiang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DomainOrgStructureService extends BaseService<Long, DomainOrgStructure> {

    @Autowired
    private DomainOrgStructureMapper domainOrgStructureMapper;

    @Autowired
    private DomainEmployeeInfoService domainEmployeeInfoService;

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
     * @param
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
     * 遍历组织并携带用户信息
     * @param groupId
     * @return
     */
    public NodeVO getDepthGroupById(Long groupId) {

        //查询当前组织信息
        //查询当前组织下的子组织信息
        Example example = new Example(DomainOrgStructure.class);
        example.createCriteria().andEqualTo("deleteFlag", "0")
                .andEqualTo("id", groupId);
        DomainOrgStructure currentOrg = domainOrgStructureMapper.selectOneByExample(example);

        NodeVO currentNode = new NodeVO();
        currentNode.setKey(currentOrg.getId().toString());
        currentNode.setTitle(currentOrg.getGroupName());
        currentNode.setChildren(getChildren(new ArrayList<>(), groupId));
        return currentNode;
    }
    private List<NodeVO> getChildren(List<NodeVO> nodeVOList, Long groupId){
        //查询当前组织下的员工信息
        DomainEmployeeInfo domainEmployeeInfo = new DomainEmployeeInfo();
        domainEmployeeInfo.setDeleteFlag(Boolean.FALSE);
        domainEmployeeInfo.setPersonnelStatus(1);
        domainEmployeeInfo.setGroupId(groupId.intValue());
        List<DomainEmployeeInfo> domainEmployeeInfoList = domainEmployeeInfoService.selectList(domainEmployeeInfo);
        List<NodeVO> nodeVOS = new ArrayList<>();
        for(DomainEmployeeInfo info : domainEmployeeInfoList){
            NodeVO nodeVO = new NodeVO();
            nodeVO.setType(1);
            nodeVO.setTitle(info.getName());
            nodeVO.setKey(info.getPersonnelNo());
            nodeVOS.add(nodeVO);
        }
        nodeVOList.addAll(nodeVOS);
        //查询当前组织下的子组织信息
        Example example = new Example(DomainOrgStructure.class);
        example.createCriteria().andEqualTo("deleteFlag", "0")
                .andEqualTo("parentId", groupId);
        List<DomainOrgStructure> orgStructureList = domainOrgStructureMapper.selectByExample(example);
        for(DomainOrgStructure info : orgStructureList){
            //遍历子组织下的人员信息/组织信息
            NodeVO nodeVO = new NodeVO();
            nodeVO.setType(2);
            nodeVO.setTitle(info.getGroupName());
            nodeVO.setKey(info.getId().toString());
            List<NodeVO> childNodes = getChildren(new ArrayList<>(), info.getId());
            nodeVO.setChildren(childNodes);
            nodeVOList.add(nodeVO);
        }
        return nodeVOList;
    }
    /**
     * 遍历组织并携带用户信息
     * @param groupId
     * @return
     */
    public List<EmployeeOrgVO> getDepthGroupListById(Long groupId) {
        //查询当前组织信息
        //查询当前组织下的子组织信息
        Example example = new Example(DomainOrgStructure.class);
        example.createCriteria().andEqualTo("deleteFlag", "0")
                .andEqualTo("id", groupId);
        DomainOrgStructure currentOrg = domainOrgStructureMapper.selectOneByExample(example);
        Assert.notNull(currentOrg, "当前组织不存在");
        Map<Long, DomainOrgStructure> orgIdMap = new HashMap<>();
        orgIdMap.put(groupId, currentOrg);

        orgIdMap.putAll(findChildrenOrgIds(orgIdMap));

        Example employeeExample = new Example(DomainEmployeeInfo.class);

        employeeExample.createCriteria().andIn("groupId", orgIdMap.keySet())
                .andEqualTo("personnelStatus", 1);

        List<DomainEmployeeInfo> employeeInfoList = domainEmployeeInfoService.selectListByExample(employeeExample);

        List<EmployeeOrgVO> employeeOrgVOList = new ArrayList<>();
        for(DomainEmployeeInfo employeeInfo : employeeInfoList){
            EmployeeOrgVO employeeOrgVO = new EmployeeOrgVO();
            employeeOrgVO.setName(employeeInfo.getName());
            employeeOrgVO.setPersonnelNo(employeeInfo.getPersonnelNo());
            employeeOrgVO.setId(employeeInfo.getId());
            employeeOrgVO.setGroupName(orgIdMap.get(Long.valueOf(employeeInfo.getGroupId())).getGroupName());
            Integer parentId = orgIdMap.get(Long.valueOf(employeeInfo.getGroupId())).getParentId();
            employeeOrgVO.setParentName(orgIdMap.get(Long.valueOf(parentId)).getGroupName());
            employeeOrgVOList.add(employeeOrgVO);
        }
        return employeeOrgVOList;
    }

    private Map<Long, DomainOrgStructure> findChildrenOrgIds(Map<Long, DomainOrgStructure> orgIdMapTemp){
        Map<Long, DomainOrgStructure> orgIdMapAll = new HashMap<>();
        for(DomainOrgStructure domainOrgStructure : orgIdMapTemp.values()){
            Map<Long, DomainOrgStructure> orgIdMap = new HashMap<>();
            Example example = new Example(DomainOrgStructure.class);
            example.createCriteria().andEqualTo("deleteFlag", "0")
                    .andEqualTo("parentId", domainOrgStructure.getId());
            List<DomainOrgStructure> orgStructureList = domainOrgStructureMapper.selectByExample(example);
            for(DomainOrgStructure orgStructure : orgStructureList){
                orgIdMap.put(orgStructure.getId(), orgStructure);
            }
            orgIdMapAll.putAll(orgIdMap);
            orgIdMapAll.putAll(findChildrenOrgIds(orgIdMap));
        }
        return orgIdMapAll;
    }

}




