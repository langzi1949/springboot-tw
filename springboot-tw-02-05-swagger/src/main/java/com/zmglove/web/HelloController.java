package com.zmglove.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 第一个Controller
 *
 * @author CZH
 * @version 1.0
 * @date 2019/4/19 19:27
 **/
@RestController
@ApiIgnore
public class HelloController {
    @RequestMapping("/hello")
    public String index(){
        return "Hello World";
    }
}