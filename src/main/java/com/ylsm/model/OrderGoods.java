package com.ylsm.model;

import com.ylsm.model.bo.OrderGoodsBO;
import com.ylsm.model.bo.SaleOrderBO;
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
public class OrderGoods {

    /*ID*/
    private String sheetId;
    /*ID*/
    private String barcode;
    private BigDecimal disprice;
    private Integer quantity;

    public static OrderGoods convert(OrderGoodsBO orderGoodsBO, String sheetId) {
        if (Objects.isNull(orderGoodsBO) || StringUtils.isBlank(sheetId)) {
            return null;
        }
        OrderGoods goods = new OrderGoods();
        goods.setSheetId(sheetId);
        goods.setBarcode(orderGoodsBO.getBarcode());
        goods.setDisprice(orderGoodsBO.getDisprice());
        goods.setQuantity(orderGoodsBO.getQuantity());
        return goods;
    }

    public static List<OrderGoods> convertBySaleOrderBo(Collection<SaleOrderBO> saleOrderBOS) {
        if (CollectionUtils.isEmpty(saleOrderBOS)) {
            return new ArrayList<>();
        }
        List<OrderGoods> orderGoods = new ArrayList<>();
        for (SaleOrderBO saleOrderBo : saleOrderBOS) {
            Collection<OrderGoodsBO> orderGoodsBos = saleOrderBo.getDetailList();
            if (CollectionUtils.isEmpty(orderGoodsBos)) {
                continue;
            }
            for (OrderGoodsBO orderGoodsBo : orderGoodsBos) {
                orderGoods.add(OrderGoods.convert(orderGoodsBo, saleOrderBo.getSheetId()));
            }
        }
        return orderGoods;
    }

}
