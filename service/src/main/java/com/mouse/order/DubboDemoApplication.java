package com.mouse.order;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mouse"}, exclude = DruidDataSourceAutoConfigure.class)
//@EnableNacosDiscovery
//@DubboComponentScan(value = "com.braindata.dubbodemo.impl")
@EnableDubbo(scanBasePackages = "com.mouse.order.impl")
@MapperScan("com.mouse.order.repository.db.mapper")
public class DubboDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoApplication.class, args);
    }

}
