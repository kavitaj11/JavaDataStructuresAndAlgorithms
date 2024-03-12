package com.datastructures.arrays;

import java.util.Arrays;

public class InsertElement {

    public static int[] arraycopy(int arr[], int i, int value){
        arr = Arrays.copyOf(arr, arr.length + 1);
        System.arraycopy(arr, i, arr, i + 1, arr.length - i - 1);
        arr[i]=value;
        return arr;
    }


    public static void main(String args[]) {
        //Insert 30 in arr at index 2
        int[] arr = {10, 20, 40, 50};
        System.out.println(Arrays.toString(arraycopy(arr, 2, 30)));

        //using apache commons lang
       // ArrayUtils.insert(2, arr, 30);
    }


}
