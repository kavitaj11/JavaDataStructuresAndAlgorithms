package com.datastructures.arrays;

/*
Given an array of positive numbers and a positive number ‘k,’
find the maximum sum of any contiguous subarray of size ‘k’.
Solution: A better approach #
If you observe closely, you will realize that to calculate the sum of a contiguous subarray,
we can utilize the sum of the previous subarray.
For this, consider each subarray as a Sliding Window of size ‘k.’
To calculate the sum of the next subarray, we need to slide the window ahead by one element.
So to slide the window forward and calculate the sum of the new position of the sliding window,
we need to do two things:
Subtract the element going out of the sliding window, i.e., subtract the first element of the window.
Add the new element getting included in the sliding window, i.e., the element coming right after the end of the window.
This approach will save us from re-calculating the sum of the overlapping part of the sliding window.

Time Complexity #
The time complexity of the above algorithm will be O(N)

Space Complexity #
The algorithm runs in constant space O(1)
*/

class MaxSumSubArrayOfSizeK {
  public static int findMaxSumSubArray(int k, int[] arr) {
    int windowSum = 0, maxSum = 0;
    int windowStart = 0;
    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
      windowSum += arr[windowEnd]; // add the next element
      // slide the window, we don't need to slide if we've not hit the required window size of 'k'
      if (windowEnd >= k - 1) {
        maxSum = Math.max(maxSum, windowSum);
        windowSum -= arr[windowStart]; // subtract the element going out
        windowStart++; // slide the window ahead
      }
    }
    return maxSum;
  }
  public static void main(String[] args) {
    System.out.println("Maximum sum of a subarray of size K: "
        + MaxSumSubArrayOfSizeK.findMaxSumSubArray(3, new int[] { 2, 1, 5, 1, 3, 2 }));
    System.out.println("Maximum sum of a subarray of size K: "
        + MaxSumSubArrayOfSizeK.findMaxSumSubArray(2, new int[] { 2, 3, 4, 1, 5 }));
  }
}