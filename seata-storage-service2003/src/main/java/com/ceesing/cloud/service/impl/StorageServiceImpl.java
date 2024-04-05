package com.ceesing.cloud.service.impl;

import com.ceesing.cloud.mapper.StorageMapper;
import com.ceesing.cloud.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;
    @Override
    public void decrease(Long productId, Integer count) {
      log.info("开始扣减库存");
      storageMapper.decrease(productId,count);
      log.info("扣减库存结束");
    }
}
