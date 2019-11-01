package com.zmglove.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * 进行算法的练习
 *
 * 题目来源，leetcode #20  Valid Parentheses
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/31 10:40
 **/
@Slf4j
public class AlgorithmTest {



    @Test
    public void test(){
//        log.info("result = {}",isValid("()[]{"));
//
//        String str = "abcdefg";
//        char[] ch = str.toCharArray();
//        rotateString(ch,3);



        log.info("{}",strStr("source","rc"));
    }


    public int strStr(String source, String target) {

        return source.indexOf(target);
    }

    public char firstUniqChar(String str) {
        // Write your code here

        List<Character>  list = new ArrayList<>();
        List<Character> existList = new ArrayList<>();
        for(int i =0 ;i<str.length();i++){
            char ch =  str.charAt(i);
            if(list.contains(ch) && !existList.contains(ch)){
                list.remove(Character.valueOf(ch));
                existList.add(ch);
            }else if(!list.contains(ch) && !existList.contains(ch)){
                list.add(ch);
            }
        }

        if(list.size() ==0){
            return ' ';
        }else{
            return list.get(0);
        }
    }



    public  void rotateString(char[] str, int offset) {
        // write your code here
        if(str ==null || str.length ==0){
            return;
        }
        // 先算出偏移的数据
        int num = offset % str.length;

        if(num ==0){
            return;
        }

        // 只要偏移两个子数组+ 最终数组就行了
        reserve(str, str.length-num,str.length-1);
        reserve(str,0,str.length-num-1);
        reserve(str,0,str.length-1);
    }

    private void reserve(char[] str,int start ,int end){
        for(;start<end;start++,end--){
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
        }
    }

    /**
     *  leetcode #20 Valid Parentheses
     * @param s
     * @return
     */
    private static  boolean isValid(String s){
        // 首先定义一下括号对
        Map<Character, Character> parenthesesMap = new HashMap<>();
        parenthesesMap.put('}', '{');
        parenthesesMap.put(']', '[');
        parenthesesMap.put(')', '(');

        // 优先判断字符串的长度是否是奇数
        if (s == null || "".equals(s) || s.length() % 2 != 0) {
            return false;
        }

        // 定义一个栈
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            // 判断是否是右括号，
            if (parenthesesMap.containsKey(s.charAt(i))) {
                // 取出stack最上一个字符
                char top = stack.isEmpty() ? '#' : stack.pop();
                if (top != parenthesesMap.get(s.charAt(i))) {
                    return false;
                }
            } else {
                // 将字符放进stack中
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

}
