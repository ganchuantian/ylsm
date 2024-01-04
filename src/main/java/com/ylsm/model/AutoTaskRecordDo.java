package com.ylsm.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: hqy
 * @create: 2023-12-28 17:26
 **/
@Data
@Accessors(chain = true)
// table: AutoTaskRecord
public class AutoTaskRecordDo {

    // ID
    // taskName
    private String taskName;

    // lastTime
    private String lastTime;

}
