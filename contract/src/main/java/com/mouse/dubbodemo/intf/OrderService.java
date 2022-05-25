package com.mouse.dubbodemo.intf;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2022/1/12 9:56 上午
 */
public interface OrderService {
    int create();

    /**
     * 改变订单的状态 status 0:创建 1: 完成 2:取消
     * @param orderId
     * @return
     */
    int buy(int orderId);
}
