package com.algorithms.dynamicProgramming;

//Given a sequence, find the length of its Longest Palindromic Subsequence (LPS).
// In a palindromic subsequence, elements read the same backward and forward.
//A subsequence is a sequence that can be derived from another sequence
// by deleting some or no elements without changing the order of the remaining elements.
class LargestPalindromicSubsequence {

  /*
  Since our memoization array dp[st.length()] stores the results for all the subproblems,
   we can conclude that we will not have more than N*N subproblems
   (where ‘N’ is the length of the input sequence).
   This means that our time complexity will be O(N^2)
The above algorithm will be using O(N^2) space for the memoization array.
Other than that we will use O(N) space for the recursion call-stack.
So the total space complexity will be O(N^2 + N), which is asymptotically equivalent to O(N^2)
   */
  public int findLPSLength(String st) {
    Integer[][] dp = new Integer[st.length()][st.length()];
    return findLPSLengthRecursive(dp, st, 0, st.length()-1);
  }

  private int findLPSLengthRecursive(Integer[][] dp, String st, int startIndex, int endIndex) {
    if(startIndex > endIndex)
      return 0;

    // every sequence with one element is a palindrome of length 1
    if(startIndex == endIndex)
      return 1;

    if(dp[startIndex][endIndex] == null) {
      // case 1: elements at the beginning and the end are the same
      if(st.charAt(startIndex) == st.charAt(endIndex)) {
        dp[startIndex][endIndex] = 2 + findLPSLengthRecursive(dp, st, startIndex+1, endIndex-1);
      } else {
        // case 2: skip one element either from the beginning or the end
        int c1 =  findLPSLengthRecursive(dp, st, startIndex+1, endIndex);
        int c2 =  findLPSLengthRecursive(dp, st, startIndex, endIndex-1);
        dp[startIndex][endIndex] = Math.max(c1, c2);
      }
    }

    return dp[startIndex][endIndex];
  }

  public static void main(String[] args) {
    LargestPalindromicSubsequence lps = new LargestPalindromicSubsequence();
    System.out.println(lps.findLPSLength("abdbca"));
    System.out.println(lps.findLPSLength("cddpd"));
    System.out.println(lps.findLPSLength("pqr"));
  }
}