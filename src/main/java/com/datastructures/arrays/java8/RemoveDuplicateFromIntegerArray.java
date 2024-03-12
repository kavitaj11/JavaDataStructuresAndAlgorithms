package com.datastructures.arrays.java8;

import java.util.*;

public class RemoveDuplicateFromIntegerArray {

    //Using Java8
    public static int[] removeDuplicatesUsingArraysStream(int[] arr) {

        return Arrays.stream(arr)
                .distinct().toArray();
    }

    //Using HashSet
    //Use LinkedHashSet to preserve the input order
    public static int[] removeDuplicatesUsingHashSet(int[] arr){
        int end = arr.length;
        Set<Integer> set = new HashSet<>();

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
