package com.ylsm.model;

import com.ylsm.model.bo.GoodsBO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 表 Goods
 * */
@Data
@Accessors(chain = true)
public class GoodsDo {

    /*ID*/
    private String barcode;

    /*商品编号 goodsNo*/
    private String goodsNo;
    /*goodsName 商品名称*/
    private String goodsName;
    /*产品大类 prodCategoryBig*/
    private String prodCategoryBig;

    /*品牌键值 brandKey*/
    private String brandKey;

    /*存储分类名称 storyCategroyName*/
    private String storyCategroyName;

    /*年份*/
    private String year;

    /*大小描述 sizeDesc*/
    private String sizeDesc;

    /*月份*/
    private String month;

    /*季度*/
    private String quarter;

    /*色号*/
    private String colorWayBand;

    /*产品中类 prodCategoryMiddel*/
    private String prodCategoryMiddel;

    /*系列*/
    private String series;

    /*色号*/
    private String colorCode;

    /*色彩描述*/
    private String colorDesc;

    /*价格 dpPrice*/
    private BigDecimal dpPrice;

    /*合并商单号*/
    private String mergeGoodsNo;

    public static GoodsDo convert(GoodsBO goodsBo) {
        if (Objects.isNull(goodsBo)) {
            return null;
        }
        GoodsDo goodsDo = new GoodsDo();
        goodsDo.setBarcode(goodsBo.getBarcode());
        goodsDo.setGoodsNo(goodsBo.getGoodsNo());
        goodsDo.setGoodsName(goodsBo.getGoodsName());
        goodsDo.setStoryCategroyName(goodsBo.getStoryCategroyName());
        goodsDo.setProdCategoryBig(goodsBo.getProdCategoryBig());
        goodsDo.setDpPrice(goodsBo.getDpPrice());
        goodsDo.setMergeGoodsNo(goodsBo.getMergeGoodsNo());
        goodsDo.setSizeDesc(goodsBo.getSizeDesc());
        goodsDo.setProdCategoryMiddel(goodsBo.getProdCategoryMiddel());
        goodsDo.setBrandKey(goodsBo.getBrandKey());
        goodsDo.setYear(goodsBo.getYear());
        goodsDo.setMonth(goodsBo.getMonth());
        goodsDo.setColorCode(goodsBo.getColorCode());
        goodsDo.setColorDesc(goodsBo.getColorDesc());
        goodsDo.setColorWayBand(goodsBo.getColorWayBand());
        goodsDo.setQuarter(goodsBo.getQuarter());
        goodsDo.setSeries(goodsBo.getSeries());
        return goodsDo;
    }

    public static List<GoodsDo> convertByGoodsBo(Collection<GoodsBO> goodsBos) {
        if (CollectionUtils.isEmpty(goodsBos)) {
            return new ArrayList<>();
        }
        List<GoodsDo> goodsDos = new ArrayList<>();
        for (GoodsBO goodsBo : goodsBos) {
            goodsDos.add(convert(goodsBo));
        }
        return goodsDos;
    }

}
