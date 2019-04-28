package com.zmglove;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 启动入口
 *
 * @author CZH
 * @version 1.0
 * @date 2019/4/19 19:26
 **/
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public DataLoader dataLoader(){
        return new DataLoader();
    }


    @Slf4j
    static class DataLoader implements CommandLineRunner{

        @Override
        public void run(String... args) throws Exception {
            log.info("Loading data..........");
        }
    }
}
