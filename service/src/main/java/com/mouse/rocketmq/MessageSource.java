package com.mouse.rocketmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2022/1/26 5:00 下午
 */
public interface MessageSource {

    /**
     * 消息管道
     * @return
     */
    @Output("output1")
    MessageChannel output1();

}
