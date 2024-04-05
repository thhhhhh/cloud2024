package com.ceesing.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {
    @GetMapping("/getAuth")
    @SentinelResource(value = "getAuth", blockHandler = "handleException", fallback = "fallbackHandler")
    public String getAuth(@RequestParam(value = "serverName",required = false)String serverName) {
        log.info("getAuth");
        return "Sentinel授权访问成功, serverName = " + serverName;
    }
    public String handleException(@RequestParam(value = "serverName",required = false)String serverName, Throwable throwable) {
        throwable.printStackTrace();
        return "服务降级了";
    }
    public String fallbackHandler(@RequestParam(value = "serverName",required = false)String serverName, Throwable throwable) {
        throwable.printStackTrace();
        return "没有访问权限";
    }
}
