package com.mouse.rocketmq;

import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2022/1/26 5:00 下午
 */
@Component
public class Producer {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private StreamBridge messageChannel;

    public boolean sendMsg(String msg) {
        return messageSource.output1().send(MessageBuilder.withPayload(msg).build());
//        return messageChannel.send("test-topic", MessageBuilder.withPayload("{\"a\":134}").build());
    }

    /**
     * RabbitMQ header设置 x-delay 延迟5000ms
     * @param msg
     * @return
     */
    public boolean sendMsgDelay(String msg) {
        return messageSource.output1().send(MessageBuilder.withPayload(msg)
                        .setHeader("x-delay", 5000)
                .build());
    }

    /**
     * RocketMQ header设置 DELAY
     * 每个延迟级别对应的延迟时间可以通过配置messageDelayLevel
     * 默认值为“1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h”。
     * 该配置属于 Broker，对所有 Topic 有效！默认的level值为 0，代表非延迟消息，超过 18 按最大值 18 计算。
     * @param msg
     * @return
     */
    public boolean sendMsgDelayViaRocketMQ(String msg) {
        return messageSource.output1().send(MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, 2)
                .build());
    }
}
