package com.ceesing.cloud.service.impl;

import com.ceesing.cloud.entities.Pay;
import com.ceesing.cloud.mapper.PayMapper;
import com.ceesing.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl implements PayService {
    @Resource
    private PayMapper payMapper;

    @Override
    public int add(Pay pay) {
        // insertSelective() 用于保存一个 pay 实体，null 值字段不会保存到数据库，使用数据库字段的默认值
        // insert() 用于保存一个 pay 实体，null 值字段会保存到数据库，不会使用数据库字段的默认值
        return payMapper.insertSelective(pay);
    }

    @Override
    public int delete(Integer id) {
        // payMapper.deleteByExample() 用于根据条件删除数据
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        // updateByPrimaryKeySelective 根据主键更新数据不为null的值，null 值字段使用数据库默认值
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
