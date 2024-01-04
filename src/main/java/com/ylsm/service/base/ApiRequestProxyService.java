package com.ylsm.service.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ylsm.api.ApiFeignClient;
import com.ylsm.model.TokenModel;
import com.ylsm.model.api.ApiResult;
import com.ylsm.model.bo.*;
import com.ylsm.service.base.TokenService;
import com.ylsm.untils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jooq.tools.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

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

    private static final Gson GSON = new Gson();
    /**
     * 同步库存
     * @param channelCode
     * @param jsonRequest
     * @return
     */
    public boolean updateStock(String channelCode, Object jsonRequest){
        TokenModel model = tokenService.getTokenInfo();
        ApiResult<Object> result = feignClient.updateStock(model.getToken(), model.getTokenId(), channelCode, GSON.toJson(jsonRequest));
        log.info("updateStock request result is result:{}", result);
        return result.success();
    }

    /*销售单接口*/

    public boolean saleSheet(String channelCode, Object jsonRequest, String manualId, String saleDate) {
        TokenModel model = tokenService.getTokenInfo();
        ApiResult<Object> result = feignClient.saleSheet(model.getToken(), model.getTokenId(), channelCode, GSON.toJson(jsonRequest), manualId, saleDate);
        log.info("saleSheet request result is result:{}", result);
        return result.success();
    }

    /*云仓单接口*/

    public Collection<SaleOrderBO> cloudSender(Date queryStartDate, Date queryEndDate){
        TokenModel model = tokenService.getTokenInfo();

        return getResult(feignClient.cloudSender(model.getToken(), model.getTokenId(), DateUtils.format(dateFormat, queryStartDate), DateUtils.format(dateFormat, queryEndDate)));
    }

    /*商品档案*/

    public Collection<GoodsBO> productInfoSender(Date queryStartDate, Date queryEndDate){
        TokenModel model = tokenService.getTokenInfo();
        return getResult(feignClient.productInfoSender(model.getToken(), model.getTokenId(), DateUtils.format(dateFormat, queryStartDate), DateUtils.format(dateFormat, queryEndDate)));
    }

    /*发货单*/

    public Collection<OrderBO> postProductSender(Date queryStartDate, Date queryEndDate){
        TokenModel model = tokenService.getTokenInfo();
        return getResult(feignClient.postProductSender(model.getToken(), model.getTokenId(), DateUtils.format(dateFormat, queryStartDate), DateUtils.format(dateFormat, queryEndDate)));
    }

    /*退货单*/
    public Collection<RejectProductBO> rejectProductSender(Date queryStartDate, Date queryEndDate){
        TokenModel model = tokenService.getTokenInfo();
        return getResult(feignClient.rejectProductSender(model.getToken(), model.getTokenId(), DateUtils.format(dateFormat, queryStartDate), DateUtils.format(dateFormat, queryEndDate)));
    }

    private <T> T getResult (ApiResult<T> apiResult) {
        String success = "S";
        String result = apiResult.getResult();
        if (Objects.isNull(apiResult) || !apiResult.success()) {
            log.error("request error! result is {}", apiResult);
            return null;
        }
        return apiResult.getData();
    }

}
