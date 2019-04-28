package com.zmglove.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/4/28 18:22
 **/
@Slf4j
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        log.info("#####ApplicationStartingEvent#####");
    }
}
