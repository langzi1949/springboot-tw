package com.zmglove.web.join;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 航班查询的
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/10 14:11
 **/
@Slf4j
public class FightQueryTask extends Thread implements FightQuery {

    private final String origin;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    public FightQueryTask(String airline, String origin, String destination) {
        super("[" + airline + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void run(){
        log.info("{}-query from {} -> {} ",getName(),origin,destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.flightList.add(getName() + "-" +randomVal);
            log.info("The Fight:{} list query successful !!!!",getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        return this.flightList;
    }
}
