package com.lin.sort;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author: Linjb
 *
 * @Date: 2018/7/30
 *
 * @name: 快速排序
 *
 * @Description:
 */
public class QuickSort {

    @Test
    public void excute(){
        SortHelper.doSort(SortHelper.getArray(),level1());
    }

    public Consumer<int[]> level1(){

        return (array) -> {

            quick(array , 0 , array.length-1);

        };
    }

    private void quick(int[] array, int first, int last) {

        if (first < last) {

            int tmp = array[first];

            int i = first , j = last;

            while (i < j) {
                while (array[j] > tmp && i < j) {
                    j--;
                }
                array[i++] = array[j];
                while (array[i] < tmp && i < j) {
                    i++;
                }
                array[j--] = array[i];
            }
            array[j+1] = tmp;
            quick(array, first, j);
            quick(array, i , last);
        }
    }

}
