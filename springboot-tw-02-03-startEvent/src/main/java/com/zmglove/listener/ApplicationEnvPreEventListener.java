package com.zmglove.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 启动
 *
 * @author CZH
 * @version 1.0
 * @date 2019/4/28 18:11
 **/
@Slf4j
public class ApplicationPreEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        log.info("#####ApplicationPreEvent#####");
    }
}
