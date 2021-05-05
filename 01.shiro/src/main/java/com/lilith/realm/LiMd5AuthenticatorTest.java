package com.lilith.realm;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author:JiaJingnan
 * @Date: 下午10:30 2021/5/5
 */
public class LiMd5AuthenticatorTest {

    @Test
    public void Md5Authenticator(){

        //1.创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //2.注入realm
        LiMd5Realm liMd5Realm = new LiMd5Realm();
        // 设置realm使用hash凭证匹配
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 使用散列算法，散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        liMd5Realm.setCredentialsMatcher(hashedCredentialsMatcher);
        securityManager.setRealm(liMd5Realm);


        //3.安全管理器注入安全工具类
        SecurityUtils.setSecurityManager(securityManager);
        // 获取subject
        Subject subject = SecurityUtils.getSubject();

        // 认证
        UsernamePasswordToken token = new UsernamePasswordToken("stark", "123");

        // 明文密码登陆
        try {
            subject.login(token);
            System.out.println("登陆成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }

        // 认证用户进行授权
        if (subject.isAuthenticated()){
             // 1.基于角色控制权限
            System.out.println(subject.hasRole("admin"));
            // 基于多角色权限控制
            subject.hasAllRoles(Arrays.asList("admin","user","super"));
            // 是否具有其中一个角色
            subject.hasRoles(Arrays.asList("admin","super"));

            System.out.println("==========");
            // 基于权限字符串的访问控制，资源标识符
            System.out.println(subject.isPermitted("user:*:*"));
        }


    }
}
