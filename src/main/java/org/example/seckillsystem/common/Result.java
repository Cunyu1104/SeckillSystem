package org.example.seckillsystem.common;

import lombok.Data;

@Data   // Lombok：自动生成 getter/setter，省得手写
public class Result<T> {          // <T>：data 可以是任意类型
    private int code;
    private String message;
    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.code = 200; r.message = "成功"; r.data = data;
        return r;
    }

    public static <T> Result<T> fail(int code, String message) {
        Result<T> r = new Result<>();
        r.code = code; r.message = message;
        return r;
    }
}
