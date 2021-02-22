package com.cjc.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2021/2/22
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 **/
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean
    getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager webSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(webSecurityManager);

        // 添加过滤器
        /*
            anno: 无需认证
            authc: 需要认证
            user: 必须拥有记住我才能用
            perms: 拥有对某个资源的权限
            role: 拥有某个角色
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/user/*","authc");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");





        // 设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");




        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean(name="userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

    // 整合thymeleaf和shiro
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}
