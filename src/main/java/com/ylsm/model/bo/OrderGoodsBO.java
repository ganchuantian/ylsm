package com.ylsm.model.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class OrderGoodsBO {

    private String barcode;
    private BigDecimal disprice;
    private Integer quantity;
    private String balanceType;

}
