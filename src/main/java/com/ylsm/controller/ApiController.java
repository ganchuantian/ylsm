package com.ylsm.controller;

import com.ylsm.api.ApiFeignClient;
import com.ylsm.constants.Constants;
import com.ylsm.model.vo.ApiParamVo;
import com.ylsm.service.ApiRequestProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.TimeZone;
import java.util.spi.TimeZoneNameProvider;


@Slf4j
@RestController()
@RequestMapping("/api")
public class ApiController {

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

    @CrossOrigin
    @PostMapping("")
    public String api(@RequestBody ApiParamVo vo) {
        log.trace("param is :{}", vo);
        switch (vo.getType()) {
            case CLOUD_SENDER:
                proxyService.cloudSender(vo.getQueryStartDate(), vo.getQueryEndDate());
                break;
            case POST_PRODUCT_SENDER:
                proxyService.postProductSender(vo.getQueryStartDate(), vo.getQueryEndDate());
                break;
            case PRODUCE_INFO_SENDER:
                proxyService.productInfoSender(vo.getQueryStartDate(), vo.getQueryEndDate());
                break;
            case REJECT_PRODUCT_SENDER:
                proxyService.rejectProductSender(vo.getQueryStartDate(), vo.getQueryEndDate());
                break;
            default:
                break;
        }
        return "";
    }

}
