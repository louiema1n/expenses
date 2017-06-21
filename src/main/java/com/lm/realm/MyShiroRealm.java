package com.lm.realm;

import com.lm.domain.shiro.Permission;
import com.lm.domain.shiro.Role;
import com.lm.domain.shiro.User;
import com.lm.service.shiro.PermissionService;
import com.lm.service.shiro.RoleService;
import com.lm.service.shiro.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 身份验证核心类
 * Created by Louie on 2017-06-18.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 获取当前登录用户
        String username = (String) super.getAvailablePrincipal(principalCollection);
        User user = this.userService.findByUsername(username);
        // 授权
        if (user != null) {
            // 设置角色
            for (Role role : this.roleService.findRoleByUid(user.getUid())) {
                authorizationInfo.addRole(role.getRole());
                // 设置权限
                for (Permission permission : this.permissionService.findPerByRoleid(role.getId())) {
                    authorizationInfo.addStringPermission(permission.getPermission());
                }
            }
            return authorizationInfo;
        }

        return null;
    }

    /**
     * 认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取输入的用户名
        String username = (String) token.getPrincipal();
        // 查询数据库是否有此用户名
        User user = this.userService.findByUsername(username);
        if (user == null) {
            // 用户名不存在
            throw new UnknownAccountException();
        }
        if (user.getState() == 2) {
            // 已锁定
            throw new LockedAccountException();
        }

        // 进行密码验证
        SimpleAuthenticationInfo authentication = new SimpleAuthenticationInfo(
                username,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),   // 加密方式
                getName());                                         // realm name
        return authentication;
    }
}
