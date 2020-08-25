package com.gw.domain.hr.service;

import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.domain.common.util.TreeUtil;
import com.gw.domain.hr.entity.DomainEmployeeInfo;
import com.gw.domain.hr.entity.vo.DomainOrgStructureResponseVO;
import com.gw.domain.hr.entity.vo.NodeVO;
import com.gw.domain.hr.entity.vo.OrgStructureResponseVO;
import com.gw.domain.common.enums.EmployeeTypeEnum;
import com.gw.domain.common.enums.NodeTypeEnum;
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

    public List<DomainOrgStructureResponseVO> getGroupById(int groupId) {
        //获取所有的节点信息
        Example example = new Example(DomainOrgStructure.class);
        example.createCriteria().andEqualTo("deleteFlag", "0")
                .andEqualTo("parentId", groupId);
        List<DomainOrgStructure> orgStructureList = domainOrgStructureMapper.selectByExample(example);
        List<DomainOrgStructureResponseVO> employeeVOList = DozerUtil.convert(orgStructureList, DomainOrgStructureResponseVO.class);
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
    public QueryResult<OrgStructureResponseVO> getGroupList(String createTime,
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
        RowBounds rowBounds = new RowBounds((page-1)*rows, rows);
        long totalRecords = domainOrgStructureMapper.selectCountByExample(example);
        List<DomainOrgStructure> orgStructureList = domainOrgStructureMapper.selectByExampleAndRowBounds(example, rowBounds);
        List<OrgStructureResponseVO> orgStructureResponseVOList = DozerUtil.convert(orgStructureList, OrgStructureResponseVO.class);
        QueryResult<OrgStructureResponseVO> queryResult = new QueryResult(totalRecords, orgStructureResponseVOList, page);
        return queryResult;
    }

    public List<DomainOrgStructureResponseVO> getGroupByTree(Integer groupId , Integer level) {
        DomainOrgStructure domainOrgStructure = new DomainOrgStructure();
        domainOrgStructure.setId(groupId.longValue());
        domainOrgStructure.setLevel(level);
        domainOrgStructure.setDeleteFlag(0);
        DomainOrgStructureResponseVO domainOrgStructure1 = domainOrgStructureMapper.selectById(groupId);
        List<DomainOrgStructureResponseVO> treeList= domainOrgStructureMapper.getGroupByTree(domainOrgStructure);
        List<DomainOrgStructureResponseVO> sysChildren = new ArrayList<>();
        return TreeUtil.findChildren(treeList, sysChildren, domainOrgStructure1.getParentId().longValue());
    }

    public List<String> getGroupByName(String groupName) {
        List<DomainOrgStructureResponseVO> domainOrgStructures = domainOrgStructureMapper.getGroupByName(groupName);
        ArrayList<String> list = new ArrayList<>();
        for (DomainOrgStructureResponseVO entity : domainOrgStructures) {
            DomainOrgStructureResponseVO structure = domainOrgStructureMapper.selectByParentId(entity.getParentId());
            String groupName1 = entity.getGroupName()+"("+structure.getGroupName()+")";
            list.add(groupName1);
        }
        return list;
    }
}




