package com.ceesing.cloud.service.impl;

import com.ceesing.cloud.apis.AccountFeignApi;
import com.ceesing.cloud.apis.StorageFeignApi;
import com.ceesing.cloud.entities.Order;
import com.ceesing.cloud.mapper.OrderMapper;
import com.ceesing.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AccountFeignApi accountFeignApi;
    @Resource
    private StorageFeignApi storageFeignApi;

    @Override
    @GlobalTransactional(name = "ceesing-cerate-order", rollbackFor = Exception.class)
    public void create(Order order) {
        //0、xid全局事务id检查
        String xid = RootContext.getXID();
        log.info("开始创建新订单 XID: {}",xid);

        // 1、新增订单 -- 状态：0（创建中）
        order.setStatus(0);
        int result = orderMapper.insert(order);

        if (result > 0) {
            // 获取插入的order
            Order fromDB = orderMapper.selectOne(order);
            log.info("新建的订单数据为：" + fromDB);
            System.out.println();

            // 2、扣减库存
            log.info("订单微服务开始调用storage，扣减库存开始");
            storageFeignApi.decrease(fromDB.getProductId(), fromDB.getCount());
            log.info("订单微服务完成调用storage，库存扣减完成");
            System.out.println();

            //3、扣减账户
            log.info("订单微服务开始调用account，扣减账户开始");
            accountFeignApi.decrease(fromDB.getUserId(), fromDB.getMoney());
            log.info("订单微服务完成调用account，账户扣减完成");
            System.out.println();

            //4、修改订单状态：1（已完结）
            log.info("订单微服务开始修改订单状态，修改为：1（已完结）");
            fromDB.setStatus(1);
            int updateResult = orderMapper.updateByPrimaryKey(fromDB);
            log.info("订单微服务完成修改订单状态，修改为：1（已完结），修改结果 = " + updateResult);
            System.out.println();
        }
        log.info("新订单创建成功 XID: {}",xid);
    }
}
