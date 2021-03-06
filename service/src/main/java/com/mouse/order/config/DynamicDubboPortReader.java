package com.mouse.order.config;

import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;

import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.config.ProtocolConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 一个机器部署两个dubbo生产者会产生端口占用问题，为了解决这个问题，在加载dubbo配置文件之前，先设置没被占用的端口
 * @author cookie
 */
@Component
public class DynamicDubboPortReader implements ApplicationContextAware{
    @Autowired
    private ApplicationContext applicationContext;

    private int port = 20880;

    @PostConstruct
    public void init(){
        Map<String, ProtocolConfig> map = applicationContext.getBeansOfType(ProtocolConfig.class);
        for(Entry<String,ProtocolConfig> con: map.entrySet()){
            port = NetUtils.getAvailablePort();
            System.out.println("=========="+port);
            con.getValue().setPort(port);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext)applicationContext;
    }

}
