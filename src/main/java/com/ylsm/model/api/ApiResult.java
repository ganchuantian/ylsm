package com.ylsm.model.api;

import com.ylsm.constants.Constants;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
public class ApiResult<T> {

    private String result;

    private String msg;

    private T data;

    public boolean success() {
        return Constants.ApiResultStatus.SUCCESS.getKey().equals(result);
    }

}
