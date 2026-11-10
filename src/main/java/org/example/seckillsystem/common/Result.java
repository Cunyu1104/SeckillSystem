package org.example.seckillsystem.common;

import lombok.Data;

@Data   // Lombok：自动生成 getter/setter，省得手写
public class Result<T> {          // <T>：data 可以是任意类型
    private int code;
    private String message;
    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.code = ResultCode.SUCCESS.getCode();
        r.message = ResultCode.SUCCESS.getMessage();
        r.data = data;
        return r;
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        Result<T> r = new Result<>();
        r.code = resultCode.getCode();
        r.message = resultCode.getMessage();
        return r;
    }

    // 保留：提示语需要临时拼出来时用，比如
    // Result.fail(ResultCode.BAD_REQUEST.getCode(), "activityId 不能为空")
    public static <T> Result<T> fail(int code, String message) {
        Result<T> r = new Result<>();
        r.code = code;
        r.message = message;
        return r;
    }
}
