package com.itheima.untils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/5 13:39
 **/

public class PasswordEncoderUntil {
   static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder ();

    public static String encode (String password){
        return bCryptPasswordEncoder.encode (password);
    }

}
