package com.ylsm.api;

import com.ylsm.annotation.TokenField;
import com.ylsm.annotation.YlsmToken;
import com.ylsm.config.FeignConfig;
import com.ylsm.model.api.ApiResult;
import com.ylsm.model.bo.GoodsBO;
import com.ylsm.model.bo.OrderBO;
import com.ylsm.model.bo.RejectProductBO;
import com.ylsm.model.bo.SaleOrderBO;
import feign.codec.StringDecoder;
import feign.gson.GsonDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient(url="${feign.host}", name = "${feign.name}", configuration = {GsonDecoder.class, FeignConfig.class})
public interface ApiFeignClient {

    /*获取 token*/
    @PostMapping("${docking.api.module.token.route}")
    String getTokenInfo(@RequestParam("systemCode") String systemCode, @RequestParam("accessKey") String accessKey);


    /*库存同步接口*/
    @PostMapping("${docking.api.module.updateStock.route}")
    @YlsmToken("${docking.api.module.updateStock.method}")
    String updateStock(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("channelCode") String channelCode, @RequestParam("jsonRequest") String jsonRequest);

    /*销售单接口*/
    @PostMapping("${docking.api.module.saleSheet.route}")
    @YlsmToken("${docking.api.module.saleSheet.method}")
    String saleSheet(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("channelCode") String channelCode, @RequestParam("jsonRequest") String jsonRequest, @RequestParam("manualId") String manualId, @RequestParam("saleDate") String saleDate);

    /*云仓单接口*/
    @PostMapping("${docking.api.module.cloundSender.route}")
    @YlsmToken("${docking.api.module.cloudSender.method}")
    ApiResult<Collection<SaleOrderBO>> cloudSender(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("queryStartDate") String queryStartDate, @RequestParam("queryEndDate") String queryEndDate);

    /*商品档案*/
    @PostMapping("${docking.api.module.productInfoSender.route}")
    @YlsmToken("${docking.api.module.productInfoSender.method}")
    ApiResult<Collection<GoodsBO>> productInfoSender(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("queryStartDate") String queryStartDate, @RequestParam("queryEndDate") String queryEndDate);

    /*发货单*/
    @PostMapping("${docking.api.module.postProductSender.route}")
    @YlsmToken("${docking.api.module.postProductSender.method}")
    ApiResult<Collection<OrderBO>> postProductSender(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("queryStartDate") String queryStartDate, @RequestParam("queryEndDate") String queryEndDate);

    /*退货单*/
    @PostMapping("${docking.api.module.rejectProductSender.route}")
    @YlsmToken("${docking.api.module.rejectProductSender.method}")
    ApiResult<Collection<RejectProductBO>> rejectProductSender(@RequestParam("token") @TokenField String token, @RequestParam("tokenId") Integer tokenId, @RequestParam("queryStartDate") String queryStartDate, @RequestParam("queryEndDate") String queryEndDate);
}
