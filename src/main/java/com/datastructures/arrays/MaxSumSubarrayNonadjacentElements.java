package com.datastructures.arrays;
/*
Find an efficient algorithm to find the maximum sum of a subsequence in an array 
so that no consecutive elements are part of this subsequence.
Runtime complexity #
The runtime complexity of this solution is linear, O(n).

Memory complexity #
The memory complexity of this solution is linear, O(n).

A naive solution to this problem is to calculate the sum of all possible subsequences with no adjacent elements 
and keep track of the subsequence with the maximum sum. The time complexity of this approach is O(n^2)
This can be improved by using dynamic programming. 
Iterate over the entire input array, and in every iteration pick the maximum of these two values:
1. Max Sum of the last iteration.
2. Max Sum of second last iteration + current iteration index.
*/

class MaxSumSubarrayNonadjacentElements{
  static int findMaxSumNonadjacent(int[] a) {
    if (a == null || a.length == 0) {
      return 0;
    }

    if (a.length == 1) {
      return a[0];
    }

    int[] result = new int[a.length];
    result[0] = a[0];
    
    for (int i = 1; i < a.length; i++)  {

      result[i] = Math.max(a[i],result[i - 1]);
      if ((i - 2) >= 0) {
        result[i] = Math.max(result[i], a[i] + result[i - 2]);
      }
    }
    
    return result[a.length - 1];
  }
  public static void main(String[] args) {
    int[] v = new int[]{1, -1, 6, -4, 2, 2};
    System.out.println("Max sum of nonadjacent subsequence: " + findMaxSumNonadjacent(v));
  }
}
