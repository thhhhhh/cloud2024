package com.ceesing.cloud.controller;

import com.ceesing.cloud.resp.ResultData;
import com.ceesing.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping("/decrease")
    public ResultData decrease(Long userId, Long money) {
        accountService.decrease(userId, money);
        return ResultData.success("账户余额扣减成功！");
    }
}
