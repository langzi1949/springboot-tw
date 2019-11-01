package com.zmglove.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ的配置类
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/23 15:13
 **/
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue(){
        return new Queue("hello");
    }
}
