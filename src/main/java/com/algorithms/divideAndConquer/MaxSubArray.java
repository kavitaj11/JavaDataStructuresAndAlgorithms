package com.algorithms.divideAndConquer;

import java.util.Arrays;
//In a given unsorted array, the maximum sum of a continuous subarray is the one who’s elements,
// when added together, give the largest possible sum.

//Using divide and conquer approach, we can find the maximum subarray sum in O(nlogn) time.
//You have to find the maximum sum
// starting from the midpoint
// and ending at some point to the left of mid,
// then find the maximum sum starting from mid + 1
// and ending with some point to the right of mid + 1.
// Finally, add up the two and return.

class MaxSubArray {
 // helper function to find the max of 3 numbers
 public static int max(int n1, int n2, int n3) {
 
  return Math.max(Math.max(n1, n2), n3);
 }

 // Finding the largest possible sum in given array 
 public static int crossingSum(int arr[], int left, int mid, int right) {
  // mid + elements to the left of mid
  
  int sum = 0;
  int leftSum = Integer.MIN_VALUE;
  for (int i = mid; i >= left; i--) {
   
   sum = sum + arr[i];
   if (sum > leftSum)
    leftSum = sum;
  }

  // mid + elements to the right of the mid
  sum = 0; int rightSum = Integer.MIN_VALUE;
  for (int i = mid + 1; i <= right; i++) {
   
   sum = sum + arr[i];
   if (sum > rightSum)
    rightSum = sum;
  }
  // sum of elements to the left and right, including mid 
  return leftSum + rightSum;
 }

 // Gives maximum subarray sum in the given array 
 public static int maxSumArr(int arr[], int left, int right) {
  
  if (left == right) // if there is only 1 element in the array
   return arr[left];

  int mid = (left + right) / 2; // find the mid point

  /* Return maximum of following three possible cases */
  return max(maxSumArr(arr, left, mid), // Max Subarray Sum present in the left half
   maxSumArr(arr, mid + 1, right), // Max Subarray Sum present in the right half 
   crossingSum(arr, left, mid, right)); // Max Subarray Sum including the midpoint */
 }



 public static void main(String args[]) {
  int[][] inputs = {{0, 1, 2, 5}, {-1, -2, -3, -4, -5}, {-2, 10, 7, -5, 15, 6}, {-1, -3, 4, -1, -2, 1, 5, -3}};
  for (int i = 0; i < inputs.length; i++) {
   if (i == 0)
    System.out.println("Case I - All Positive Inputs\n" + Arrays.toString(inputs[i]) + "\t---> \tMax Subarr Sum: \"" + maxSumArr(inputs[i], 0, inputs[i].length - 1) + "\"\n");

   else if (i == 1)
    System.out.println("Case II - All Negative Inputs\n" + Arrays.toString(inputs[i]) + "\t---> \tMax Subarr Sum: \"" + maxSumArr(inputs[i], 0, inputs[i].length - 1) + "\"\n");

   else if (i == 2) {
    System.out.println("Case III - Both Positive & Negative Inputs");
    System.out.println(Arrays.toString(inputs[i]) + "\t---> \tMax Subarr Sum: \"" + maxSumArr(inputs[i], 0, inputs[i].length - 1) + "\"");
   } else if (i == 3)
    System.out.println(Arrays.toString(inputs[i]) + "\t---> \tMax Subarr Sum: \"" + maxSumArr(inputs[i], 0, inputs[i].length - 1) + "\"\n");
  }
 }
}