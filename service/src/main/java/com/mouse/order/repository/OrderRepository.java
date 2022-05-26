package com.mouse.order.repository;

import com.mouse.order.repository.db.mapper.OrderMapper;
import com.mouse.order.repository.db.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2021/11/17 8:37 下午
 */
@Component
public class OrderRepository {
    @Autowired
    OrderMapper orderMapper;

    public int insertOrder(Order order) {
        return orderMapper.insert(order);
    }

    public int updateOrder(Order order) {
        return orderMapper.updateById(order);
    }

    public Order getOrderById(Long id) {
        return orderMapper.selectById(id);
    }



}
