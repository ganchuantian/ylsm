package com.ylsm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ylsm.api.ApiFeignClient;
import com.ylsm.model.TokenModel;
import com.ylsm.model.api.ApiResult;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class TokenService  extends AbstractApiRequestService {

    @Value("${docking.account}")
    private String account;

    @Value("${docking.password}")
    private String password;

    @Value("${docking.token.expire.limit:60}")
    private Integer limit;

    private Integer retry = 3;

    @Autowired
    private ApiFeignClient apiFeignClient;

    private final TokenInfo tokenInfo = TokenInfo.getTokenInfo();

    @Autowired
    private ObjectMapper objectMapper;

    private static Long LAST_UPDATE_TIME = null;


    public synchronized TokenModel getTokenInfo() {
        return getTokenInfo(false);
    }

    public synchronized TokenModel getTokenInfo(boolean force) {
        int count = 1;
        while (force || isExpire() || Objects.isNull(tokenInfo) || tokenInfo.contentHasEmpty()) {
            // todo 可配置
            if (count > retry) {
                log.error("get token error on request api {} time!", retry);
                throw new RuntimeException(String.format("get token error on %s times!", retry));
            }
            if (refreshToken()) {
                force = false;
            } else {
                count ++;
            }
        }
        TokenModel model = new TokenModel();
        BeanUtils.copyProperties(tokenInfo, model);
        return model;
    }

    private boolean isExpire() {
        if (Objects.isNull(LAST_UPDATE_TIME)) {
            return true;
        }
        return System.currentTimeMillis() - LAST_UPDATE_TIME > limit*60*100;
    }

    private boolean refreshToken() {
        String s = apiFeignClient.getTokenInfo(account, password);
        if (StringUtils.isBlank(s)) {
            log.error("request token/getTokenInfo api error, result is empty! account:{}, pass:{}, responseData:{}", account, password, s);
            return false;
        }
        ApiResult<TokenModel> result = null;
        try {
            result = objectMapper.readValue(s, new TypeReference<ApiResult<TokenModel>>() {});
        } catch (JsonProcessingException e) {
            log.error("get token info result json deserialize error! account:{}, pass:{}, responseData:{}", account, password, s, e);
        }
        if (Objects.isNull(result) || !result.success() || Objects.isNull(result.getData())) {
            log.error("get token info result error. account:{}, pass:{}, responseData:{}, result:{}", account, password, s, result);
            return false;
        }
        BeanUtils.copyProperties(result.getData(), tokenInfo);
        LAST_UPDATE_TIME = System.currentTimeMillis();
        return true;
    }

    @Data
    @Accessors(chain = true)
    public static class TokenInfo {

        private Integer tokenId;

        private String token;

        private final static TokenInfo TOKEN_INFO = new TokenInfo();

        private TokenInfo(){

        }

        public static TokenInfo getTokenInfo() {
            return TOKEN_INFO;
        }

        public boolean contentHasEmpty() {
            return Objects.isNull(tokenId) || StringUtils.isBlank(token);
        }

    }
}
