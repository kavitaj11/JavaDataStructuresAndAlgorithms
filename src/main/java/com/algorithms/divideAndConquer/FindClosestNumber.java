package com.algorithms.divideAndConquer;

import java.util.Arrays;

/*
In any given sorted array, the closest number to a given number
 is the one whose absolute difference is closest to zero to the given number.
 Note that the array may have duplicate and negative values.

Boundary cases:
We need to take care of the following extreme cases in our program:
When the target is smaller than the first element of the array
When the target is larger than the last element of the array
In either of the cases above,
we can be sure that either the first or the last element is the closest to the target
(remember that the array is sorted).
We can be sure about this since moving towards the center would only consume time and clock cycles.

The time complexity of this solution is O(logn)
because we are looking at the middle element at each step
and essentially dropping one half of the array.
We are left with only half of the elements we began with to check.
 */

public class FindClosestNumber {
 
 // The method finds the closer of 2 number to the target -
 // assuming that `second` is greater than `first` and `target` lies in the middle
 public static int closer(int first, int second, int target) {
  
  if (target - first >= second - target)
   return second;
  else
   return first;
 }

 // Returns the closest number to `target` from the input array `arr[]`
 public static int closestNumber(int arr[], int target) {
  
  int n = arr.length;
  // Dealing with the Boundary Cases
  if (target <= arr[0]) // if target is lesser than the starting element of array
   return arr[0]; // return starting element as the array is sorted 

  if (target >= arr[n - 1]) // if target is greater than the last element of array
   return arr[n - 1]; // return last element 

  int i = 0, j = n, mid = 0;

  while (i < j) // Performing Binary search to find closest element
  {
   mid = (i + j) / 2; // get middle index
   if (arr[mid] == target) // if element at middle is equal to target it's the closest, return that.
    return arr[mid];

   if (target < arr[mid]) // if target is less than the element at middle, search in left subarray
   {
    if (mid > 0 && target > arr[mid - 1]) // If target is greater than previous to mid, return closest of two 
     return closer(arr[mid - 1], arr[mid], target);

    j = mid; // Search in rest of the left subarray
   } 
   else // if target is greater than the element at middle, search in the right subarray
   {
    
    if (mid < n - 1 && target < arr[mid + 1])
     return closer(arr[mid], arr[mid + 1], target);

    i = mid + 1; // Search in rest of the right subarray
   }
  }
  return arr[mid]; // One element left after search, that must be the closest, return that
 }

 public static void main(String args[]) {
  int arr[] = { 1, 2, 4, 5, 6, 6, 8, 9 }; 
  int target = 11;
  System.out.println(Arrays.toString(arr) + "\tTarget = " + target + "  --->   Closest Number = " + closestNumber(arr, target));

  int arr1[] = {3,5,7,9,11,17}; 
  target = 10;
  System.out.println(Arrays.toString(arr1) + "\t\tTarget = " + target + "  --->   Closest Number = " + closestNumber(arr1, target));
 }
}