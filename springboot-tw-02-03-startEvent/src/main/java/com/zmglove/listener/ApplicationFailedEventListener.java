package com.zmglove.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/4/28 18:16
 **/
@Slf4j
public class ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        log.info("#####ApplicationFailedEvent#####");
    }
}
