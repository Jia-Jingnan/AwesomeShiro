package com.lilith.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @Author:JiaJingnan
 * @Date: 下午10:26 2021/5/5
 * 使用自定义realm
 */
public class LiMd5Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("=============");
        // 获取主用户信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println(primaryPrincipal);
        //根据身份信息，获取该用户的角色信息及权限信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 设置角色权限
        // 将数据库中查询的角色信息赋值给权限对象
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");

        // 将数据库中查询权限信息赋值给权限对象
        simpleAuthorizationInfo.addStringPermission("user:*:01");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取身份信息
        String principal = (String) token.getPrincipal();
        if ("stark".equals(principal)){
            // 数据库用户名，md5+salt之后的密码，随机盐，realm名字
            return new SimpleAuthenticationInfo(principal,"e4f9bf3e0c58f045e62c23c533fcf633",
                    ByteSource.Util.bytes("X0*7ps"),this.getName());
        }
        return null;
    }
}
