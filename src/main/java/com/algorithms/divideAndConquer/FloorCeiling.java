package com.algorithms.divideAndConquer;

import java.util.Arrays;

//Problem statement #
//You are given a collection of sorted integers say int [] inputs.
// For any given number xx, return a floor and ceil value of x from the given array inputs.
// If either ceil or floor does not exist in the given array, -1 should be returned in its place.

class FloorCeiling {

 /*
 We can make use of the fact that the array is sorted.
 So, by a little modification of the binary search algorithm, we can successfully crack this problem.
 The core idea remains the same: we divide the array at the midpoint
 and search for the key either in left or right subarray based on the comparison with a given number.

 floor function
Continue the iteration until the search space is completely consumed:
Initialize the floor to -1.
Find the mid of the array.
If x is equal to the middle element, it is the floor. Return the floor.
If x is less than the mid element, update the right limit to mid - 1 and keep looking for the floor value in the left subarray.
If x is more than the mid element, update the floor to arr[mid] and the left limit to mid + 1, and keep searching for the target in the right subarray.

ceil function
Continue the iteration until the search space is completely consumed:
Initialize the ceil to -1.
Find the mid of the array.
If x is equal to the middle element, it is the ceil. Return the ceil.
If x is less than the mid element, update the right limit to mid - 1 and update ceil to arr[mid]. Now search for the ceil in the left subarray.
If x is more than the mid element, update the left limit to mid + 1 and keep searching for the target in the right subarray.
  Time complexity #
The running complexity for this is the same as the binary search,
 i.e., O(log(n)), where n is the size of the array.
  */
 public static int findFloor(int[] arr, int x) // Function to find floor of x in a sorted array arr[]
 {
  int left = 0, right = arr.length - 1;
  int floor = -1; // initialize floor to -1, if -1 is returned as it is, then floor does not exist!
  while (left <= right) {
   
   int mid = (left + right) / 2; // find the middle value
   if (arr[mid] == x) // if x is equal to mid element, it is the floor value
    return arr[mid];
   else if (x < arr[mid]) // if x is less than the mid element, floor exists in the left subArr[left...mid-1]
    right = mid - 1;
   else // if x is more than the mid element, floor exists in the right subArr[mid...right].  
   {
    floor = arr[mid];
    left = mid + 1;
   }
  }
  return floor;
 }

 public static int findCeiling(int[] arr, int x) // Function to find ceiling of input x in a sorted array
 {
  int left = 0, right = arr.length - 1;
  int ceil = -1; // initialize ceiling value to -1, if -1 is returned as it is, then ceil doesnot exit!

  while (left <= right) {
   
   int mid = (left + right) / 2; // find the middle value 
   if (arr[mid] == x) // if x is equal to middle element, it is the ceiling 
    return arr[mid];
   else if (x < arr[mid]) // if x is less than the mid element, ceil exists in the left subArray[left...mid-1]
   {
    ceil = arr[mid];
    right = mid - 1;
   } else // if x is more than the mid element, ceil exists in the right subArr[mid...right]
    
    left = mid + 1;
  }
  return ceil;
 }
 // wraper function to call both Floor and Ceiling Functions and then store the output in the `output` array
 public static void findFloorCeiling(int[] input, int x, int[] output) {
  
  output[0] = findFloor(input, x);
  output[1] = findCeiling(input, x);
 }



 public static void main(String[] args) {
  int[] inputs = {1, 2, 3, 5, 7};
  int[] output = new int[2];
  FloorCeiling.findFloorCeiling(inputs, 4, output);
  System.out.println("Floor and Ceil of 4 is: " + Arrays.toString(output));

 }
}

