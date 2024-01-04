package com.ylsm.model.bo;

import com.ylsm.model.OrderDetailDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Accessors(chain = true)
public class OrderDetailBO {

    private String manualID;

    /*销售时间*/
    private String saleDate;

    /*渠道码*/
    private String channelCode;

    private Collection<OrderGoodsBO> detailList;

    public static Collection<OrderDetailBO> convertByDetailDo(Collection<OrderDetailDO> list) {
        List<OrderDetailBO> orderDetailBOList = new ArrayList();
        if (CollectionUtils.isEmpty(list)) {
            return orderDetailBOList;
        }
        String manualID = null;
        OrderDetailBO orderDetailBO = null;

        for (OrderDetailDO orderDetailDO : list) {
            OrderGoodsBO orderGoodsBO;
            if (manualID != null && !"".equals(manualID) && orderDetailDO.getManualID().equals(manualID)) {
                orderGoodsBO = new OrderGoodsBO();
                orderGoodsBO.setBarcode(orderDetailDO.getBarcode());
                orderGoodsBO.setDisprice(orderDetailDO.getDisprice());
                orderGoodsBO.setQuantity(orderDetailDO.getQuantity());
                orderDetailBO.getDetailList().add(orderGoodsBO);
            } else {
                manualID = orderDetailDO.getManualID();
                orderDetailBO = new OrderDetailBO();
                orderDetailBO.setManualID(orderDetailDO.getManualID());
                orderDetailBO.setChannelCode(orderDetailDO.getChannelCode());
                orderDetailBO.setSaleDate(orderDetailDO.getSaleDate());
                orderGoodsBO = new OrderGoodsBO();
                orderGoodsBO.setBarcode(orderDetailDO.getBarcode());
                orderGoodsBO.setDisprice(orderDetailDO.getDisprice());
                orderGoodsBO.setQuantity(orderDetailDO.getQuantity());
                orderDetailBO.setDetailList(new ArrayList());
                orderDetailBO.getDetailList().add(orderGoodsBO);
                orderDetailBOList.add(orderDetailBO);
            }
        }
        return orderDetailBOList;

    }


}
