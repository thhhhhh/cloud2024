package com.ceesing.cloud.service;

import com.ceesing.cloud.entities.Order;

public interface OrderService {
    /**
     * 创建新订单
     * @param order 传入的订单对象
     */
    public void create(Order order);
}
