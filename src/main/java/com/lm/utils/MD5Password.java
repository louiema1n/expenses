package com.lm.utils;

import com.lm.domain.shiro.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 根据username，password进行md5加密
 * Created by Louie on 2017-06-19.
 */
public class MD5Password {
    public static User md5PasswordUser(User user) {
        // 获取salt
        SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator();
        String salt= secureRandomNumberGenerator.nextBytes().toHex();
        //组合username,两次迭代，对密码进行加密
        String password_cipherText= new Md5Hash(user.getPassword(),user.getUsername()+salt,2).toHex();
        user.setPassword(password_cipherText);
        user.setSalt(salt);
        return user;
    }
}
