package com.zmglove.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 读取配置
 *
 * @author CZH
 * @version 1.0
 * @date 2019/4/24 15:15
 **/
@Data
@ConfigurationProperties(prefix = "com.zmglove.tw")
@Configuration
public class StudentApiConfig {

    private String desc;
    private String apiUri;
    private String nameSpace;
    private String value;
    private Integer number;

}
