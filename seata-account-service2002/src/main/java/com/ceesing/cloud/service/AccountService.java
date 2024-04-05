package com.ceesing.cloud.service;

public interface AccountService {
    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money  扣减金额
     */
    void decrease(Long userId, Long money);
}
