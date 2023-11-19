package com.ylsm.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class GoodsDo {

    private String barcode;

    private String goodsNo;

    private String goodsName;

    private String prodCategoryBig;

    private String brandKey;

    private String storyCategroyName;
    private String year;

    private String sizeDesc;
    private String month;
    private String quarter;

    private String colorWayBand;

    private String prodCategoryMiddel;
    private String series;

    private String colorCode;

    private String colorDesc;

    private BigDecimal dpPrice;

    private String mergeGoodsNo;

}
