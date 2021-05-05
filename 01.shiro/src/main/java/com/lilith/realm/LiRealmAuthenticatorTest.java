package com.lilith.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Author:JiaJingnan
 * @Date: 下午7:02 2021/5/5
 * 自定义realm认证测试
 */
public class LiRealmAuthenticatorTest {

    @Test
    public void authenticator(){
        //1.创建SecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //2.设置使用的realm
        securityManager.setRealm(new LiRealm());
        //3.安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //4.通过安全工具类获取subject
        Subject subject = SecurityUtils.getSubject();
        //5.创建token，通过用户名密码
        UsernamePasswordToken token = new UsernamePasswordToken("stark", "123");
        //6.执行登陆
        try {
            subject.login(token);
            System.out.println(subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }


    }
}
