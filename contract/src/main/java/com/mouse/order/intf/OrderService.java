package com.mouse.order.intf;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2022/1/12 9:56 上午
 */
public interface OrderService {
    long create();

    /**
     * 改变订单的状态 status 0:创建 1: 完成 2:取消
     * @param orderId
     * @return
     */
    int buy(long orderId);
}
