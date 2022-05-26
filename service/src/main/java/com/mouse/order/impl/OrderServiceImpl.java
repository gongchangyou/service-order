package com.mouse.order.impl;

import com.mouse.order.intf.OrderService;
import com.mouse.order.repository.OrderRepository;
import com.mouse.order.repository.db.model.Order;
import lombok.val;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2022/1/13 1:44 下午
 */
@Component
@DubboService(version = "1.0.0", group = "order-demo") //group是小的分类
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private OrderRepository orderRepository;

    public int add(int i, int j) {
        Map<String, ProtocolConfig> map = applicationContext.getBeansOfType(ProtocolConfig.class);
        int port = 0;
        for(Map.Entry<String,ProtocolConfig> con: map.entrySet()){
            port = con.getValue().getPort();
        }
        return i + j + port;
    }


    @Override
    public long create() {
        val order = Order.builder()
                .status(0)
                .build();
        orderRepository.insertOrder(order);
        return order.getId();
    }

    /**
     * 改变订单的状态 status 0:创建 1: 完成 2:取消
     *
     * @param orderId
     * @return
     */
    @Override
    public int buy(long orderId) {
        val result = orderRepository.updateOrder(Order.builder()
                        .id(orderId)
                        .status(1)
                .build());
        return result;
    }
}
