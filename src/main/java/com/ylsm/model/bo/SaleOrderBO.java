package com.ylsm.model.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

@Data
@Accessors(chain = true)
public class SaleOrderBO {

    /*单号*/
    private String sheetId;

    /*销售日期*/
    private String saleDate;

    /*渠道码*/
    private String channelCode;

    /*渠道名称*/
    private String channelName;

    /*未知*/
    private String cloundType;

    private Collection<OrderGoodsBO> detailList;

}
