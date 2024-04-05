package com.ceesing.cloud.controller;

import com.ceesing.cloud.resp.ResultData;
import com.ceesing.cloud.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {
    @Resource
    private StorageService storageService;

    @RequestMapping("/decrease")
    public ResultData decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return ResultData.success("库存扣减成功！");
    }
}
