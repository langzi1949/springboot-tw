package com.zmglove.mq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * 消息接收服务
 */
@Service @Slf4j
public class QueueReceiveService {

    @JmsListener(destination = "mytest.queue",containerFactory = "queueListenerFactory")
    public void receiveQueue(String message){
        log.info(">>>>>> 消费者接收到的消息:{}",message);
    }
}
