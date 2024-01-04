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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    @Autowired
    private AutoTaskRecordService autoTaskRecordService;

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

    @Transactional(rollbackFor = Exception.class)
    public void reload(int day) {
        Calendar calendar = new GregorianCalendar();
        Date now = new Date();
        calendar.setTime(now);
        int i = day;
        calendar.add(Calendar.DATE, -day);

        do {
            synInfo(calendar.getTime(), calendar.getTime());
            this.autoTaskRecordService.updateTime("productInfoSender");
            calendar.add(Calendar.DATE, 1);
            --i;
        } while(i >= 0);
    }




}
