package com.zmglove.web;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OverloadTest {
    static class Human{}
    static class Man extends Human{}
    static class Woman extends Human{}

    public void sayHello(Human human){
        log.info("human say Hello");
    }
    public void sayHello(Man man){
        log.info("man say Hello");
    }
    public void sayHello(Woman woman){
        log.info("woman say Hello");
    }


    public static void main(String[] args) {
        Human man = new Man();
        Woman woman = new Woman();

        OverloadTest test = new OverloadTest();
        test.sayHello(man);
        test.sayHello(woman);
    }


}
