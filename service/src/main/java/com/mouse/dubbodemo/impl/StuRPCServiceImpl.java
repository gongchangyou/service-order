package com.mouse.dubbodemo.impl;

import com.mouse.dubbodemo.intf.StuRpcService;
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
@DubboService(version = "1.0.0", group = "dubbo-demo") //group是小的分类
public class StuRPCServiceImpl implements StuRpcService {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public int add(int i, int j) {
        Map<String, ProtocolConfig> map = applicationContext.getBeansOfType(ProtocolConfig.class);
        int port = 0;
        for(Map.Entry<String,ProtocolConfig> con: map.entrySet()){
            port = con.getValue().getPort();
        }
        return i + j + port;
    }
}
