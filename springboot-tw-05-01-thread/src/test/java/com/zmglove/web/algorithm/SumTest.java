package com.zmglove.web.algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SumTest {

    public static void main(String[] args) {
        log.info("{}",conver(new char[]{'a','b','c','d','e','f','g'},3));

    }

    private static char[] conver(char[] str,int offset){
        // char
        String s  = new String(str);
        // 计算s的长度
        int length = s.length();

        if(offset == 0){
            return null;
        }

        // 具体的个数
        int num = offset%length;
        // 子字符串
        String lastStr = s.substring(length-num);
        s = lastStr+s.substring(0,length-num);

        str = s.toCharArray();
        return str;
    }
}
