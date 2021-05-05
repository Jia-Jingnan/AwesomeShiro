package com.lilith.md5;

import org.apache.shiro.crypto.hash.Md2Hash;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Author:JiaJingnan
 * @Date: 下午10:14 2021/5/5
 */
public class ShiroMD5 {

    public static void main(String[] args) {

        Md5Hash md5Hash = new Md5Hash();
        md5Hash.setBytes("123".getBytes());

        String s = md5Hash.toHex();
        System.out.println(s);

        // 使用md5加密
        Md5Hash md5Hash1 = new Md5Hash("123");
        System.out.println(md5Hash1.toHex());

        // md5 + salt
        Md5Hash md5Hash2 = new Md5Hash("123", "X0*7ps");
        System.out.println(md5Hash2.toHex());

        // md5 + salt + hash散列
        Md5Hash md5Hash3 = new Md5Hash("123", "X0*7ps", 1024);
        System.out.println(md5Hash3);
    }
}
