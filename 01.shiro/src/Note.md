认证：  
1.最终执行用户名比较 SimpleAccountRealm中的doGetAuthenticationInfo  
2.最终密码校验是在 AuthenticatingRealm中的assertCredentialMatch完成

AuthenticatingRealm 认证realm doGetAuthenticationInfo  
AuthorizingRealm 授权realm doGetAuthorizationInfo  
AuthorizingRealm继承了AuthenticatingRealm，具有doGetAuthenticationInfo（认证） 和 doGetAuthorizationInfo（授权）   
SimpleAccountRealm就是继承了AuthorizingRealm,同时拥有认证和授权两个方法  

MD5加密：  
作用：一般用来加密或签名（校验和）  
特点：MD5算法不可逆，如果内容相同，无论执行多少次md5生成的结果始终都是一致的  
生成结果：始终是一个16进制32位长度字符串