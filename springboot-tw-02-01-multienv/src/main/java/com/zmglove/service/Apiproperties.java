package com.zmglove.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Api的配置信息
 *
 * @author CZH
 * @version 1.0
 * @date 2019/4/22 17:28
 **/
@Component
@Data
public class Apiproperties {
    @Value("${com.zmglove.tw.apiUri}")
    private String apiUri;

    @Value("${com.zmglove.tw.nameSpace}")
    private String nameSpace;

    @Value("${com.zmglove.tw.desc}")
    private String desc;

    @Value("${com.zmglove.tw.value}")
    private String value;

    @Value("${com.zmglove.tw.number}")
    private Integer number;
}
