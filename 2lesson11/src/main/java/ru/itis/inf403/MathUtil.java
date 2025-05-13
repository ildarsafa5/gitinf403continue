package ru.itis.inf403;

import java.util.Arrays;

public class MathUtil {
    public static int sum(int a, int b) {
        return a+b;
    }

    /**
     *
     * @return >0, a>b; <0, a<b; 0, a=b
     */
    public static int compare(int a,int b) {
        return a-b;
    }

    public static int divide(int a, int b) throws IllegalArgumentException{
        if (b==0) {
            throw new IllegalArgumentException("Знаменатель не 0!");
        }
        return a/b;
    }

    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minpos = i;
            for (int j = i; j < array.length; j++) {
                if (array[j]<array[minpos]) {
                    minpos = j;
                }
            }
            int temp = array[i];
            array[i] = array[minpos];
            array[minpos] = temp;
        }
        return array;
    }

}
