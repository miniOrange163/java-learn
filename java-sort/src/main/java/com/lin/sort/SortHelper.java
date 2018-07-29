package com.lin.sort;

import java.util.Random;
import java.util.function.Consumer;

/**
 * @author: Linjb
 *
 * @Date: 2018/7/30
 *
 * @name:
 *
 * @Description:
 */
public class SortHelper {

    public static int[] getRandomArray(int length){

        int[] array = new int[length];

        Random random = new Random(100);

        for(int i = 0 ;i<length;i++) {
            array[i] = random.nextInt();
        }

        return array;
    }

    public static int[] getArray(){

        int[] array = new int[]{50,13,2,78,23,34,88,43,11,49};

        return array;
    }

    public static void printArray(int[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
    }

    public static void doSort(int[] array, Consumer<int[]> consumer) {

        System.out.println("before:");
        SortHelper.printArray(array);

        consumer.accept(array);

        System.out.println("\nafter:");
        SortHelper.printArray(array);
    }
}
