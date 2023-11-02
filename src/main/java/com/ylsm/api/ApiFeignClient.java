package com.ylsm.api;

import com.ylsm.annotation.TokenField;
import com.ylsm.annotation.YlsmToken;
import com.ylsm.config.FeignConfig;
import feign.codec.StringDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="${docking.api.host}", name = "client", configuration = {StringDecoder.class, FeignConfig.class})
public interface ApiFeignClient {

    /*获取 token*/
    @PostMapping("/token/getTokenInfo")
    String getTokenInfo(@RequestParam("systemCode") String systemCode, @RequestParam("accessKey") String accessKey);


    /*库存同步接口*/
    @PostMapping("/outer/updateStock")
    @YlsmToken("updateStock")
    String updateStock(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("channelCode") String channelCode, @RequestParam("jsonRequest") String jsonRequest);

    /*销售单接口*/
    @PostMapping("/outer/saleSheet")
    @YlsmToken("saleSheet")
    String saleSheet(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("channelCode") String channelCode, @RequestParam("jsonRequest") String jsonRequest, @RequestParam("manualId") String manualId, @RequestParam("saleDate") String saleDate);

    /*云仓单接口*/
    @PostMapping("/outer/cloundSender")
    @YlsmToken("cloundSender")
    String cloudSender(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("queryStartDate") String queryStartDate, @RequestParam("queryEndDate") String queryEndDate);

    /*商品档案*/
    @PostMapping("/outer/productInfoSender")
    @YlsmToken("productInfoSender")
    String productInfoSender(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("queryStartDate") String queryStartDate, @RequestParam("queryEndDate") String queryEndDate);

    /*发货单*/
    @PostMapping("/outer/postProductSender")
    @YlsmToken("postProductSender")
    String postProductSender(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("queryStartDate") String queryStartDate, @RequestParam("queryEndDate") String queryEndDate);

    /*退货单*/
    @PostMapping("/outer/rejectProductSender")
    @YlsmToken("rejectProductSender")
    String rejectProductSender(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("queryStartDate") String queryStartDate, @RequestParam("queryEndDate") String queryEndDate);
}
