package com.ylsm.service;

import com.ylsm.model.OrderGoods;
import com.ylsm.model.bo.SaleOrderBO;
import com.ylsm.model.CloundStoreOrderDO;
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
public class CloudSenderService {

    @Value("${sleep-time:3000L}")
    private Long sleepTime;

    @Value("${select-size:2000}")
    private Integer selectSize;

    @Autowired
    private ApiRequestProxyService requestProxyService;

    @Transactional(rollbackFor = Exception.class)
    public void synInfo(Date startTime, Date endTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            log.error("thread sleep exception!", e);
        }
        Collection<SaleOrderBO> saleOrderBOS = requestProxyService.cloudSender(startTime, endTime);
        if (CollectionUtils.isEmpty(saleOrderBOS)) {
            return;
        }
        Set<String> sheetIds = new HashSet<>();
        List<String> sheetIdList = saleOrderBOS.stream().map(SaleOrderBO::getSheetId).filter(sheetIds::add).collect(Collectors.toList());
        List<CloundStoreOrderDO> oldCloudStoreOrderDOS = new ArrayList<>();
        // 有时查询的结果会非常大需要分步去数据库中查询历史存储结果
        for (int i = 0; i < sheetIdList.size(); i += selectSize) {
            Collection<String> codes = sheetIdList.subList(i, Math.min(i + selectSize, sheetIdList.size()));
            oldCloudStoreOrderDOS.addAll(null); // todo  查找已同步至库中的数据
        }
        sheetIds = oldCloudStoreOrderDOS.stream().map(CloundStoreOrderDO::getSheetId).collect(Collectors.toSet());
        if (!CollectionUtils.isEmpty(sheetIds)) {
            Set<String> finalSheetIds = sheetIds;
            saleOrderBOS = saleOrderBOS.stream().filter(r -> !finalSheetIds.contains(r.getSheetId())).collect(Collectors.toList());
        }
        Collection<CloundStoreOrderDO> insertCloudStoreOrderList = CloundStoreOrderDO.convertBySaleOrderBo(saleOrderBOS);
        Collection<OrderGoods> insertOrderGoods = OrderGoods.convertBySaleOrderBo(saleOrderBOS);

        // todo insert insertCloudStoreOrderList
        // todo insert insertOrderGoods
    }

}
