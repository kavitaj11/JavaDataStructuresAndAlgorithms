package com.algorithms.dynamicProgramming;

/*
Given a set of positive numbers,
find if we can partition it into two subsets such that the sum of elements in both the subsets is equal.
This problem follows the 0/1 Knapsack pattern.
A basic brute-force solution could be to try all combinations of partitioning the given numbers into two sets to see if any pair of sets has an equal sum.
*/
class PartitionSet {

  //time and space complexity of O(N*S),
  // where ‘N’ represents total numbers and ‘S’ is the total sum of all the numbers.
  public boolean canPartition(int[] num) {
    int n = num.length;
    // find the total sum
    int sum = 0;
    for (int i = 0; i < n; i++)
      sum += num[i];

    // if 'sum' is a an odd number, we can't have two subsets with same total
    if(sum % 2 != 0)
      return false;

    // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
    sum /= 2;

    boolean[][] dp = new boolean[n][sum + 1];

    // populate the sum=0 column, as we can always have '0' sum without including any element
    for(int i=0; i < n; i++)
      dp[i][0] = true;

    // with only one number, we can form a subset only when the required sum is equal to its value
    for(int s=1; s <= sum ; s++) {
      dp[0][s] = (num[0] == s ? true : false);
    }

    // process all subsets for all sums
    for(int i=1; i < n; i++) {
      for(int s=1; s <= sum; s++) {
        // if we can get the sum 's' without the number at index 'i'
        if(dp[i-1][s]) {
          dp[i][s] = dp[i-1][s];
        } else if (s >= num[i]) { // else if we can find a subset to get the remaining sum
          dp[i][s] = dp[i-1][s-num[i]];
        }
      }
    }

    // the bottom-right corner will have our answer.
    return dp[n-1][sum];
  }

  public static void main(String[] args) {
    PartitionSet ps = new PartitionSet();
    int[] num = {1, 2, 3, 4};
    System.out.println(ps.canPartition(num));
    num = new int[]{1, 1, 3, 4, 7};
    System.out.println(ps.canPartition(num));
    num = new int[]{2, 3, 4, 6};
    System.out.println(ps.canPartition(num));
  }
}