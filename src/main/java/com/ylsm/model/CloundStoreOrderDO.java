package com.ylsm.model;

import com.ylsm.model.bo.SaleOrderBO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 表 CloundStore
 */
@Data
@Accessors(chain = true)
public class CloundStoreOrderDO {

    /*sheetId 单号*/
    private String sheetId;

    /*saleDate 销售日期*/
    private String saleDate;

    /*channelCode 仓库代码*/
    private String channelCode;

    /*channelName 仓库名称*/
    private String channelName;

    /*cloundType 类型*/
    private String cloundType;


    public static CloundStoreOrderDO convert(SaleOrderBO saleOrderBO) {
        CloundStoreOrderDO cloundStoreOrderDO = new CloundStoreOrderDO();
        cloundStoreOrderDO.setSheetId(saleOrderBO.getSheetId());
        cloundStoreOrderDO.setChannelCode(saleOrderBO.getChannelCode());
        cloundStoreOrderDO.setChannelName(saleOrderBO.getChannelName());
        cloundStoreOrderDO.setSaleDate(saleOrderBO.getSaleDate());
        cloundStoreOrderDO.setCloundType(saleOrderBO.getCloundType());
        return cloundStoreOrderDO;
    }

    public static List<CloundStoreOrderDO> convertBySaleOrderBo(Collection<SaleOrderBO> saleOrderBO) {
        List<CloundStoreOrderDO> cloundStoreOrderDOS = new ArrayList<>();
        for (SaleOrderBO orderBO : saleOrderBO) {
            cloundStoreOrderDOS.add(convert(orderBO));
        }
        return cloundStoreOrderDOS;
    }

}
