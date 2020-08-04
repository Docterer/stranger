package com.scaffold.auth.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author danyiran
 * @create 2020/8/4 14:15
 */
@Configuration
public class ShiroConfig {

    private static final Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 自定义realm
     * @return
     */
    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        log.info("myrealm注册完成");
        return myRealm;
    }

    /**
     * 安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(myRealm());
        log.info("securityManager注册完成");
        return securityManager;
    }

    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        //定义shiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置自定义的 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");

        Map<String,String> filterChainMap = new LinkedHashMap<>();

    }
}
