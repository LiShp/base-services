package com.gw.domain.common.util;

import com.gw.domain.hr.entity.vo.DomainOrgStructureResponseVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsp
 * @date 2019-12-23 14:37
 */
public class TreeUtil {

    /**
     * 根据层级获取对应的树 所有工厂的树、所有车间的树、所有线体的树
     * @param sysOrganizationalunitVos 数据源
     * @param tree
     * @return
     */
    public static DomainOrgStructureResponseVO findChildren(List<DomainOrgStructureResponseVO> sysOrganizationalunitVos, DomainOrgStructureResponseVO tree){
        if(tree != null) {
            Long id = tree.getId();
            List<DomainOrgStructureResponseVO> sysChildren = tree.getSysChildren();
            for(DomainOrgStructureResponseVO treeDB:sysOrganizationalunitVos){
                Long parentId = treeDB.getParentId();
                if(parentId.equals(id)){
                    sysChildren.add(treeDB);
                    findChildren(sysOrganizationalunitVos, treeDB);
                }
            }
            tree.setSysChildren(sysChildren);
        }else{
            tree = new DomainOrgStructureResponseVO();
            tree.setId(0L);
            tree.setSysChildren(new ArrayList<DomainOrgStructureResponseVO>());
            findChildren(sysOrganizationalunitVos, tree);
        }
        return tree;
    }

    public static List<DomainOrgStructureResponseVO> findChildren(List<DomainOrgStructureResponseVO> sysOrganizationalunitVos, List<DomainOrgStructureResponseVO> sysChildren, Long id){
        for(DomainOrgStructureResponseVO treeDB:sysOrganizationalunitVos){
            Long parentId = treeDB.getParentId();
            if(parentId.equals(id)){
                sysChildren.add(treeDB);
                treeDB.getSysChildren().addAll(findChildren(sysOrganizationalunitVos,new ArrayList<>(), treeDB.getId()));
            }
        }
        return sysChildren;
    }

}