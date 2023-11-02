package com.ylsm.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenModel {

    private Integer tokenId;

    private String token;

}
