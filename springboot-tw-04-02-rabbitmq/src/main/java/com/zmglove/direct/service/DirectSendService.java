package com.zmglove.direct.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 消息发送
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/23 15:09
 **/
@Service
@Slf4j
public class DirectSendService {

    @Autowired
    private AmqpTemplate rabbitTemple;

    public void send(){
        String context = "Hello World :"+new Date();

        log.info(">>>>>>>准备发送啦,消息体:{}",context);

        rabbitTemple.convertAndSend("hello",context);
    }


}
