package com.lin.sort;

import org.junit.Test;

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
    public void test(){

        int[] array = SortHelper.getArray();
        for (int i : array) {
            System.out.print(i + "\t");
        }
        mergeSort(array,0,array.length-1);
        System.out.println();
        for (int i : array) {
            System.out.print(i + "\t");
        }

    }
    private static void merge(int[] array,int firstIndex,int midIndex,int lastIndex){

        int[] tmp = new int[lastIndex - firstIndex + 1];

        int i = firstIndex , j = midIndex+1 , k = 0;
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

        System.arraycopy(tmp, 0, array, firstIndex, tmp.length);

    }

    private void mergeSort(int[] array,int firstIndex,int lastIndex){


        if (firstIndex < lastIndex) {
            int mid = (firstIndex + lastIndex) / 2;

            mergeSort(array, firstIndex, mid);
            mergeSort(array, mid+1, lastIndex);
            merge(array,firstIndex,mid,lastIndex);
        }


    }

    /**
     * 　*　<pre>
     * 　*　二路归并
     * 　*　原理：将两个有序表合并和一个有序表
     * 　*　</pre>
     * 　*
     * 　*　@param　a
     * 　*　@param　s
     * 　*　第一个有序表的起始下标
     * 　*　@param　m
     * 　*　第二个有序表的起始下标
     * 　*　@param　t
     * 　*　第二个有序表的结束小标
     * 　*
     */
    /*private static void merge(int[] a, int s, int m, int t) {
        int[] tmp = new int[t - s + 1];
        int i = s, j = m, k = 0;
        while (i < m && j <= t) {
            if (a[i] <= a[j]) {
                tmp[k] = a[i];
                k++;
                i++;
            } else {
                tmp[k] = a[j];
                j++;
                k++;
            }
        }
        while (i < m) {
            tmp[k] = a[i];
            i++;
            k++;
        }
        while (j <= t) {
            tmp[k] = a[j];
            j++;
            k++;
        }
        System.arraycopy(tmp, 0, a, s, tmp.length);
    }*/

    /**
     * 　*
     * 　*　@param　a
     * 　*　@param　s
     * 　*　@param　len
     * 　*　每次归并的有序集合的长度
     */
    /*public static void mergeSort(int[] a,  int len) {
        int size = a.length;
        int mid = size / (len << 1);
        int c = size & ((len << 1) - 1);
        //　-------归并到只剩一个有序集合的时候结束算法-------//
        if (mid == 0)
            return;
        //　------进行一趟归并排序-------//

        for (int i = 0; i < mid; ++i) {
            int s = i * 2 * len;
            System.out.println("s:" + s + " m:" + (s + len) + " t:" + ((len << 1) + s - 1));
            merge(a, s, s + len, (len << 1) + s - 1);
        }
        //　-------将剩下的数和倒数一个有序集合归并-------//
        if (c != 0)
            merge(a, size - c - 2 * len, size - c, size - 1);
        //　-------递归执行下一趟归并排序------//
        mergeSort(a,  2 * len);
    }*/

    /*public static void main(String[] args) {
        int[] a = new int[]{4, 3, 6, 1, 2, 5,10,6};
        mergeSort(a, 1);
        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + "　");
        }
    }*/
}
