package com.ylsm.service;

import com.ylsm.model.OrderDO;
import com.ylsm.model.PostProductGoodsDetail;
import com.ylsm.model.bo.OrderBO;
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
public class PostProductService {

    @Value("${sleep-time:3000L}")
    private Long sleepTime;

    @Value("${select-size:2000}")
    private Integer selectSize;

    @Autowired
    private ApiRequestProxyService requestProxyService;

    @Autowired
    private AutoTaskRecordService autoTaskRecordService;

    @Transactional(rollbackFor = Exception.class)
    public void synInfo(Date startTime, Date endTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            log.error("thread sleep exception!", e);
        }

        Collection<OrderBO> orderBos = requestProxyService.postProductSender(startTime, endTime);
        if (CollectionUtils.isEmpty(orderBos)) {
            return;
        }
        Set<String> sheetIds = new HashSet<>();
        List<String> sheetIdList = orderBos.stream().map(OrderBO::getSheetId).filter(sheetIds::add).collect(Collectors.toList());
        List<OrderDO> oldOrderDOS = new ArrayList<>(); // todo 通过 sheetIds 查找已同步至库中的数据
        for (int i = 0; i < sheetIdList.size(); i += selectSize) {
            Collection<String> codes = sheetIdList.subList(i, Math.min(i + selectSize, sheetIdList.size()));
            oldOrderDOS.addAll(null); // todo  查找已同步至库中的数据
        }
        sheetIds = oldOrderDOS.stream().map(OrderDO::getSheetId).collect(Collectors.toSet());
        if (!CollectionUtils.isEmpty(sheetIds)) {
            Set<String> finalSheetIds = sheetIds;
            orderBos = orderBos.stream().filter(r -> !finalSheetIds.contains(r.getSheetId())).collect(Collectors.toList());
        }
        if (CollectionUtils.isEmpty(orderBos)) {
            return;
        }
        List<OrderDO> insertOrderDos = OrderDO.convertByOrderBo(orderBos);
        List<PostProductGoodsDetail> postProductGoodsDetails = PostProductGoodsDetail.convertBySaleOrderBo(orderBos);

        // todo insert insertCloudStoreOrderList
        // todo insert insertOrderGoods

    }

    @Transactional
    public void reload(int day) {
        Calendar calendar = new GregorianCalendar();
        Date now = new Date();
        calendar.setTime(now);
        int i = day;
        calendar.add(Calendar.DATE, -day);
        do {
            synInfo(calendar.getTime(), calendar.getTime());
            autoTaskRecordService.updateTime("postProductSender");
            calendar.add(Calendar.DATE, 1);
            --i;
        } while(i >= 0);
    }

}
