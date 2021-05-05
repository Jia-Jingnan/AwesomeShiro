package com.lilith.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author:JiaJingnan
 * @Date: 下午7:00 2021/5/5
 * 自定义realm实现，将认证/授权的数据来源转为数据库实现
 */
public class LiRealm extends AuthorizingRealm {


    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // token中获取用户名
        String principal = (String) token.getPrincipal();
        System.out.println(principal);
        // 根据身份信息使用jdbc或者mybatis查询数据库
        if ("stark".equals(principal)){
            // 参数1：返回数据库中正确的用户名；参数2：返回的数据库中的密码，参数3：提供当前realm的名字，this.getName()
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("stark","123",this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
