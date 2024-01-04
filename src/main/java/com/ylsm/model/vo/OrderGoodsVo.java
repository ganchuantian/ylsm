package com.ylsm.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: hqy
 * @create: 2023-12-26 10:33
 **/
@Data
@Accessors(chain = true)
public class OrderGoodsVo {

    // 条形码
    private String barcode;

    // 数量
    private int quantity;

}
