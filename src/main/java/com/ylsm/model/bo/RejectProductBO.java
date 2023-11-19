package com.ylsm.model.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

@Data
@Accessors(chain = true)
public class RejectProductBO {

    /*单号*/
    private String sheetId;
    private String applySheetId;

    /*时间*/
    private String sheetDate;

    /*渠道码*/
    private String channelCode;

    /*渠道名称*/
    private String channelName;

    /*目标渠道名称*/
    private String toChannelName;

    /*拒绝类型*/
    private String rejectType;

    /*备注*/
    private String remark;

    private Collection<OrderGoodsBO> detailList;

}
