package com.lin.sort;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author: Linjb
 *
 * @Date: 2018/7/30
 *
 * @name: 选择排序
 *
 * @Description:
 */
public class SelectionSort {

    @Test
    public void excute(){
        SortHelper.doSort(SortHelper.getArray(),level1());
    }

    public Consumer<int[]> level1(){

        return (array) -> {

            for(int i = 0 ;i < array.length ; i++) {

                int min = array[i];
                int minIndex = i;
                for(int j = i+1; j < array.length ; j++) {
                    if (array[j] < min) {
                        min = array[j];
                        minIndex = j;
                    }
                }
                int tmp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = tmp;
            }
        };
    }
}
