package com.lilith.authc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @Author:JiaJingnan
 * @Date: 下午6:03 2021/5/5
 * 认证：最终执行用户名比较 SimpleAccountRealm类中的doGetAuthenticationInfo方法中执行用户名比较
 * 最终密码的校验是在AuthenticatingRealm中 assertCredentialsMathc中完成
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
        } catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("认证失败，用户名不存在");
        } catch (IncorrectCredentialsException e2){
            e2.printStackTrace();
            System.out.println("认证失败，密码不正确");

        }
    }
}
