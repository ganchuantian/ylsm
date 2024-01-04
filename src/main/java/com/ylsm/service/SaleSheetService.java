package com.ylsm.service;

import com.ylsm.model.OrderDetailDO;
import com.ylsm.model.bo.OrderDetailBO;
import com.ylsm.service.base.ApiRequestProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @description:
 * @author: hqy
 * @create: 2023-12-26 09:30
 **/
@Service
@Slf4j
public class SaleSheetService {

//    @Autowired
//    private OrderDetailMapper orderDetailMapper;

    @Value("${sleep-time:3000L}")
    private Long sleepTime;

    @Autowired
    private ApiRequestProxyService apiRequestProxyService;

    @Autowired
    private AutoTaskRecordService autoTaskRecordService;

    public SaleSheetService() {
    }

    public boolean synSaleSheet(Date saleDate){
/*        Example example = new Example(OrderDetailDO.class);
        example.createCriteria().andEqualTo("saleDate", saleDate + " 00:00:00.000");
        List<OrderDetailDO> orderDetailDOList = this.orderDetailMapper.selectByExample(example);*/
        // todo 查询满足条件的订单
        Collection<OrderDetailDO> orderDetailDOList = new ArrayList<>();
        Collection<OrderDetailBO> orderDetailBOList = OrderDetailBO.convertByDetailDo(orderDetailDOList);
        for (OrderDetailBO item : orderDetailBOList) {
            synSaleSheet(item);
            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                log.error("thread sleep exception!", e);
            }
        }
        return true;
    }

    public boolean synSaleSheet(OrderDetailBO orderDetailBO) {
        return apiRequestProxyService.saleSheet(orderDetailBO.getChannelCode(), orderDetailBO.getDetailList(), orderDetailBO.getManualID(), orderDetailBO.getSaleDate());
    }

    @Transactional(rollbackFor = Exception.class)
    public void reload(int day) {
        Calendar calendar = new GregorianCalendar();
        Date now = new Date();
        calendar.setTime(now);
        int i = day;
        calendar.add(Calendar.DATE, -day);
        do {
            synSaleSheet(calendar.getTime());
            autoTaskRecordService.updateTime("saleSheet");
            calendar.add(Calendar.DATE, 1);
            --i;
        } while(i >= 0);
    }


}
