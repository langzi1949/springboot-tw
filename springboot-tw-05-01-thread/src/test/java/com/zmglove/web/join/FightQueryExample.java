package com.zmglove.web.join;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 样例
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/10 14:19
 **/
@Slf4j
public class FightQueryExample {

    private static final List<String> fightCompany = Arrays.asList("CSA","CEA","HNA");


    public static void main(String[] args){
        List<String> results = search("SH","BJ");
        log.info("*************最终的查询结果***************");
        results.forEach(log::info);
    }

    private static List<String> search(String origin,String destination){
        final List<String> result = new ArrayList<>();

        // 创建线程任务
        List<FightQueryTask> tasks = fightCompany.stream().map(f -> createTask(f,origin,destination)).collect(Collectors.toList());
        // 启动线程
        tasks.forEach(Thread::start);
        tasks.forEach(t ->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 在此之前，当前的线程会阻塞住
        tasks.stream().map(FightQuery::get).forEach(result::addAll);

        return result;
    }


    /**
     * 创建任务
     * @param fight
     * @param origin
     * @param destination
     * @return
     */
    private static FightQueryTask createTask(String fight,String origin,String destination){
        return new FightQueryTask(fight, origin,destination);
    }
}
