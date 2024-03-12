package com.datastructures.set;

import java.util.HashSet;
import java.util.Set;

class FindTwoArrayElementsToMatchSum{
/*
Code Explanation #
We solve this problem by using a HashSet called set. For every element in the arr array, the difference of key & each element (n-arr[i]) is computed. If it’s not already present in the set, this value is added, and the index moves to the next element of the array.

As soon as the difference of value stored in set becomes equal to any value in the array arr, the two numbers adding up to n are found!

Therefore, an array of size 2 called result is created to store the pair that sums up to ‘n’. If Hash-Set contains an array element, the result[] is updated, or else it is returned containing the default value.

Time complexity #
This code works in O(n)O(n) as the whole array is iterated over once.

*/
  public static int[] findSum(int[] arr, int n) 
  {
    int[] result = new int[2];
    Set<Integer> set = new HashSet<Integer>();
    for(int i: arr) 
    {
      if(set.contains(n - i)){
          result[0] = i;
          result[1] = n - i;
          break;
      }
      set.add(i);
    }
    return result;   // return the elements in the array whose sum is equal to the value passed as parameter 
  }

  public static void main(String args[]) 
  {
    int n = 0;
    int[] arr1 = {};
    if(arr1.length > 0){
      int[] arr2 = findSum(arr1, n);
      int num1 = arr2[0];
      int num2 = arr2[1];

      if((num1 + num2) != n)
        System.out.println("Not Found");
      else {
        System.out.println("Number 1 = " + num1);
        System.out.println("Number 2 = " + num2);
        System.out.println("Sum = " +  (n) );

      }
    } else {
      System.out.println("Input Array is Empty!");
    }
  }
}
