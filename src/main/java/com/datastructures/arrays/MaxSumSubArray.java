package com.datastructures.arrays;

import com.recursion.backtracking.SubSetSum;

public class MaxSumSubArray {

    //Given an array, the algorithm to find the maximum subarray sum is called Kadane’s Algorithm.
    //Let’s take an array dp[] where each dp[i] denotes maximum subarray sum ending at index i (including i).
    //And since we want the maximum subarray sum,
    // we add the current element to the maximum of 0 and previous sum
    // (zero here denotes that we’re starting a new from the current element).
    //This problem falls under Dynamic Programming Paradigm.
    //We can say that
     //dp[i]=max(dp(i-1),0)+arr[i]
    //Base conditions:
    // dp[0] = arr[0]
    //Time Complexity: O(N)
    //Space Complexity: O(1)
    public int getMaxSubarraySum(int[] array){
        int currentMax = 0;
        int totalMax =  Integer.MIN_VALUE;

        for(int i = 0; i < array.length; i++){
            currentMax = Math.max(currentMax, 0) + array[i];
            totalMax = Math.max(totalMax, currentMax);
        }
        return totalMax;
    }


    //We could optimize the space complexity by taking dp[i-1]
    // which is the previous sum into a variable,
    // eliminating the need for dp[] array.
    //Time Complexity: O(N)
    //Space Complexity: O(1)


    static int getMaxSubarraySumOptimized(int[] A) {
    if (A.length < 1) {
      return 0;
    }

    int currMax = A[0];
    int globalMax = A[0];
    for (int i = 1; i < A.length; ++i) {

      if (currMax < 0) {
        currMax = A[i];
      } else {
        currMax += A[i];
      }

      if (globalMax < currMax) {
        globalMax = currMax;
      }
    }

    return globalMax;
  }

    public static void main(String[] args) {
        int[] input = { 2, 3, 4, -5, 7, -8,3, 2, 1, 6 };
        MaxSumSubArray maxSubArray = new MaxSumSubArray();
        int maxsum= maxSubArray.getMaxSubarraySumOptimized(input);
        System.out.println(maxsum);
    }
}
