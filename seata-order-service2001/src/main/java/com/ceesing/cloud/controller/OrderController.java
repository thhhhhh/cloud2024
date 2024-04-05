package com.ceesing.cloud.controller;

import com.ceesing.cloud.entities.Order;
import com.ceesing.cloud.resp.ResultData;
import com.ceesing.cloud.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public ResultData create(Order order)
    {
        log.info("------->交易开始");
        orderService.create(order);
        log.info("------->交易结束");
        return ResultData.success(order);
    }
}
