package org.example.seckillsystem.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(200, "成功"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未登录或登录已过期"),
    SERVER_ERROR(500, "系统繁忙，请稍后重试"),
    SOLD_OUT(1004, "手慢了，商品已售罄");

    private final int code;
    private final String message;

//    ResultCode(int code, String message) {
//        this.code = code;
//        this.message = message;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
}
