package com.zmglove.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/4/28 18:18
 **/
@Slf4j
public class ApplicationPreEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        log.info("#####ApplicationPreEvent#####");
    }
}
