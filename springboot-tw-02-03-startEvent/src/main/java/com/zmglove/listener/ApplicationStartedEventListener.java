package com.zmglove.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/4/28 18:20
 **/
@Slf4j
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("#####ApplicationStartedEvent#####");
    }
}
