package org.example.seckillsystem.controller;

import org.example.seckillsystem.common.Result;
import org.example.seckillsystem.common.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private int stock = 1;

    @GetMapping("/hello")
    public Result<String> hello(){
        return Result.ok("hello,seckill");
    }

    @GetMapping("/buy")
    public Result<String> buy(){
        if(stock <= 0){
            return Result.fail(ResultCode.SOLD_OUT);
        }
        stock--;
        return Result.ok("抢到了！");
    }
}
