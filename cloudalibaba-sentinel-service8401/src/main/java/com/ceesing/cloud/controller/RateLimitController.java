package com.ceesing.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource/{id}")
    @SentinelResource(value = "byResource", blockHandler = "handleException", fallback = "fallbackHandler")
    public String byResource(@PathVariable("id") Integer id,
                             @RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2) {
        if (id == 0) {
            throw new RuntimeException("id不能等于0，报服务端异常");
        }
        return "测试@SentinelResource限流案例返回成功，id = " + id;
    }
    public String handleException(@PathVariable("id") Integer id,
                                  @RequestParam(value = "p1",required = false)String p1,
                                  @RequestParam(value = "p2",required = false)String p2,
                                  BlockException exception) {
        return "测试@SentinelResource限流案例 -- 发生限流";
    }
    public String fallbackHandler(@PathVariable("id") Integer id,
                                  @RequestParam(value = "p1",required = false)String p1,
                                  @RequestParam(value = "p2",required = false)String p2,
                                  Throwable throwable) {
        throwable.printStackTrace();
        return "测试@SentinelResource限流案例 -- 服务器发生错误";
    }
}
