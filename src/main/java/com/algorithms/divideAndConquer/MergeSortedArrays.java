package com.algorithms.divideAndConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Problem statement #
Given a 2D array of k rows and 33 sorted columns and an empty 1D output array of size k*n,
copy all the elements from the k sorted arrays to the k*n output array using a divide and conquer approach.
Input #
The inputs are:
int[][] arr (2D array of integers)
int k (number of sorted arrays in arr)
int[] result (empty k*nk∗n sized 1D array)

Output #
The output is void (you are required to copy all the sorted elements in the given result array).

Sample input #
{{16, 25, 36}, {2, 9, 15}, {22, 55, 67}, {79, 38, 99}}

Sample output #
{2, 9, 15, 16, 22, 25, 36, 38, 55, 67, 79, 99}
 */
class MergeSortedArrays {
 public static int n = 4; // Each array has 3 elements, i.e. our 2D array has 3 columns

 //Solution#1
 /*
 A naive solution to this problem would be:
Append all the arrays one after another in an ArrayList say Output.
Next, simply sort the Output using the built-in Java function, i.e., Collections.sort();.
Time complexity #
Appending kk sorted arrays into an ArrayList array would take O(n*k) time.
 Since Collections.sort() runs in O(size*log(size)) and the size of Output is (n*k),
  sorting would take O(n*k*log(n * k))
Therefore, the total time complexity of this naive solution would be O(n*k + n*k* log(n*k)) 0r O(n*k * log(n*k))
  */
 public static ArrayList< Integer > mergeSortedArrays(int[][] arr, ArrayList < Integer > Output) {
  //traversing the 2-D array and appending all arrays into an ArrayList
  for (int i = 0; i < arr.length; i++) {
   for (int j = 0; j < arr[i].length; j++) {
    //adding into the ArrayList
    Output.add(arr[i][j]);
   }
  }
  //sorting the ArryList using the inbuilt sort function
  Collections.sort(Output);
  return Output;
 }


 //Solution#2: Divide and conquer (efficient) #
 /*
 The driver program calls the function mergeSortedArrays(arr, k, result);
 to merge the sorted 1D arrays stored in the input 2D array.

 Merging the k sorted array creates a recursion tree of log(k) height
 because at each step the number of remaining arrays becomes half.
 The algorithm takes a time of O(n*k)  at each recursive call.
 So, the time complexity of this approach comes out to be O( n*k * log(k))
  */
 public static void sortAndMerge(int left, int right, int[] result) {

  int leftIndex = left * n;
  int rightIndex = ((left + right) / 2 + 1) * n;

  int leftSize = ((left + right) / 2 - left + 1) * n;
  int rightSize = (right - (left + right) / 2) * n;

  int[] leftArr = new int[leftSize];
  int[] rightArr = new int[rightSize];

  for (int i = 0; i < leftSize; i++) // Storing data in left array
   leftArr[i] = result[leftIndex + i];

  for (int i = 0; i < rightSize; i++) // Storing data in right array
   rightArr[i] = result[rightIndex + i];

  // Temporarily store the index of the left and right array
  int leftCurr = 0;
  int rightCurr = 0;
  int currIndex = leftIndex; // Storing the current index of the output array

  while (leftCurr + rightCurr < leftSize + rightSize) // implementing two pointers merging technique
  {
   if (rightCurr == rightSize || (leftCurr != leftSize && leftArr[leftCurr] < rightArr[rightCurr])) {

    result[currIndex] = leftArr[leftCurr];
    leftCurr++;
    currIndex++;
   }
   else {

    result[currIndex] = rightArr[rightCurr];
    rightCurr++;
    currIndex++;
   }
  }
 }
 // Implementing the merge-sort alogorithm
 public static void mergeSortedRecursive(int[][] arr, int left, int right, int[] result) {

  if (left == right) // Base condition, recursion breaks when we reach one element
  {
   for (int i = 0; i < n; i++)
    result[left * n + i] = arr[left][i];
  }
  else {

   // Sorting the left half of the array
   mergeSortedRecursive(arr, left, (left + right) / 2, result);

   // Sorting the right half of the array
   mergeSortedRecursive(arr, (left + right) / 2 + 1, right, result);

   // Merging both the right and left halves of the array
   sortAndMerge(left, right, result);
  }
 }
 public static void mergeSortedArraysDivideAndConquer(int[][] arr, int k, int[] result) {

  mergeSortedRecursive(arr, 0, k - 1, result);
 }


 public static void main(String args[]) {
  // 2D input array 
  int[][] arr = {{16, 25, 36}, {2, 9, 15}, {22, 55, 67}, {79, 38, 99}}; 
  ArrayList < Integer > Output = new ArrayList < Integer > ();

  System.out.print(mergeSortedArrays(arr, Output));


  int array[][] = {{ 16, 25, 36, 79 }, { 2, 9, 15, 38 }, { 22, 55, 67, 98 }};
  //Initialize k to be equal to the number of subarrays (1D arrays),
  // and the result array to be of appropriate size, i.e., for k arrays of n elements each.
  int k = array.length, n = array[k - 1].length;
  int[] result = new int[n * k];
  mergeSortedArraysDivideAndConquer(array, k, result);
  System.out.print(Arrays.toString(result));

 }
}