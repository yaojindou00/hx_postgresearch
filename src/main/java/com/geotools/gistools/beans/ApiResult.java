package com.geotools.gistools.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * @描述 请求数据的返回值
 * @创建人 ddw
 * @创建时间 2020/11/13
 */

@Data
public class ApiResult implements Serializable {
    //请求结果
    private boolean isSucceed = true;
    //请求状态
    private int code = 200;
    //请求信息
    private String message = "";
    //请求结果数据
    private Object data = new Object();

    public ApiResult() {
    }

    public ApiResult(boolean isSucceed, int code, String message, Object data) {
        this.isSucceed = isSucceed;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
