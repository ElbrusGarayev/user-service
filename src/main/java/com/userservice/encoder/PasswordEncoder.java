package com.userservice.encoder;

import org.apache.commons.codec.digest.DigestUtils;


public class PasswordEncoder {

    public static String sha256(String password){
        return DigestUtils.sha256Hex(password);
    }
}
