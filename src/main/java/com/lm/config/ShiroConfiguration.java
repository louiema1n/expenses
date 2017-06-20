package com.lm.config;

import com.lm.realm.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro核心配置
 * Created by Louie on 2017-06-18.
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 核心filter
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 配置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 退出
        filterChainDefinitionMap.put("/logout", "logout");
        // 配置静态资源无需权限访问
        filterChainDefinitionMap.put("/bootstrap/**", "anon");
        filterChainDefinitionMap.put("/bootstrapvalidator/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/easyui/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        // 配置记住我或验证通过可以访问的url
        filterChainDefinitionMap.put("/index", "user");
        filterChainDefinitionMap.put("/", "user");
        // 设置所有url都需要验证才能访问
        filterChainDefinitionMap.put("/**", "authc");

        // 设置登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登陆成功后跳转url
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 设置未授权url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 核心SecurityManager
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 注入自定义realm
        securityManager.setRealm(myShiroRealm());
        // 注入缓存管理器
        securityManager.setCacheManager(ehCacheManager());
        // 注入rememberMe Cookie管理器
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }

    /**
     * 自定义realm
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        // 注入凭证适配器
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 凭证适配器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager());
        // 设置算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列次数
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 开启shiro aop支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Ehcache缓存管理器
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        // 加载Ehcache-shiro配置文件
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return ehCacheManager;
    }

    /**
     * rememberMe Cookie对象
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        // cookie name对应前端CheckBox的name
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 设置cookie生效时间，单位：秒
        simpleCookie.setMaxAge(1 * 60);
        return simpleCookie;
    }

    /**
     * rememberMe Cookie管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

}
