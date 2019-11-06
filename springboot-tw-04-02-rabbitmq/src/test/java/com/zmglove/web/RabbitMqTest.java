package com.zmglove.web;

import com.zmglove.direct.service.DirectSendService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * RabbitMQ的测试类
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/23 15:48
 **/
@Slf4j
public class RabbitMqTest extends ApplicationTest{

    @Autowired
    private DirectSendService directSendService;

    @Test
    public void hello() throws InterruptedException {
        directSendService.send();
        TimeUnit.SECONDS.sleep(2);
    }



    public static void main(String[] args){

//        int[]  nums = new int[]{5,6,7};
//
//        log.info("{}" ,twoSum(nums,11));

    }

    public static List<Integer> getNarcissisticNumbers(int n) {
        // write your code here

        List<Integer> result  = new ArrayList<>();
        if(n == 1){
            result.add(0);
        }
        int start = (int) Math.pow(10,n-1);
        int end = (int)Math.pow(10,n) -1;

        for(int i = start; i<=end;i++){
            // 具体的数据为i
            int temp =i;
            int index  =0;
            int sum = 0;
            for(int j=1;j<= n;j++){
                index = temp % 10;
                sum += Math.pow(index,n);
                temp = (temp -index) / 10;
            }
            if(sum == i){
                result.add(i);

            }
        }
        return result;
    }


    public static int singleNumber(int[] A) {
        // write your code here
        Map<Integer,Integer> map = new HashMap<>();
        for(int a :A){
            if(map.containsKey(a)){
                int value = map.get(a) +1;
                map.put(a,value);
            }else{
                map.put(a,1);
            }
        }

        for(Map.Entry<Integer,Integer> entry :map.entrySet()){
            if(entry.getValue() ==1) {
                return entry.getKey();
            }
        }
        return 0;

    }



    public static int[] twoSum(int nums[],int target){
        Map<Integer, Integer> tempMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (tempMap.containsKey(target - nums[i])) {
                return new int[]{tempMap.get(target - nums[i]), i};
            }
            tempMap.put(nums[i], i);
        }
        return null;
    }


    private static boolean isValid(String s){
        // 进行数组的校验
        if(s == null || "".equals(s) || s.length() % 2 !=0){
            return false;
        }
        int mid  = s.length() /2;

        // 取出一半的子数组
        String subStr = s.substring(0,mid);

        for(int i =0;i<subStr.length();i++){
            if (('{' != subStr.charAt(i) || '}' != s.charAt(i + mid)) &&
                    ('[' != subStr.charAt(i) || ']' != s.charAt(i + mid)) &&
                    ('(' != subStr.charAt(i) || ')' != s.charAt(i + mid))) {
                        return false;
                    }
        }
        return true;

    }


    public static  ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return  null;
    }


    class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val =  val;
        }
    }

}
