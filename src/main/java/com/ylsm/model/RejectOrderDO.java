package com.ylsm.model;

import com.ylsm.model.bo.RejectProductBO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@Accessors(chain = true)
public class RejectOrderDO {

    /*ID*/
    private String sheetId;

    private String applySheetId;

    private String sheetDate;

    private String channelCode;

    private String channelName;

    private String toChannelName;

    private String rejectType;
    private String remark;

    public static RejectOrderDO convert(RejectProductBO rejectProductBo) {
        if (Objects.isNull(rejectProductBo)) {
            return null;
        }
        RejectOrderDO rejectOrderDO = new RejectOrderDO();
        rejectOrderDO.setSheetId(rejectProductBo.getSheetId());
        rejectOrderDO.setApplySheetId(rejectProductBo.getApplySheetId());
        rejectOrderDO.setChannelCode(rejectProductBo.getChannelCode());
        rejectOrderDO.setChannelName(rejectProductBo.getChannelName());
        rejectOrderDO.setRejectType(rejectProductBo.getRejectType());
        rejectOrderDO.setSheetDate(rejectProductBo.getSheetDate());
        rejectOrderDO.setToChannelName(rejectProductBo.getToChannelName());
        rejectOrderDO.setRemark(rejectProductBo.getRemark());
        return rejectOrderDO;
    }

    public static List<RejectOrderDO> convertByRejectProductBo(Collection<RejectProductBO> rejectProductBos) {
        if (CollectionUtils.isEmpty(rejectProductBos)) {
            return new ArrayList<>();
        }
        List<RejectOrderDO> rejectOrderDos = new ArrayList<>();
        for (RejectProductBO rejectProductBo : rejectProductBos) {
            rejectOrderDos.add(convert(rejectProductBo));
        }
        return rejectOrderDos;
    }

}
