package com.zmglove.mq.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * 消息发送service
 */
@Service
@Slf4j
public class QueueSendService {

    @Autowired
    private JmsTemplate jmsTemplate;


    /**
     * 发送消息
     */
    public void sendMessage(String message){
        // 消息的管道 Queue
        Destination destination =  new ActiveMQQueue("mytest.queue");
        jmsTemplate.convertAndSend(destination,message);

    }
}
