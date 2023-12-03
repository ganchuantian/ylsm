package com.ylsm.model;

import com.ylsm.model.bo.OrderBO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@Accessors(chain = true)
public class OrderDO {

    /*ID*/
    private String sheetId;

    private String manualId;

    private String saleDate;

    private String channelCode;

    private String channelName;
    private String remark;

    public static OrderDO convert(OrderBO orderBO) {
        if (Objects.isNull(orderBO)) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        orderDO.setSheetId(orderBO.getSheetId());
        orderDO.setManualId(orderBO.getManualId());
        orderDO.setChannelCode(orderBO.getChannelCode());
        orderDO.setChannelName(orderBO.getChannelName());
        orderDO.setSaleDate(orderBO.getSaleDate());
        orderDO.setRemark(orderBO.getRemark());
        return orderDO;
    }

    public static List<OrderDO> convertByOrderBo(Collection<OrderBO> orderBos) {
        if (CollectionUtils.isEmpty(orderBos)) {
            return new ArrayList<>();
        }
        List<OrderDO> orderDos = new ArrayList<>();
        for (OrderBO orderBo : orderBos) {
            orderDos.add(convert(orderBo));
        }
        return orderDos;
    }


}
