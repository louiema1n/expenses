package com.lm.utils;

import com.lm.domain.EasyuiTree;
import com.lm.domain.shiro.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * 将role转换成tree
 * Created by Louie on 2017-06-22.
 */
public class RoleToTree {
    /**
     * 将role实体转换成EasyuiTree
     * @param role
     * @return
     */
    public EasyuiTree roleToNode(Role role, List<Role> userRoles) {
        EasyuiTree node = new EasyuiTree();
        // 如果该用户角色包含当前角色，设为true
        for (Role userRole : userRoles) {
            if (userRole.getRole().equals(role.getRole())) {
                node.setChecked(true);
            }
        }
        node.setId(role.getId());
        node.setText(role.getDescription());
        node.setState("");
        node.setChildren(new ArrayList<EasyuiTree>());
        return node;
    }

    /**
     * 生成最终的tree json
     * @param roles
     * @return
     */
    public List<EasyuiTree> roleToTree(List<Role> roles, long rootId, List<Role> userRoles) {
        List<EasyuiTree> trees = new ArrayList<>();
        // 根节点从0开始生成
        buildTree(roles, trees, rootId, userRoles);
        return trees;
    }

    /**
     * 递归将子节点加入children
     * @param roles
     * @param trees
     * @param pid
     */
    private void buildTree(List<Role> roles, List<EasyuiTree> trees, long pid, List<Role> userRoles) {
        // 遍历roles
        for (Role role : roles) {
            // 如果当前role id和pid相等，则将和该pid相等id的role加入当前id的子节点
            if (pid == role.getPid()) {
                // 将role转化成node
                EasyuiTree tree = roleToNode(role, userRoles);
                // 将该节点加入trees
                trees.add(tree);
                // 递归，为新节点查找子节点
                buildTree(roles, tree.getChildren(), tree.getId(), userRoles);
            }
        }
    }
}
