package com.datastructures.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicate {

    public static int[] removeDuplicatesIterative(int[] arr) {
        //Sort the array
        Arrays.sort(arr);
        //Create new array to store unique numbers from given array
        int newArray[] = new int[arr.length];
        newArray[0] = arr[0];
        int k = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] > arr[i]) {
                newArray[k] = arr[i + 1];
                k++;
            }
        }
        newArray = Arrays.copyOf(newArray, k);
        arr = newArray;
        System.out.println(Arrays.toString(newArray));
        return arr;
    }


    //Using Java8
    public static int[] removeDuplicatesUsingArraysStream(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    //Using HashSet
    //Use LinkedHashSet to preserve the input order
    public static int[] removeDuplicatesUsingHashSet(int[] arr){
        int end = arr.length;
        Set<Integer> set = new LinkedHashSet<>();
        for(int i = 0; i < end; i++){
            set.add(arr[i]);
        }
        //now if you iterate through this set, it will contain only unique values.
        set.forEach(System.out::println);
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String args[]){
        int arr[]={2,5,3,7,8,9,10,13,57,38, 6, 8, 10, 13};
        removeDuplicatesUsingHashSet(arr);
    }


}
