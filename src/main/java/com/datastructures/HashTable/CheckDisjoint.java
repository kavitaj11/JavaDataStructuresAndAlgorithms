package com.datastructures.HashTable;

import java.util.HashSet;

//For a lookup array with m elements and a subset array with n elements, the time complexity is O(m+n).
class CheckDisjoint {

  public static boolean isDisjoint(int[] arr1, int[] arr2) {
    //Create an HashSet and store all values of arr1   
    HashSet< Integer > hSet = new HashSet < >();
    for (int value : arr1) {
      hSet.add(value);
    }
    //Traverse arr2 and check if arr1 contains any arr2 element
    for (int value : arr2) {
      if (hSet.contains(value)) return false;
    }
    return true;
  }
  
  public static void main(String args[]) {
   
    int[] arr1 = {9, 4, 3, 1, -2, 6, 5};
    int[] arr2 = {7, 10, 8};
    int[] arr3 = {1, 12};
    System.out.println(isDisjoint(arr1, arr2));
    System.out.println(isDisjoint(arr1, arr3));
    
  }
}