package com.cslg.graduation.util;

import lombok.Data;

/**
 * @author 若竹流风
 * @version 1.0
 * @className Result
 * @description 通用返回工具类
 * @date 2020/3/25 19:43
 */
@Data
public class Result {
    private int code;//返回的业务码  0：成功执行  1：发生错误
    private String message;//信息

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.code=0;
        this.message = "执行成功";
    }
}
