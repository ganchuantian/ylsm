package com.ylsm.model.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

@Data
@Accessors(chain = true)
public class OrderDetailBO {

    private String manualID;

    /*销售时间*/
    private String saleDate;

    /*渠道码*/
    private String channelCode;

    private Collection<OrderGoodsBO> detailList;

}
