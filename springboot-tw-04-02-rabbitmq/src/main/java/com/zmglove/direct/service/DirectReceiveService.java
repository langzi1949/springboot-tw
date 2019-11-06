package com.zmglove.direct.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 消息的接受者
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/23 15:46
 **/
@Service
@RabbitListener(queues = "hello")
@Slf4j
public class DirectReceiveService {
    @RabbitHandler
    public void process(String message){
      log.info("获取的消息为:{}",message);
    }
}
