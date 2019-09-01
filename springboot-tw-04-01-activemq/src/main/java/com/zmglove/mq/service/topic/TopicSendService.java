package com.zmglove.mq.service.topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * 发布主题
 */
@Service
@Slf4j
public class TopicSendService {


    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String message){
        Destination destination = new ActiveMQTopic("mytest.topic");
        log.info("开始发送主题......");
        // 发送
        jmsTemplate.convertAndSend(destination,message);
    }

}
