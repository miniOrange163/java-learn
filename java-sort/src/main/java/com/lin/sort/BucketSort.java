package com.lin.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author: Linjb
 *
 * @Date: 2018/7/30
 *
 * @name: 桶排序
 *
 * @Description:
 */
public class BucketSort {

    @Test
    public void excute(){
        SortHelper.doSort(SortHelper.getArray(),level1());
    }


    public Consumer<int[]> level1(){

        return (array) -> {

            bucketSort(array);
        };
    }

    /**
     * 适合场景：数组的值范围比较狭小分布比较均匀的情况,如给500W高考考生成绩进行排序
     * @param array
     */
    private void bucketSort(int[] array) {

        int max = 1;
        for (int i = 0; i < array.length; i++) {
            max = max > array[i] ? max : array[i];
        }

        int list[] = new int[max+1];

        for (int i = 0; i < array.length; i++) {
            list[array[i]]++;
        }
        int index = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] > 0) {
                int len = list[i];
                for (int j = 0; j < len; j++) {
                    array[index++] = i;
                }
            }
        }
    }

}
