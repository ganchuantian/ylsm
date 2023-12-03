package com.ylsm.service;

import com.ylsm.model.GoodsDo;
import com.ylsm.model.bo.GoodsBO;
import com.ylsm.service.base.ApiRequestProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductInfoService {

    @Value("${sleep-time:3000L}")
    private Long sleepTime;

    @Value("${select-size:2000}")
    private Integer selectSize;

    @Autowired
    private ApiRequestProxyService proxyService;

    @Transactional(rollbackFor = Exception.class)
    public void synInfo(Date startTime, Date endTime) {

        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            log.error("thread sleep exception!", e);
        }
        Collection<GoodsBO> goodsBos = proxyService.productInfoSender(startTime, endTime);
        if (CollectionUtils.isEmpty(goodsBos)) {
            return;
        }
        Set<String> barCodes = new HashSet<>();
        List<String> barCodeList = goodsBos.stream().map(GoodsBO::getBarcode).filter(barCodes::add).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(barCodeList)) {
            return;
        }
        List<GoodsDo> goods = new ArrayList<>();
        // 有时查询的结果会非常大需要分步去数据库中查询历史存储结果
        for (int i = 0; i < barCodeList.size(); i += selectSize) {
            Collection<String> codes = barCodeList.subList(i, Math.min(i + selectSize, barCodeList.size()));
            goods.addAll(null); // todo 找出匹配的所有商品
        }
        barCodes = goods.stream().map(GoodsDo::getBarcode).collect(Collectors.toSet());
        if (!CollectionUtils.isEmpty(barCodes)) {
            // 过滤出新增的数据
            Set<String> finalBarCodes = barCodes;
            goodsBos = goodsBos.stream().filter(r -> !finalBarCodes.contains(r.getBarcode())).collect(Collectors.toSet());
        }
        Set<String> finalBarCodes1 = new HashSet<>();
        goodsBos = goodsBos.stream().filter(r -> finalBarCodes1.add(r.getBarcode())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(goodsBos)) {
            return;
        }
        List<GoodsDo> insertList = GoodsDo.convertByGoodsBo(goodsBos);
        // todo insert insertList
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            a.add(i);
        }
        log.info(String.valueOf(a.subList(0, 5)));
        log.info(String.valueOf(a.subList(5, 10)));
    }


}
