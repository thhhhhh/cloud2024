package com.ceesing.cloud.apis;

import com.ceesing.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-account-service")
public interface AccountFeignApi
{
    /**
     * 扣减账户余额
     * @param userId 扣减用户ID
     * @param money  扣减金额
     * @return       执行结果
     */
    @PostMapping("/account/decrease")
    ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
