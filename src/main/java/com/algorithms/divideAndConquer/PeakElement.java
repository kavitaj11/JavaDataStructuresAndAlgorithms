package com.algorithms.divideAndConquer;

import java.util.Arrays;

class PeakElement {
 
 // This function is based on the recursive binary search algorithm
 // returning the index of the peak element in the given array

 /*
 Time complexity #
Using this technique, we drop half of the array at every recursive call.
So, after each call, we are only left with half of the work as compared to the last call.
Using this divide and conquer technique, we can solve this problem in O(logn).
  */

 public static int findPeakRecursive(int low, int high, int size, int array[]) {
  // Just as in binary search, we will first find the middle element 
  
  int middle = low + (high - low) / 2;

  // If neighbors of the middle element exist, compare them with the element 
  if ((middle == size - 1 || array[middle + 1] <= array[middle]) &&
   (middle == 0 || array[middle - 1] <= array[middle]))
   return middle;

  // If the left neighbor of the middle element is greater, The peak element must be in the left subarray    
  else if (array[middle - 1] > array[middle] && middle > 0)
   return findPeakRecursive(low, (middle - 1), size, array);

  // If the right neighbor of the middle element is greater, The peak element must be in the right subarray    
  else
   return findPeakRecursive((middle + 1), high, size, array);
 }

 // Wrapper to call the recursive findPeakRecursive() 
 public static int findPeak(int arr[]) {
  
  int n = arr.length;
  return findPeakRecursive(0, n - 1, n, arr);
 }



/* Driver program to test above functions */
public static void main(String args[]) {
 // A 2D array to store integer input arrays
 int[][] inputs = { {7, 11, 22, 13, 4, 0} , {13, 27, 54, 11, 99, 1} ,{0, 1, 2, 3, 4, 5} , {10, 9, 8, 7, 6}};
 
 int peak = 0; // variable to store the peak value for each input array

 for (int i = 0; i < inputs.length; i++) {
  peak = findPeak(inputs[i]);
  if (i == 2)
   System.out.println("Input Array: " + Arrays.toString(inputs[i]) + " ----> Peak = \"" + inputs[i][peak] + "\"\t\t (Ascending Order - Peak = Last Element)\n");
  else if (i == 3)
   System.out.println("Input Array: " + Arrays.toString(inputs[i]) + " ----> Peak = \"" + inputs[i][peak] + "\"\t\t(Descending Order - Peak = First Element)\n");
  else
   System.out.println("Input Array: " + Arrays.toString(inputs[i]) + " ----> Peak = \"" + inputs[i][peak] + "\"\n");

 }
}
}