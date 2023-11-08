package com.ylsm.untils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TokenUtils {

    public static String encrypt(String token, String route){
        log.info("calculate token. token:{} route:{}", token, route);
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String dstr = dateStr.substring(0, 17) + "00";
        String str = token+route+dstr;
        token = DigestUtils.md5Hex(DigestUtils.md5Hex(DigestUtils.md5Hex(str)));
        return token;
    }

}
