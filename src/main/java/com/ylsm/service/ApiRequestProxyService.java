package com.ylsm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ylsm.api.ApiFeignClient;
import com.ylsm.model.TokenModel;
import com.ylsm.untils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ApiRequestProxyService {

    @Autowired
    private ApiFeignClient feignClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenService tokenService;

    @Value("${spring.jackson.date-format: yyyy-MM-dd}")
    private String dateFormat;

    /**
     * 同步库存
     * @param channelCode
     * @param jsonRequest
     * @return
     */
    public String updateStock(String channelCode, Object jsonRequest) throws JsonProcessingException {
        TokenModel model = tokenService.getTokenInfo();
        return feignClient.updateStock(model.getToken(), model.getTokenId(), channelCode, objectMapper.writeValueAsString(jsonRequest));
    }

    /*销售单接口*/

    public String saleSheet(String channelCode, Object jsonRequest, String manualId, Date saleDate) throws JsonProcessingException {
        TokenModel model = tokenService.getTokenInfo();
        return feignClient.saleSheet(model.getToken(), model.getTokenId(), channelCode, objectMapper.writeValueAsString(jsonRequest), manualId, DateUtils.format("yyyy-MM-dd HH:mm:ss", saleDate));
    }

    /*云仓单接口*/

    public String cloudSender(Date queryStartDate, Date queryEndDate){
        TokenModel model = tokenService.getTokenInfo();
        return feignClient.cloudSender(model.getToken(), model.getTokenId(), DateUtils.format(dateFormat, queryStartDate), DateUtils.format(dateFormat, queryEndDate));
    }

    /*商品档案*/

    public String productInfoSender(Date queryStartDate, Date queryEndDate){
        TokenModel model = tokenService.getTokenInfo();
        return feignClient.productInfoSender(model.getToken(), model.getTokenId(), DateUtils.format(dateFormat, queryStartDate), DateUtils.format(dateFormat, queryEndDate));
    }

    /*发货单*/

    public String postProductSender(Date queryStartDate, Date queryEndDate){
        TokenModel model = tokenService.getTokenInfo();
        return feignClient.postProductSender(model.getToken(), model.getTokenId(), DateUtils.format(dateFormat, queryStartDate), DateUtils.format(dateFormat, queryEndDate));
    }

    /*退货单*/
    public String rejectProductSender(Date queryStartDate, Date queryEndDate){
        TokenModel model = tokenService.getTokenInfo();
        return feignClient.rejectProductSender(model.getToken(), model.getTokenId(), DateUtils.format(dateFormat, queryStartDate), DateUtils.format(dateFormat, queryEndDate));
    }

}
