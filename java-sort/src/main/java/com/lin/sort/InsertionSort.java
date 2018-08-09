package com.lin.sort;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author: Linjb
 *
 * @Date: 2018/7/30
 *
 * @name: 插入排序
 *
 * @Description:
 */
public class InsertionSort {

    @Test
    public void excute(){
        SortHelper.doSort(SortHelper.getArray(),level1());
    }

    public Consumer<int[]> level1(){

        return (array) -> {

            for(int i = 1 ; i < array.length ; i++) {
                for(int j = 0 ; j < i ; j++) {
                    if (array[i] < array[j]) {

                        int tmp = array[i];
                        array[i] = array[j];
                        array[j] = tmp;
                    }
                }
            }
        };
    }
}
