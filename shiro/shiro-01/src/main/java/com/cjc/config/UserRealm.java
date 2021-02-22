package com.cjc.config;


import com.cjc.entity.User;
import com.cjc.entity.UserManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2021/2/22
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 **/
public class UserRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        logger.info("# 进入doGetAuthorizationInfo");
        SimpleAuthorizationInfo info
                = new SimpleAuthorizationInfo();

        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();

        User user = (User) subject.getPrincipal();

        // 设置权限
        info.setStringPermissions(user.getPermsSet());

        logger.info("# doGetAuthorizationInfo授权完毕");

        return info;
    }
    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.info("## 执行AuthenticationInfo");



        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        // 根据username查找数据库
        User user = UserManager.getUserByUsername(username);
        if(user == null){
            return null;
        }

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser",user);

        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
