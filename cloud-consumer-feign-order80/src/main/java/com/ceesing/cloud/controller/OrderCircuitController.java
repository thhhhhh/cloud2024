package com.ceesing.cloud.controller;

import com.ceesing.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/feign/circuit")
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @RequestMapping(value = "/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuit(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    public String myCircuitFallback(Integer id, Throwable e) {
        log.error("请求id ：{}", id);
        log.error("异常信息：{}", e.getMessage());
        return "myCircuitFallback: 系统繁忙，请稍后再试  /(ㄒoㄒ)/~~";
    }

    /**
     *(船的)舱壁,隔离
     * @param id
     * @return
     */
    @GetMapping(value = "/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback",type = Bulkhead.Type.SEMAPHORE)
    public String myBulkhead(@PathVariable("id") Integer id)
    {
        return payFeignApi.myBulkhead(id);
    }
    public String myBulkheadFallback(Throwable t)
    {
        return "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
    }

    /**
     *(船的)舱壁,隔离
     * @param id
     * @return
     */
    @GetMapping(value = "/bulkhead/threadPool/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadThreadPoolFallback",type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadThreadPool(@PathVariable("id") Integer id)
    {
        System.out.println(Thread.currentThread().getName() + "\t" + "---- 线程开始执行");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "---- 线程准备离开");
        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id));
    }
    public CompletableFuture<String> myBulkheadThreadPoolFallback(Integer id, Throwable t)
    {
        return CompletableFuture.supplyAsync(() -> "myBulkheadThreadPoolFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
    }

    @GetMapping(value = "/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service",fallbackMethod = "myRatelimitFallback")
    public String myBulkheadratelimit(@PathVariable("id") Integer id)
    {
        return payFeignApi.myRatelimit(id);
    }
    public String myRatelimitFallback(Integer id,Throwable t)
    {
        return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
    }

}
