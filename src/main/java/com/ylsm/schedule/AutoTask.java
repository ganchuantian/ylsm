package com.ylsm.schedule;

import com.ylsm.model.AutoTaskRecordDo;
import com.ylsm.service.*;
import com.ylsm.untils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: hqy
 * @create: 2023-12-28 17:12
 **/
@Component
@Slf4j
public class AutoTask {

    @Autowired
    private CloudSenderService cloudSenderService;

    @Autowired
    private AutoTaskRecordService autoTaskRecordService;

    @Autowired
    private PostProductService postProductService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private RejectProductService rejectProductService;

    @Autowired
    private SaleSheetService saleSheetService;

    @Autowired
    private UpdateStockService updateStockService;

    @Async
    @Scheduled(cron = "${schedule.auto.close.idle:0 0 0/1 * * ?}")
    public void cloudSenderAsync() throws ParseException {
        log.info("云仓同步开始");
        AutoTaskRecordDo autoTaskRecord = new AutoTaskRecordDo();
        autoTaskRecord.setTaskName("cloundSender");
//        String last = ((AutoTaskRecordDo)this.autoTaskRecordMapper.selectOne(autoTaskRecord)).getLastTime();
        String last = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date lastDate;
        if (StringUtils.isNotBlank(last)) {
            lastDate = df.parse(last);
        } else {
            lastDate = new Date();
        }
        Assert.notNull(lastDate, "cloud sender async error last sync date is null!");
        int needDay = DateUtils.getReloadDay(last);
        if (needDay >= 0) {
            cloudSenderService.reload(needDay);
        } else {
            cloudSenderService.synInfo(lastDate, lastDate);
            this.autoTaskRecordService.updateTime("cloundSender");
        }
        log.info("云仓同步结束");
    }

    @Async
    @Scheduled(cron = "${schedule.auto.close.idle:0 0 0/1 * * ?}")
    public void postProductAsync() throws ParseException {
        log.info("发货单同步开始");
        AutoTaskRecordDo autoTaskRecord = new AutoTaskRecordDo();
        autoTaskRecord.setTaskName("postProductSender");
//        String last = ((AutoTaskRecordDo)this.autoTaskRecordMapper.selectOne(autoTaskRecord)).getLastTime();
        String last = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date lastDate;
        if (StringUtils.isNotBlank(last)) {
            lastDate = df.parse(last);
        } else {
            lastDate = new Date();
        }
        Assert.notNull(lastDate, "post product sender async error last sync date is null!");
        int needDay = DateUtils.getReloadDay(last);
        if (needDay >= 0) {
            postProductService.reload(needDay);
        } else {
            postProductService.synInfo(lastDate, lastDate);
            this.autoTaskRecordService.updateTime("postProductSender");
        }
        log.info("发货单同步结束");
    }

    @Async
    @Scheduled(cron = "${schedule.auto.close.idle:0 0 0/1 * * ?}")
    public void productInfoSenderAsync() throws ParseException {
        log.info("商品同步开始");
        AutoTaskRecordDo autoTaskRecord = new AutoTaskRecordDo();
        autoTaskRecord.setTaskName("productInfoSender");
        String last = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date lastDate;
        if (StringUtils.isNotBlank(last)) {
            lastDate = df.parse(last);
        } else {
            lastDate = new Date();
        }
        Assert.notNull(lastDate, "product info sender async error last sync date is null!");
        int needDay = DateUtils.getReloadDay(last);
        if (needDay >= 0) {
            productInfoService.reload(needDay);
        } else {
            productInfoService.synInfo(lastDate, lastDate);
            this.autoTaskRecordService.updateTime("productInfoSender");
        }
        log.info("商品同步结束");
    }

    @Async
    @Scheduled(cron = "${schedule.auto.close.idle:0 0 0/1 * * ?}")
    public void rejectProductAsync() throws ParseException {
        log.info("退单同步开始");
        AutoTaskRecordDo autoTaskRecord = new AutoTaskRecordDo();
        autoTaskRecord.setTaskName("rejectProductSender");
//        String last = ((AutoTaskRecordDo)this.autoTaskRecordMapper.selectOne(autoTaskRecord)).getLastTime();
        String last = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date lastDate;
        if (StringUtils.isNotBlank(last)) {
            lastDate = df.parse(last);
        } else {
            lastDate = new Date();
        }
        Assert.notNull(lastDate, "reject product async error last sync date is null!");
        int needDay = DateUtils.getReloadDay(last);
        if (needDay >= 0) {
            rejectProductService.reload(needDay);
        } else {
            rejectProductService.synInfo(lastDate, lastDate);
            this.autoTaskRecordService.updateTime("rejectProductSender");
        }
        log.info("退单同步结束");
    }


    @Async
    @Scheduled(cron = "${schedule.auto.close.idle:0 0 0/1 * * ?}")
    public void saleSheetAsync() throws ParseException {
        log.info("订单同步开始");
        AutoTaskRecordDo autoTaskRecord = new AutoTaskRecordDo();
        autoTaskRecord.setTaskName("saleSheet");
//        String last = ((AutoTaskRecordDo)this.autoTaskRecordMapper.selectOne(autoTaskRecord)).getLastTime();
        String last = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date lastDate;
        if (StringUtils.isNotBlank(last)) {
            lastDate = df.parse(last);
        } else {
            lastDate = new Date();
        }
        Assert.notNull(lastDate, "sale sheet async error last sync date is null!");
        int needDay = DateUtils.getReloadDay(last);
        if (needDay >= 0) {
            saleSheetService.reload(needDay);
        } else {
            saleSheetService.synSaleSheet(lastDate);
            this.autoTaskRecordService.updateTime("saleSheet");
        }
        log.info("订单同步结束");
    }

    @Async
    @Scheduled(cron = "${schedule.auto.close.idle:0 0 0/1 * * ?}")
    public void updateStockAsync() throws ParseException {
        log.info("库存同步开始");
        updateStockService.synUpdateStock();
        this.autoTaskRecordService.updateTime("updateStock");
        log.info("库存同步结束");
    }
}
