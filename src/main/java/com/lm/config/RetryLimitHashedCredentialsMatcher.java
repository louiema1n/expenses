package com.lm.config;

import com.lm.service.shiro.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 重写HashedCredentialsMatcher，实现密码多次错误限制(连续5次错误锁定1分钟，再次错误永久锁定)
 * Created by Louie on 2017-06-20.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private UserService userService;

    // Atomic线程安全的
    private Cache<String, AtomicInteger> retryLimitCache;

    // 构造函数获取缓存对象
    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        this.retryLimitCache = cacheManager.getCache("retryLimitCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        // 从缓存中取出重试次数
        AtomicInteger retryCount = retryLimitCache.get(username);
        if (retryCount == null) {
            // 不存在，则设为0
            retryCount = new AtomicInteger(0);
            // 放入缓存
            retryLimitCache.put(username, retryCount);
        }
        // 执行父类的doCredentialsMatch
        boolean match = super.doCredentialsMatch(token, info);
        if (match) {
            if (retryCount.get() < 5) {
                // 验证通过且小于5次，清除RetryCount
                retryLimitCache.remove(username);
            } else if (retryCount.get() == 5) {
                // 连续错误5次,警告用户
                throw new ExcessiveAttemptsException();
            }
        }else {
            // 验证失败，自增
            retryCount.incrementAndGet();
            if (retryCount.get() == 5) {
                // 连续错误5次,警告用户
                throw new ExcessiveAttemptsException();
            }
            if (retryCount.get() > 5){
                // 永久锁定账户
                this.userService.lockUserByUsername(username);
                throw new LockedAccountException();
            }
        }
        return match;
    }
}
