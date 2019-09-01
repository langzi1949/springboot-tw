package com.zmglove.web;


import com.zmglove.mq.service.topic.TopicSendService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TopicTest extends ApplicationTest {

    @Autowired
    private TopicSendService topicSendService;

    @Test
    public void topicTest() throws InterruptedException {
        topicSendService.sendMessage("Wade.........");

        Thread.sleep(10000);
    }
}
