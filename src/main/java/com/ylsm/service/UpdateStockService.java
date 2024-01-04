package com.ylsm.service;

import com.ylsm.model.vo.OrderGoodsVo;
import com.ylsm.service.base.ApiRequestProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: hqy
 * @create: 2023-12-26 10:30
 **/
@Service
@Slf4j
public class UpdateStockService {

    @Value("${sleep-time:3000L}")
    private Long sleepTime;

    @Autowired
    private ApiRequestProxyService apiRequestProxyService;

    public boolean synUpdateStock() {
//        List<String> channelCodes = this.updateStockMapper.getChannelCode();
        // todo 获取库中所有渠道码
        List<String> channelCodes = new ArrayList<>();

        for (String code : channelCodes) {
//            List<OrderGoodsVo> orderGoodsVOList = this.updateStockMapper.getGoodsByCode(code);
            List<OrderGoodsVo> orderGoodsVos = new ArrayList<>();
            apiRequestProxyService.updateStock(code, orderGoodsVos);
            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                log.error("thread sleep exception!", e);
            }
        }
        return true;
    }

}
