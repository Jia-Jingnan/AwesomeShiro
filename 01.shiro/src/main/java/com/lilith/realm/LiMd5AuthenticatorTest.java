package com.lilith.realm;

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

    }
}
