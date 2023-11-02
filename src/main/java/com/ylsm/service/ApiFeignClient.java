package com.ylsm.service;

import feign.codec.StringDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="${docking.api.host}", name = "client", configuration = StringDecoder.class)
public interface ApiFeignClient {

    /*获取 token*/
    @PostMapping("/token/getTokenInfo")
    String getTokenInfo(@RequestParam String systemCode, @RequestParam String accessKey);


    /*库存同步接口*/
    @PostMapping("/outer/updateStock")
    String updateStock();

    /*销售单接口*/
    @PostMapping("/outer/saleSheet")
    String saleSheet();

    /*云仓单接口*/
    @PostMapping("/outer/cloundSender")
    String cloudSender();

    /*商品档案*/
    @PostMapping("/outer/productInfoSender")
    String productInfoSender();

    /*发货单*/
    @PostMapping("/outer/postProductSender")
    String postProductSender();

    /*退货单*/
    @PostMapping("/outer/rejectProductSender")
    String rejectProductSender();
}
