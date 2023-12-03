package com.ylsm.model;

import com.ylsm.model.bo.OrderGoodsBO;
import com.ylsm.model.bo.RejectProductBO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@Accessors(chain = true)
public class ReturnOrderGoodsDo {

    /*主键*/
    private String sheetId;
    /*主键*/
    private String barcode;

    /**/
    private BigDecimal disprice;


    private Integer quantity;

    public static ReturnOrderGoodsDo convert(OrderGoodsBO orderGoodsBO, String sheetId) {
        if (Objects.isNull(orderGoodsBO) || StringUtils.isBlank(sheetId)) {
            return null;
        }
        ReturnOrderGoodsDo goods = new ReturnOrderGoodsDo();
        goods.setSheetId(sheetId);
        goods.setBarcode(orderGoodsBO.getBarcode());
        goods.setDisprice(orderGoodsBO.getDisprice());
        goods.setQuantity(orderGoodsBO.getQuantity());
        return goods;
    }

    public static List<ReturnOrderGoodsDo> convertRejectProductBo(Collection<RejectProductBO> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return new ArrayList<>();
        }
        List<ReturnOrderGoodsDo> orderGoods = new ArrayList<>();
        for (RejectProductBO item : collection) {
            Collection<OrderGoodsBO> orderGoodsBos = item.getDetailList();
            if (CollectionUtils.isEmpty(orderGoodsBos)) {
                continue;
            }
            for (OrderGoodsBO orderGoodsBo : orderGoodsBos) {
                orderGoods.add(convert(orderGoodsBo, item.getSheetId()));
            }
        }
        return orderGoods;
    }

}
