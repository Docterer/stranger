package com.scaffold.common.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.*;

/**
 * @Author danyiran
 * @create 2020/7/2 00:11
 */
@Configuration
@Slf4j
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        log.info("ShiroConfig-------------------------------------");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //当shiroFilter(SecurityManager securityManager)时，此处为securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());


        //自定义过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //filterMap.put("jwt", new JwtFilter());
        filterMap.put("anon", new AnonymousFilter());
        filterMap.put("logout", new LogoutFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //要拦截的接口
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        //swagger接口权限 开放
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        //放在最后
        //filterChainDefinitionMap.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 将MyShiroRealm的配置注入到IoC容器，
     *
     * @return
     */
//    @Bean
//    public UserRealm userRealm() {
//        log.info("ShiroConfig.myShiroRealm()");
//        UserRealm userRealm = new UserRealm();
//        return adminRealm;
//    }
    @Bean
    public SecurityManager securityManager() {
        log.info("ShiroConfig.securityManager()");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setAuthenticator(modularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        //realms.add(userRealm());
        securityManager.setRealms(realms);

        //关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * 为shiro权限控制开启aop注解支持，这样才能使用@RequiresPermissions等注解
     * 使用代理方式，所以需要开启代码支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        System.out.println("开启Shrio注解");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 当没有访问权限时，会抛出异常，需要自定义异常处理，将没有权限的异常重定向到403页面
     *
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        System.out.println("自定义异常处理");
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        //授权异常处理
        mappings.setProperty("UnauthorizedException", "403");
        // None by default
        resolver.setExceptionMappings(mappings);
        // No default
        resolver.setDefaultErrorView("error");
        // Default is "exception"
        resolver.setExceptionAttribute("ex");
        return resolver;
    }
}
