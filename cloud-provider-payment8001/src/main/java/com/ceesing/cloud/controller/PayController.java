package com.ceesing.cloud.controller;

import com.ceesing.cloud.entities.Pay;
import com.ceesing.cloud.entities.PayDTO;
import com.ceesing.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping(value = "/pay")
@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    // pay的增删改查

    @PostMapping(value = "/add")
    @Operation(summary = "新增", description = "新增支付流水方法，json串做参数")
    public String addPay(@RequestBody Pay pay) {
        // @RequestBody注解使得开发者能够方便地接收和处理HTTP请求体中的复杂数据，无需手动解析和转换
        log.info("addPay:{}", pay);
        return payService.add(pay) > 0 ? "success" : "fail";
    }

    @DeleteMapping(value = "/delete")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public String deletePay(@RequestBody Pay pay) {
        log.info("deletePay:{}", pay);
        return payService.delete(pay.getId()) > 0 ? "success" : "fail";
    }

    @PutMapping(value = "/update")
    @Operation(summary = "更新", description = "更新支付流水方法")
    public String updatePay(@RequestBody PayDTO payDTO) {
        log.info("updatePay:{}", payDTO);
        // 将PayDTO对象转换为Pay对象，到service和dao层需要使用真实的对象类型
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        return payService.update(pay) > 0 ? "success" : "fail";
    }

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "查询", description = "根据id查询支付流水方法")
    public Pay getPayById(@PathVariable("id") Integer id) {
        // 当使用@PathVariable注解时，Spring会自动将请求路径中的变量绑定到方法参数上，从而实现动态路由的功能。
        // 如果报错Name for argument of type... ，就需要为参数指定名称，如@PathVariable("id") Integer id
        log.info("getPayById:{}", id);
        return payService.getById(id);
    }

    @GetMapping(value = "/getAll")
    @Operation(summary = "查询", description = "查询所有支付流水方法")
    public List<Pay> getAllPay() {
        log.info("getAllPay");
        return payService.getAll();
    }
}