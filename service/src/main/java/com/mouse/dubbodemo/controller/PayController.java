package com.mouse.dubbodemo.controller;

import com.mouse.dubbodemo.intf.StuRpcService;
import com.mouse.rocketmq.Producer;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@RestController
@RequestMapping(value = "/pay")
public class PayController {
    @DubboReference(version = "1.0.0", group = "dubbo-demo")
    StuRpcService stuRpcService;

    @Autowired
    private Producer producer;

    @Value("${spring.profiles.active}")
    String active;

    @GetMapping(value = "/toPay")
    public String pay(){
        log.info("hello world");
        int a = stuRpcService.add(5,1);
        return "success!" + a + active;
    }

    @GetMapping(value = "/sendMessage")
    public String sendMessage(
            @RequestParam("msg") String msg
    ){
        return producer.sendMsg(msg)? "true":"false";
    }

    @GetMapping(value = "/sendDelayMessage")
    public String sendDelayMessage(
            @RequestParam("msg") String msg
    ){
        return producer.sendMsgDelayViaRocketMQ(msg)? "true":"false";
    }

    /**
     * 执行shell命令
     */
    @RequestMapping(value = "/exec", method = RequestMethod.POST)
    public String exec(
            @RequestParam(value = "command") String command,
            @RequestParam(value = "path", required = false) String path
    ) {
        log.info("command=" + command);
        execCommand("sh " + path);
        execCommand(command);

        return "{}";
    }

    private int execCommand(String command) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            val result = process.waitFor();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                log.info("input={}", line);
            }
            input.close();

            log.info("result={}", result);

            return result;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
