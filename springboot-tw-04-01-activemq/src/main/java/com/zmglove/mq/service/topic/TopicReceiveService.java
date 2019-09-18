package com.zmglove.mq.service.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TopicReceiveService {

    @JmsListener(destination = "mytest.topic",containerFactory = "topicListenerFactory")
    public void receiveMessage(String message){
        log.info("订阅收到的消息为:{}",message);
    }
}
