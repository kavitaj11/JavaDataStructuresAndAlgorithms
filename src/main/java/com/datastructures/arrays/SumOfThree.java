package com.datastructures.arrays;

import java.util.Arrays;

//Given an array of integers and a value,
// determine if there are any three integers in the array whose sum equals the given value.
/*
Hints:
Sort data
Iterate from both ends
 */


class SumOfThree {
  //Solution 1: Naive
  //The simple and naive solution is to have three nested loops
  // iterate over all unordered triples to see if their sum is equal to the given integer or not.
  //The runtime complexity of this solution is cubic, O(n^3)
  //The memory complexity of this solution is constant, O(1).
  public static boolean findSumOfThree(int arr[], int requiredSum) {
    for (int i = 0; i < arr.length-2; i++) {
      for (int j = i+1; j < arr.length-1; j++) {
        for (int k = j+1; k < arr.length; k++) {
          //i,j and k are indices to cater the scenario in which same array element is used to calculate the sum.
          if ((i != j) && (j != k) && (k != i)) {
            int sum = arr[i] + arr[j] + arr[k];
            if (sum == requiredSum) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }


  //Solution 2: Using Binary Search
  /*
  We first sort the input array in O(nlogn) time.

  Then we iterate over each pair (a, b) in the array in a nested loop
   and calculate the remaining sum (sum - (a + b)).
   We try to find the remaining sum in the array using binary search.
   If we find it, we have found the solution, with the three numbers being (a, b) and (sum - (a+b)).
   The runtime complexity of this solution is O(n^2 log n)
   The memory complexity of this solution is constant, O(1).
   */
  public static boolean findSumOfThreeSol2(int arr[], int requiredSum) {
      Arrays.sort(arr);
      for (int i = 0; i < arr.length-2; i++) {
        for (int j = i+1; j < arr.length-1; j++) {
          // Looking for requiredSum = arr[i]+arr[j]+arr[k]
          int remainingSum = requiredSum - arr[i] - arr[j];
          int k = Arrays.binarySearch(arr, remainingSum);
          if (k > 0 && k != i && k != j) {
            return true;
          }
        }
      }
      return false;
    }

  //Solution 2: Using “Sum of Two Values” method
  /*
   In this solution, we first sort the array.
   Then we fix one element e,
   and try to find a pair (a, b) in the remaining array such that required_sum - e = a + b.

   We start with the first element e in the array (at index i = 0)
   and try to find such a pair (a, b) in the remaining array (i.e., A[i + 1] to A[n - 1])
   that satisfies the condition: a+b = required_sum - e.
   We can find such a pair in linear time.
   If we find such a pair, we have found the solution: a, b and e
   and thus, we can stop the iteration.
   Otherwise, we repeat the above steps for all elements e at index i = 1 to n - 3,
   until we find a pair that meets the condition.
   The runtime complexity of this solution is quadratic, O(n^2)
   The memory complexity of this solution is constant, O(1).
   */

    static boolean findSumOfTwo(int[] A, int val, int startIndex) {

      for (int i = startIndex, j = A.length - 1; i < j;) {
        int sum = A[i] + A[j];
        if (sum == val) {
          return true;
        }

        if (sum < val) {
          ++i;
        } else {
          --j;
        }
      }

      return false;
    }

    public static boolean findSumOfThreeOptimized(int arr[], int requiredSum) {
      Arrays.sort(arr);
      for (int i = 0; i < arr.length-2; ++i) {
        int remainingSum = requiredSum - arr[i];
        if (findSumOfTwo(arr, remainingSum, i + 1)) {
          return true;
        }
      }
      return false;
    }

  public static void main(String []args){
    int[] arr = {3, 7, 1, 2, 8, 4, 5};
    System.out.println("Original Array: " + Arrays.toString(arr));
    System.out.println("Sum 20 exists " + findSumOfThreeOptimized(arr, 20));
    System.out.println("Sum 10 exists " + findSumOfThreeSol2(arr, 10));
    System.out.println("Sum 21 exists " + findSumOfThree(arr, 21));
  }
}  