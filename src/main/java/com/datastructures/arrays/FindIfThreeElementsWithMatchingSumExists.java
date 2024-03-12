package com.datastructures.arrays;/*Runtime complexity #
The runtime complexity of this solution is quadratic, O(n^2)

Memory complexity #
The memory complexity of this solution is constant, O(1)O(1).
By sorting the array, we have to change the input array itself.
If we are not allowed to modify the input array,
then we can use a hash table to achieve the same time complexity
(see the first solution of Sum of Two Values problem).
However, this will require O(n) of extra memory.

Another alternative approach is to create a copy of the input array
and sort that rather than sorting the original.
*/


import java.util.Arrays;

class FindIfThreeElementsWithMatchingSumExists{
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

  public static boolean findSumOfThree(int arr[], int requiredSum) {
    Arrays.sort(arr);
    for (int i = 0; i < arr.length-2; ++i) {
      int remainingSum = requiredSum - arr[i];
      if (findSumOfTwo(arr, remainingSum, i + 1)) {
        return true;
      }
    }  
    return false;
  }
  
  
  /*
  Brute Force Method
  Runtime complexity #
  The runtime complexity of this solution is cubic, O(n^3)

  Memory complexity #
  The memory complexity of this solution is constant, O(1)O(1).

  The simple and naive solution is to have three nested loops iterate over all unordered triples to see if their sum is equal to the given integer or not.
*/
  public static boolean findSumOfThreeUsingBruteForce(int arr[], int requiredSum) {
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
  
  /*
  Using BinarySearch
  Runtime complexity #
  The runtime complexity of this solution is O(n^2 log n)

  Memory complexity #
  The memory complexity of this solution is constant, O(1)O(1).

  This solution is also simple. We first sort the input array in O(n log n)O(nlogn) time.*/

  public static boolean findSumOfThreeUsingBinarySearch(int arr[], int requiredSum) {
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

  
  public static void main(String []args){
    int[] arr = {3, 7, 1, 2, 8, 4, 5};
    System.out.println("Original Array: " + Arrays.toString(arr));                                         
    System.out.println("Sum 20 exists " + findSumOfThree(arr, 20)); 
    System.out.println("Sum 10 exists " + findSumOfThree(arr, 10));
    System.out.println("Sum 21 exists " + findSumOfThree(arr, 21));
  }
}  
