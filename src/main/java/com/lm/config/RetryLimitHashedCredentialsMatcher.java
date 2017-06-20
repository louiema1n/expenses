package com.lm.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 重写HashedCredentialsMatcher，实现密码多次错误限制
 * Created by Louie on 2017-06-20.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
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
        if (retryCount.incrementAndGet() > 4) {
            // 错误次数大于5
            throw new ExcessiveAttemptsException();
        }
        // 执行父类的doCredentialsMatch
        boolean match = super.doCredentialsMatch(token, info);
        if (match) {
            // 验证通过，清除RetryCount
            retryLimitCache.remove(username);
        }
        return match;
    }
}
