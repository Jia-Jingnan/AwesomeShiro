package com.lilith.authc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @Author:JiaJingnan
 * @Date: 下午6:03 2021/5/5
 */
public class LiAuthenticator {

    public static void main(String[] args) {

        //1.创建安全管理器对象SecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2.给安全管理器设置realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));

        //3.全局工具类SecurityUtils
        // 首先给工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        //4.关键对象 subject 主体
        Subject subject = SecurityUtils.getSubject();

        // 5.使用要登陆的用户名及密码创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("stark", "123");

        try {
            // 6.执行登陆
            System.out.println("认证状态:" + subject.isAuthenticated());
            subject.login(token);
            System.out.println("认证状态:" + subject.isAuthenticated());
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
