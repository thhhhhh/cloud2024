package com.ceesing.cloud.controller;

import com.ceesing.cloud.entities.PayDTO;
import com.ceesing.cloud.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RequestMapping(value = "/consumer")
@Tag(name = "订单消费者模块", description = "订单CRUD")
@RestController
public class OrderController {
    // 服务提供者地址
    private static final String URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    // 尽量与provider的请求路径一致
    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增订单方法")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(URL + "/pay/add", payDTO, ResultData.class);
    }

    @PostMapping(value = "/pay/delete")
    @Operation(summary = "删除", description = "删除订单方法")
    public ResultData deleteOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(URL + "/pay/delete", payDTO, ResultData.class);
    }

    @PostMapping(value = "/pay/update")
    @Operation(summary = "更新", description = "更新订单方法")
    public ResultData updateOrder(@RequestBody PayDTO payDTO) {
        log.info("deleteOrder:{}", payDTO);
        return restTemplate.postForObject(URL + "/pay/update", payDTO, ResultData.class);
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "查询", description = "根据id查询订单方法")
    public ResultData getOrderById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "查询", description = "查询所有订单方法")
    public ResultData getAllOrder() {
        return restTemplate.getForObject(URL + "/pay/getAll", ResultData.class);
    }

    @GetMapping(value = "/pay/get/info")
    @Operation(summary = "查询", description = "获取consul上配置信息的测试方法")
    public ResultData<String> getInfoByConsul() {
        return restTemplate.getForObject(URL + "/pay/get/info", ResultData.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }
}
