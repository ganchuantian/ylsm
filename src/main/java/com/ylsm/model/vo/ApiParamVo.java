package com.ylsm.model.vo;

import com.ylsm.constants.Constants;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description:
 * @author: hqy
 * @create: 2023-11-10 14:10
 **/
@Data
@Accessors(chain = true)
public class ApiParamVo {

    private Date queryStartDate;

    private Date queryEndDate;

    private Constants.ApiOperationType type;

}
