package com.datastructures.HashTable;

import java.util.HashSet;

//Can you find whether a given array is a subset of another by using a built-in Hash Table?
//We simply iterate over arr2 to see whether their elements can be found in arr1.
//At the back end, the values are checked against their hashed indices in arr1.
//Time Complexity #
//For a lookup array with m elements and a subset array with n elements,
// the time complexity is O(m+n).
class CheckSubset {
  static boolean isSubset(int[] arr1, int[] arr2) {
    HashSet<Integer> hset= new HashSet<>();
    // hset stores all the values of arr1 
    for (int value : arr1) {
      hset.add(value);
    } 

    // loop to check if all elements of arr2 also 
    // lies in arr1 
    for (int value : arr2) {
      if (!hset.contains(value))
        return false;
    } 
    return true; 
  }
  
  public static void main(String args[]) {
    
    int[] arr1 = {9, 4, 7, 1, -2, 6, 5};
    int[] arr2 = {7, 1, -2};
    int[] arr3 = {10, 12};

    System.out.println(isSubset(arr1, arr2));
    System.out.println(isSubset(arr1, arr3));
  }
}