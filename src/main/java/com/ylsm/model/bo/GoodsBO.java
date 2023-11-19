package com.ylsm.model.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class GoodsBO {

    /*商品编号*/
    private String goodsNo;

    /*大分类*/
    private String prodCategoryBig;

    private String helpRemember;

    private String brandKey;

    private String storyCategroyName;

    private String year;

    private String sizeDesc;

    private String priority;

    private String categoryName;

    private String goodsType;

    private String saleTime;

    private String month;

    private String colorWayBand;

    private String series;

    private BigDecimal dpPrice;

    private String colorCode;

    private String prodCategoryMiddel;

    private String barcode;

    private String goodsName;

    private String mergeGoodsNo;

    private String colorDesc;

    private String quarter;

}
