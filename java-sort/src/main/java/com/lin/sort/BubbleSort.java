package com.lin.sort;

import org.junit.Test;

/**
 * @author: Linjb
 *
 * @Date: 2018/7/30
 *
 * @name: 冒泡排序
 *
 * @Description:
 */
public class BubbleSort {


    @Test
    public void level1(){

        int[] array = SortHelper.getArray();

        System.out.println("before:");
        SortHelper.printArray(array);

        for (int i = 0; i < array.length; i++) {
            for(int j = 1;j<array.length;j++) {
                if (array[j - 1] > array[j]) {
                    int tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
            }
        }

        System.out.println("\nafter:");
        SortHelper.printArray(array);


    }

}
