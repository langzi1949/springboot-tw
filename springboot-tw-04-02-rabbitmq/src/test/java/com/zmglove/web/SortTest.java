package com.zmglove.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 排序算法的练习
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/31 15:32
 **/
@Slf4j
public class SortTest {

    @Test
    public void test() {
        int[] array = new int[]{8, 6, 7, 5, 3};
        insertSort(array);
        log.info("{}", array);
    }

    /**
     * 冒泡排序
     *
     * @param arr 待排序的数组
     */
    private void bubbleSort(int[] arr) {
        //进行排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            log.info("排序过程中:{}", arr);
        }
    }


    /**
     * 选择排序
     *
     * @param arr 待排序的数组
     */
    private void selectSort(int[] arr) {
        log.info("原来数组为:{}", arr);
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i; j < size; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    index = j;
                }
            }
            // 数据进行交换
            int temp = arr[i];
            arr[i] = min;
            arr[index] = temp;
            log.info("排序过程中:{}", arr);
        }
    }

    /**
     * 插入排序
     * @param arr 待排序的数组
     */
    private void insertSort(int[] arr){
        log.info("原来数组为:{}", arr);
        int size = arr.length;

       if(size <= 1){
           return;
       }

        for (int i = 1; i < size; i++) {
            int temp = arr[i];
            int index = i;
            for (int j = i-1; j >=0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                    index =j;
                }
            }
            arr[index] = temp;
            log.info("排序过程中:{}", arr);
        }
    }


}
