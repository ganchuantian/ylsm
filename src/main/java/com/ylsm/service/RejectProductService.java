package com.ylsm.service;

import com.ylsm.model.RejectOrderDO;
import com.ylsm.model.ReturnOrderGoodsDo;
import com.ylsm.model.bo.RejectProductBO;
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
public class RejectProductService {


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
        Collection<RejectProductBO> rejectProductBos = requestProxyService.rejectProductSender(startTime, endTime);
        if (CollectionUtils.isEmpty(rejectProductBos)) {
            return;
        }
        Set<String> sheetIds = new HashSet<>();
        List<String> sheetIdList = rejectProductBos.stream().map(RejectProductBO::getSheetId).filter(sheetIds::add).collect(Collectors.toList());
        List<RejectOrderDO> rejectOrderDos = new ArrayList<>();
        // 有时查询的结果会非常大需要分步去数据库中查询历史存储结果
        for (int i = 0; i < sheetIdList.size(); i += selectSize) {
            Collection<String> codes = sheetIdList.subList(i, Math.min(i + selectSize, sheetIdList.size()));
            rejectOrderDos.addAll(null); // todo  查找已同步至库中的数据
        }
        sheetIds = rejectOrderDos.stream().map(RejectOrderDO::getSheetId).collect(Collectors.toSet());
        if (!CollectionUtils.isEmpty(sheetIds)) {
            // 滤出需要新增的数据
            Set<String> finalSheetIds = sheetIds;
            rejectProductBos = rejectProductBos.stream().filter(r -> !finalSheetIds.contains(r.getSheetId())).collect(Collectors.toList());
        }
        Collection<RejectOrderDO> insertRejectOrderDos = RejectOrderDO.convertByRejectProductBo(rejectProductBos);
        Collection<ReturnOrderGoodsDo> insertReturnOrderGoodsDos = ReturnOrderGoodsDo.convertRejectProductBo(rejectProductBos);

        // todo insert insertRejectOrderDos
        // todo insert insertReturnOrderGoodsDos
    }

}
