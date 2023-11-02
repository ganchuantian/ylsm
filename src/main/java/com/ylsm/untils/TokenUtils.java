package com.ylsm.untils;

import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TokenUtils {

    public static String encrypt(String token, String route){
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String dstr = dateStr.substring(0, 17) + "00";
        String str = token+route+dstr;
        token = DigestUtils.md5Hex(DigestUtils.md5Hex(DigestUtils.md5Hex(str)));
        return token;
    }

}
