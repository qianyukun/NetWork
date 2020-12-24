/*
 * Copyright © 2014-2020 Kikatech.com, All Rights Reserved.
 */

package com.kika.network.bean;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.Serializable;

/**
 *
 * @author ywx
 * @date 2020-12-17
 */
@JsonObject
public class Result<T> implements Serializable {

    @JsonField
    public int errorCode;
    @JsonField
    public String errorMsg;
    @JsonField
    public T data;

    public Result() {
    }

    public int getCode() {
        return errorCode;
    }

    public String getMessage() {
        return errorMsg;
    }

    public T getData() {
        return data;
    }

}
