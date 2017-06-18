package com.lm.realm;

import com.lm.domain.shiro.User;
import com.lm.service.shiro.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
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

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
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
        System.out.println("身份验证开始...");

        // 获取输入的用户名
        String username = (String) token.getPrincipal();
        // 查询数据库是否有此用户名
        User user = this.userService.findByUsername(username);
        if (user == null) {
            return null;
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
