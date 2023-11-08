package com.ylsm.controller;

import com.ylsm.api.ApiFeignClient;
import com.ylsm.service.ApiRequestProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@Slf4j
@RestController()
@RequestMapping("/test")
public class TestController {

    private ApiFeignClient apiFeignClient;

    private ApiRequestProxyService proxyService;

    @Autowired(required = false)
    public void setApiFeignClient(ApiFeignClient apiFeignClient) {
        this.apiFeignClient = apiFeignClient;
    }

    @Autowired
    public void setProxyService(ApiRequestProxyService apiRequestProxyService) {
        this.proxyService = apiRequestProxyService;
    }
    @Value("${docking.account}")
    private String account;

    @Value("${docking.password}")
    private String password;

    @GetMapping("/tt")
    public String test() {
        return apiFeignClient.getTokenInfo(account, password);
    }

    @GetMapping("/1")
    public String a() {
        return proxyService.cloudSender(new Date(), new Date());
    }

    @GetMapping("/2")
    public String a2() {
        return proxyService.productInfoSender(new Date(), new Date());
    }

    @GetMapping("/3")
    public String a3() {
        return proxyService.postProductSender(new Date(), new Date());
    }

    @GetMapping("/4")
    public String a4() {
        return proxyService.rejectProductSender(new Date(), new Date());
    }

}
