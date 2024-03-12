package com.datastructures.arrays;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
Given two sorted arrays, the task is to merge them in a sorted manner.
Examples:

Input: arr1[] = { 1, 3, 4, 5}, arr2[] = {2, 4, 6, 8}
Output: arr3[] = {1, 2, 3, 4, 4, 5, 6, 8}
Input: arr1[] = { 5, 8, 9}, arr2[] = {4, 7, 8}
Output: arr3[] = {4, 5, 7, 8, 8, 9}
 */
class checkMergeArray {
/*
Method 1 (O(n1 * n2) Time and O(1) Extra Space)
Create an array arr3[] of size n1 + n2.
Copy all n1 elements of arr1[] to arr3[]
Traverse arr2[] and one by one insert elements (like insertion sort) of arr3[] to arr1[].
This step take O(n1 * n2) time.
*/

/*
Method 2- (O(n1 + n2) Time and O(n1 + n2) Extra Space
The idea is to use Merge function of Merge sort.
Create an array arr3[] of size n1 + n2.
Simultaneously traverse arr1[] and arr2[].
Pick smaller of current elements in arr1[] and arr2[],
copy this smaller element to next position in arr3[] and move ahead in arr3[]
and the array whose element is picked.
If there are remaining elements in arr1[] or arr2[], copy them also in arr3[].
*/
  // Merge arr1 and arr2 into resultantArray
  public static int[] mergeArrays(int[] arr1, int[] arr2) {
    int s1 = arr1.length;
    int s2 = arr2.length;
    int[] resultantArray = new int[s1+s2];
    int i = 0, j = 0, k = 0;

    // Traverse both array 
    while (i < s1 && j < s2) { 
      // Check if current element of first array is smaller than current element of second array.
      // If yes, store first array element and increment first array index.
      // Otherwise do same with second array
      if (arr1[i] < arr2[j]) 
        resultantArray[k++] = arr1[i++]; 
      else
        resultantArray[k++] = arr2[j++]; 
    } 

    // Store remaining elements of first array 
    while (i < s1) 
      resultantArray[k++] = arr1[i++]; 

    // Store remaining elements of second array 
    while (j < s2) 
      resultantArray[k++] = arr2[j++]; 

    return resultantArray;
  }


  public static void main(String args[]) {

    int[] arr1 = {1,12,14,17,23}; // creating a sorted array called arr1
    int[] arr2 = {11,19,27};  // creating a sorted array called arr2

    int[] resultantArray = mergeArrays(arr1, arr2); // calling mergeArrays

    System.out.print("Arrays after merging: ");
    for(int i = 0; i < arr1.length + arr2.length; i++) {
      System.out.print(resultantArray[i] + " ");
    }
  }
}


