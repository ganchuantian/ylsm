package com.ylsm.service;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.support.WebContentGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Data
@Accessors(chain = true)
@Slf4j
public abstract class AbstractApiRequestService<T> {

    private String url;

    private Map<String, Object> header;

    private Map<String, Object> param;

    private String method = WebContentGenerator.METHOD_POST;

    private Object body;
/*
    public void addHeader(String key, String value) {
        if () {

        }
    }


    public Object request() {

    }

    public T sendRequest() {

    }*/


    public static void main(String[] args) {
        String token = "ODM5OTk2NjdjNzkyY2NhMzU1MzY5NmM3NjY3YzFlMDU=";

        String method = "postProductSender";

        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        String dstr = dateStr.substring(0, 17) + "00";

        String str = token+method+dstr;

        token = DigestUtils.md5Hex(DigestUtils.md5Hex(DigestUtils.md5Hex(str)));
        log.debug(token);
    }



}
