package com.ylsm.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 表 v_cjjk_xs
 */
@Data
@Accessors(chain = true)

public class OrderDetailDO {

    /*xp(小牌?) 号码*/
    private String manualID;

    /*销售日期*/
    private String saleDate;
    /*仓库代码*/
    private String channelCode;

    /*条形码*/
    private String barcode;

    /*金额*/
    private BigDecimal disprice;


    /*数量*/
    private int quantity;

}
