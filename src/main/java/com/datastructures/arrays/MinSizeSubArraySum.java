package com.datastructures.arrays;

/*
Given an array of positive numbers and a positive number ‘S,’
 find the length of the smallest contiguous subarray whose sum is greater than or equal to ‘S’.
Return 0 if no such subarray exists.
Solution: This problem follows the Sliding Window pattern,
and we can use a similar strategy as discussed in Maximum Sum Subarray of Size K.
There is one difference though: in this problem, the sliding window size is not fixed.

Time Complexity #
The time complexity of the above algorithm will be O(N).
The outer for loop runs for all elements,
and the inner while loop processes each element only once;
therefore, the time complexity of the algorithm will be O(N+N),
which is asymptotically equivalent to O(N).

Space Complexity #
The algorithm runs in constant space O(1).
 */

class MinSizeSubArraySum {
  public static int findMinSubArray(int S, int[] arr) {
    int windowSum = 0, minLength = Integer.MAX_VALUE;
    int windowStart = 0;
    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
      windowSum += arr[windowEnd]; // add the next element
      // shrink the window as small as possible until the 'windowSum' is smaller than 'S'
      while (windowSum >= S) {
        minLength = Math.min(minLength, windowEnd - windowStart + 1);
        windowSum -= arr[windowStart]; // subtract the element going out
        windowStart++; // slide the window ahead
      }
    }

    return minLength == Integer.MAX_VALUE ? 0 : minLength;
  }

  public static void main(String[] args) {
    int result = MinSizeSubArraySum.findMinSubArray(7, new int[]{2, 1, 5, 2, 3, 2});
    System.out.println("Smallest subarray length: " + result);
    result = MinSizeSubArraySum.findMinSubArray(7, new int[]{2, 1, 5, 2, 8});
    System.out.println("Smallest subarray length: " + result);
    result = MinSizeSubArraySum.findMinSubArray(8, new int[]{3, 4, 1, 1, 6});
    System.out.println("Smallest subarray length: " + result);
  }
}