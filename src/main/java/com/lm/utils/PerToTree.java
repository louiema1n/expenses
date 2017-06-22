package com.lm.utils;

import com.lm.domain.EasyuiTree;
import com.lm.domain.shiro.Permission;
import com.lm.domain.shiro.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * 将role转换成tree
 * Created by Louie on 2017-06-22.
 */
public class PerToTree {
    /**
     * 将role实体转换成EasyuiTree
     * @param permission
     * @return
     */
    public EasyuiTree perToNode(Permission permission, List<Permission> rolePers) {
        EasyuiTree node = new EasyuiTree();
        // 如果该用户角色包含当前角色，设为true
        for (Permission rolePer : rolePers) {
            if (rolePer.getName().equals(permission.getName())) {
                node.setChecked(true);
            }
        }
        node.setId(permission.getId());
        node.setText(permission.getName());
        node.setState("");
        node.setChildren(new ArrayList<EasyuiTree>());
        return node;
    }

    /**
     * 生成最终的tree json
     * @param permissions
     * @return
     */
    public List<EasyuiTree> perToTree(List<Permission> permissions, long rootId, List<Permission> rolePers) {
        List<EasyuiTree> trees = new ArrayList<>();
        // 根节点从0开始生成
        buildTree(permissions, trees, rootId, rolePers);
        return trees;
    }

    /**
     * 递归将子节点加入children
     * @param permissions
     * @param trees
     * @param pid
     */
    private void buildTree(List<Permission> permissions, List<EasyuiTree> trees, long pid, List<Permission> rolePers) {
        // 遍历roles
        for (Permission permission : permissions) {
            // 如果当前role id和pid相等，则将和该pid相等id的role加入当前id的子节点
            if (pid == permission.getPid()) {
                // 将role转化成node
                EasyuiTree tree = perToNode(permission, rolePers);
                // 将该节点加入trees
                trees.add(tree);
                // 递归，为新节点查找子节点
                buildTree(permissions, tree.getChildren(), tree.getId(), rolePers);
            }
        }
    }
}
