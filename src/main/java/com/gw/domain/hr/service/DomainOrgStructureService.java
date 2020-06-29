package com.gw.domain.hr.service;

import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.entity.vo.DomainOrgStructureVO;
import com.gw.domain.hr.entity.vo.NodeVO;
import com.gw.domain.hr.entity.vo.OrgStructureVO;
import com.gw.domain.hr.enums.EmployeeTypeEnum;
import com.gw.domain.hr.enums.NodeTypeEnum;
import com.gw.domain.hr.mapper.DomainOrgStructureMapper;
import com.gw.domain.hr.entity.DomainOrgStructure;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
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
public class DomainOrgStructureService  {

    @Autowired
    private DomainOrgStructureMapper domainOrgStructureMapper;

    @Autowired
    private DomainEmployeeInfoService domainEmployeeInfoService;


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
        currentNode.setType(NodeTypeEnum.ROG.getCode());
        currentNode.setChildren(getChildren(new ArrayList<>(), groupId));
        return currentNode;
    }
    private List<NodeVO> getChildren(List<NodeVO> nodeVOList, Long groupId){
        //查询当前组织下的员工信息
        DomainEmployeeInfo domainEmployeeInfo = new DomainEmployeeInfo();
        domainEmployeeInfo.setDeleteFlag(Boolean.FALSE);
        domainEmployeeInfo.setPersonnelStatus(1);
        domainEmployeeInfo.setGroupId(groupId.intValue());
        domainEmployeeInfo.setIsFormal(EmployeeTypeEnum.FORMAL.getCode());
        List<DomainEmployeeInfo> domainEmployeeInfoList = domainEmployeeInfoService.selectList(domainEmployeeInfo);
        List<NodeVO> nodeVOS = new ArrayList<>();
        for(DomainEmployeeInfo info : domainEmployeeInfoList){
            NodeVO nodeVO = new NodeVO();
            nodeVO.setType(NodeTypeEnum.EMP.getCode());
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
            nodeVO.setType(NodeTypeEnum.ROG.getCode());
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
    public List<List<String>> getDepthGroupListById(Long groupId) {
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
                .andEqualTo("personnelStatus", 1)
                .andEqualTo("isFormal", EmployeeTypeEnum.FORMAL.getCode());
        List<DomainEmployeeInfo> employeeInfoList = domainEmployeeInfoService.selectListByExample(employeeExample);

        List<List<String>> employeeOrgVOList = new ArrayList<>();
        for(DomainEmployeeInfo employeeInfo : employeeInfoList){
            List<String> list = new ArrayList<>();
            list.add(employeeInfo.getPersonId().toString());
            list.add(employeeInfo.getPersonnelNo());
            list.add(employeeInfo.getName());
            list.add(employeeInfo.getGroupId().toString());
            list.add(orgIdMap.get(Long.valueOf(employeeInfo.getGroupId())).getGroupName());
            Integer parentId = orgIdMap.get(Long.valueOf(employeeInfo.getGroupId())).getParentId();
            if(groupId.equals(Long.valueOf(employeeInfo.getGroupId()))){
                parentId = employeeInfo.getGroupId();
            }
            list.add(orgIdMap.get(Long.valueOf(parentId)).getGroupName());
            employeeOrgVOList.add(list);
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

    /**
     * 获取组织列表
     * @param createTime
     * @param updateTime
     * @param page
     * @param rows
     * @return
     */
    public QueryResult<OrgStructureVO> getGroupList(String createTime,
                                                    String updateTime,
                                                    Integer page,
                                                    Integer rows) {
        Example example = new Example(DomainOrgStructure.class);

        Example.Criteria criteria =  example.createCriteria();
        if(!StringUtils.isEmpty(createTime)){
            criteria.andGreaterThan("createTime", createTime);
        }
        if(!StringUtils.isEmpty(updateTime)){
            criteria.andGreaterThan("updateTime", updateTime);
        }
        RowBounds rowBounds = new RowBounds(rows, (page-1)*rows);
        long totalRecords = domainOrgStructureMapper.selectCountByExample(example);
        List<DomainOrgStructure> orgStructureList = domainOrgStructureMapper.selectByExampleAndRowBounds(example, rowBounds);//domainOrgStructureMapper.selectByExampleAndRowBounds(example, rowBounds);
        List<OrgStructureVO> orgStructureVOList = DozerUtil.convert(orgStructureList, OrgStructureVO.class);
        QueryResult<OrgStructureVO> queryResult = new QueryResult(totalRecords, orgStructureVOList, page);
        return queryResult;
    }

}




