package com.ceesing.cloud.service.impl;

import com.ceesing.cloud.mapper.AccountMapper;
import com.ceesing.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    @Override
    public void decrease(Long userId, Long money) {
        // 扣减账户余额
        log.info("------->account-service中扣减账户余额开始");
        accountMapper.decrease(userId,money);
        // 超时
        /*try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        //int i = 10 / 0;
        log.info("------->account-service中扣减账户余额结束");
    }
}
