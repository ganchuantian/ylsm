package com.ylsm.model;

import com.ylsm.model.bo.OrderBO;
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
public class PostProductGoodsDetail {

    /*ID*/
    private String sheetId;
    /*ID*/
    private String barcode;
    private BigDecimal disprice;
    private Integer quantity;
    private String balancetype;

    public static PostProductGoodsDetail convert(OrderGoodsBO orderGoodsBo, String sheetId) {
        if (Objects.isNull(orderGoodsBo) || StringUtils.isBlank(sheetId)) {
            return null;
        }
        PostProductGoodsDetail postProductGoodsDetail = new PostProductGoodsDetail();
        postProductGoodsDetail.setSheetId(sheetId);
        postProductGoodsDetail.setBarcode(orderGoodsBo.getBarcode());
        postProductGoodsDetail.setDisprice(orderGoodsBo.getDisprice());
        postProductGoodsDetail.setQuantity(orderGoodsBo.getQuantity());
        postProductGoodsDetail.setBalancetype(orderGoodsBo.getBalanceType());
        return postProductGoodsDetail;
    }

    public static List<PostProductGoodsDetail> convertBySaleOrderBo(Collection<OrderBO> orderBos) {
        if (CollectionUtils.isEmpty(orderBos)) {
            return new ArrayList<>();
        }
        List<PostProductGoodsDetail> orderGoods = new ArrayList<>();
        for (OrderBO orderBo : orderBos) {
            Collection<OrderGoodsBO> orderGoodsBos = orderBo.getDetailList();
            if (CollectionUtils.isEmpty(orderGoodsBos)) {
                continue;
            }
            for (OrderGoodsBO orderGoodsBo : orderGoodsBos) {
                orderGoods.add(convert(orderGoodsBo, orderBo.getSheetId()));
            }
        }
        return orderGoods;
    }

}
