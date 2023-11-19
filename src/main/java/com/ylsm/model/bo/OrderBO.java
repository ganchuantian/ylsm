package com.ylsm.model.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

@Data
@Accessors(chain = true)
public class OrderBO {

    private String sheetId;

    private String manualId;

    private String saleDate;

    private String channelCode;

    private String channelName;

    private String remark;

    private Collection<OrderGoodsBO> detailList;


}
