package com.algorithms.divideAndConquer;


import java.util.Arrays;

class Shuffle {


 //Solution #1
/*
It is safe to assume that for this problem the given array can be divided into two equal halves
because the number of elements in the array is 2*n
So, the naive (brute-force) approach to this problem is:
Use of two nested loops to transfer the elements in the right half of the array to the left half.
Run the first loop half the size of array times, i-e, size/2 times, to traverse the elements in the second half of the array.
Then, transfer the elements of the right half to the left half using the inner loop
Since two for loops ranging up to nn are used in this approach,
 the worst-case time complexity of this solution is O(n^2)
​​
 */
 public static void shuffleArrNaive(int arr[], int size)  // Swap elements from left to right
 {
  for (int i = 0, q = 1, k = size; i < size; i++, k++, q++)
   for (int j = k; j > i + q; j--)
     swap(arr, j-1, j);
 }

 /*
 Boundary cases
You need to take care of the following extreme cases in your program:
When there are only two elements in the array
When the size of input array is not the multiple of 2*n OR there are an Odd number of elements in the array
  */

 //Solution #2: Divide and conquer (efficient) #
 //Time complexity #
 //This solution gives the worst-case time complexity of O(nlogn).
 public static void shuffleArrayUtil(int arr[], int left, int right) // Method to shuffle 2*n sized array's elements
 {

  if (right - left <= 1) // Base case: return array if only 2 elements are remaining
   return;

  int middle = (left + right) / 2; // compute middle index
  int temp = middle + 1; // first half of second array 
  int mmiddle = (left + middle) / 2; // second half of first array 

  for (int i = mmiddle + 1; i <= middle; i++) // swapping elements for subarray
   swap(arr, i, temp++);

  // Pass splitted first and second half of the array to utility function
  shuffleArrayUtil(arr, left, middle);
  shuffleArrayUtil(arr, middle + 1, right);
 }

 public static Object ShuffleArray(int arr[]) {
  
  int size = arr.length;
  if (size == 2) // corner case 1 check
  {
   System.out.println("After: " + Arrays.toString(arr));
   return true;
  } else if (size % 2 != 0) // corner case 2 check
  {
   
   System.out.println("After: " + Arrays.toString(arr));
   return false;
  } else {
   
   shuffleArrayUtil(arr, 0, size -1 );
   System.out.println("After: " + Arrays.toString(arr));
   return true;
  }
 }


 public static void swap(int[] array, int i, int j)
 {
  int temp = array[i];
  array[i] = array[j];
  array[j] = temp;
 }

 public static void main(String args[]) {
  int[][] arr = {{0, 1}, { 11, 12, 13}, {0, 1, 2, 3, 4, 5}, { 1, 3, 5, 7, 2, 4, 6, 8 }};
  boolean check = true;
  for (int i = 0; i < arr.length; i++) {
   System.out.println("Before: " + Arrays.toString(arr[i]));
   ShuffleArray(arr[i]);
   System.out.println();
  }
 }


}