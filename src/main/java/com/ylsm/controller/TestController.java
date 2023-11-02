package com.ylsm.controller;

import com.ylsm.service.ApiFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController()
@RequestMapping("/test")
public class TestController {

    private ApiFeignClient apiFeignClient;

    @Autowired(required = false)
    public void setApiFeignClient(ApiFeignClient apiFeignClient) {
        this.apiFeignClient = apiFeignClient;
    }
    @Value("${docking.account}")
    private String account;

    @Value("${docking.password}")
    private String password;

    @GetMapping("/tt")
    public String test() {
        return apiFeignClient.getTokenInfo(account, password);
    }

}
