package com.datastructures.collectionsframework;

import java.util.*;
public class DisjointDemo {
    public static void main(String[] args) {
        // Let us create  arrays of integers
        Integer arr1[] = {10, 20, 30, 40, 50};
        Integer arr2[] = {60, 70, 80, 90, 100};
        Integer arr3[] = {50, 70, 80, 90, 100};
        Double  arr4[] = {50.0, 60.0, 110.0};   
        // Here we are using disjoint() method to check
        // whether two arrays are disjoint or not

        System.out.println("is arr1 disjoint to arr2 : " +
                         Collections.disjoint(Arrays.asList(arr1), Arrays.asList(arr2)));
  
        System.out.println("is arr1 disjoint to arr3 : " +
                         Collections.disjoint(Arrays.asList(arr1), Arrays.asList(arr3)));
       
        System.out.println("is arr1 disjoint to arr4 : " +
                         Collections.disjoint(Arrays.asList(arr1), Arrays.asList(arr4)));
  }
}