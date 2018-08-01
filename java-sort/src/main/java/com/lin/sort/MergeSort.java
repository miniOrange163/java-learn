package com.lin.sort;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author: Linjb
 *
 * @Date: 2018/7/30
 *
 * @name: 归并排序
 *
 * @Description:
 */
public class MergeSort {

    @Test
    public void excute(){
        SortHelper.doSort(SortHelper.getArray(),level1());
    }

    public Consumer<int[]> level1(){

        return (array) -> {

            mergeSort(array,0,array.length-1);

        };
    }

    private void mergeSort(int[] array, int firstIndex,  int lastIndex) {

        if (firstIndex < lastIndex) {
            int mid = (firstIndex + lastIndex) / 2;
            mergeSort(array, firstIndex, mid);
            mergeSort(array, mid + 1, lastIndex);
            merge(array, firstIndex, mid, lastIndex);
        }
    }

    private void merge(int[] array, int firstIndex, int midIndex, int lastIndex) {

        int[] tmp = new int[lastIndex - firstIndex + 1];
        int i = firstIndex, j = midIndex+1, k = 0;
        while (i <= midIndex && j <= lastIndex) {
            if (array[i] < array[j]) {
                tmp[k++] = array[i++];
            }
            else{
                tmp[k++] = array[j++];
            }
        }
        while (i <= midIndex) {
            tmp[k++] = array[i++];
        }
        while (j <= lastIndex) {
            tmp[k++] = array[j++];
        }
        System.arraycopy(tmp,0,array,firstIndex,tmp.length);
    }


}
