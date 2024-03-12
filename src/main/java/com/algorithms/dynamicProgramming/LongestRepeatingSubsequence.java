package com.algorithms.dynamicProgramming;

/*
Given a sequence, find the length of its longest repeating subsequence (LRS).
A repeating subsequence will be the one that appears at least twice in the original sequence
and is not overlapping
(i.e. none of the corresponding characters in the repeating subsequences have the same index).
Example 1:
Input: “t o m o r r o w”
Output: 2
Explanation: The longest repeating subsequence is “or” {tomorrow}.

Example 2:
Input: “a a b d b c e c”
Output: 3
Explanation: The longest repeating subsequence is “a b c” {a a b d b c e c}.
 */
class LongestRepeatingSubsequence {

  //The time and space complexity of the above algorithm is O(n^2)
  //​​where ‘n’ is the length of the input sequence.
  public int findLRSLength(String str) {
    int[][] dp = new int[str.length()+1][str.length()+1];
    int maxLength = 0;
    // dp[i1][i2] will be storing the LRS up to str[0..i1-1][0..i2-1]
    // this also means that subsequences of length zero (first row and column of dp[][]),
    // will always have LRS of size zero.
    for(int i1=1; i1 <= str.length(); i1++) {
      for(int i2=1; i2 <= str.length(); i2++) {
        if(i1 != i2 && str.charAt(i1-1) == str.charAt(i2-1))
          dp[i1][i2] = 1 + dp[i1-1][i2-1];
        else
          dp[i1][i2] = Math.max(dp[i1-1][i2], dp[i1][i2-1]);
        
        maxLength = Math.max(maxLength, dp[i1][i2]);
      }
    }
    return maxLength;
  }

  public static void main(String[] args) {
    LongestRepeatingSubsequence lrs = new LongestRepeatingSubsequence();
    System.out.println(lrs.findLRSLength("tomorrow"));
    System.out.println(lrs.findLRSLength("aabdbcec"));
    System.out.println(lrs.findLRSLength("fmff"));
  }
}