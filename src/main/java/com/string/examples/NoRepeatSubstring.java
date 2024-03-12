package com.string.examples;

import java.util.*;
//Given a string, find the length of the longest substring, which has no repeating characters.
/*
Solution #
This problem follows the Sliding Window pattern,
and we can use a similar dynamic sliding window strategy
as discussed in Longest Substring with K Distinct Characters.
We can use a HashMap to remember the last index of each character we have processed.
 Whenever we get a repeating character,
we will shrink our sliding window to ensure that we always have distinct characters in the sliding window.
 */
class NoRepeatSubstring {
  /*
  Time Complexity #
  The above algorithm’s time complexity will be O(N), where ‘N’ is the number of characters in the input string.

Space Complexity #
The algorithm’s space complexity will be O(K), where K is the number of distinct characters in the input string.
 This also means K<=NK<=N, because in the worst case, the whole string might not have any repeating character,
 so the entire string will be added to the HashMap.
 Having said that, since we can expect a fixed set of characters in the input string (e.g., 26 for English letters),
  we can say that the algorithm runs in fixed space O(1);
 in this case, we can use a fixed-size array instead of the HashMap.
   */
  public static int findLength(String str) {
    int windowStart = 0, maxLength = 0;
    Map<Character, Integer> charIndexMap = new HashMap<>();
    // try to extend the range [windowStart, windowEnd]
    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
      char rightChar = str.charAt(windowEnd);
      // if the map already contains the 'rightChar', shrink the window from the beginning so that
      // we have only one occurrence of 'rightChar'
      if (charIndexMap.containsKey(rightChar)) {
        // this is tricky; in the current window, we will not have any 'rightChar' after its previous index
        // and if 'windowStart' is already ahead of the last index of 'rightChar', we'll keep 'windowStart'
        windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
      }
      charIndexMap.put(rightChar, windowEnd); // insert the 'rightChar' into the map
      maxLength = Math.max(maxLength, windowEnd - windowStart + 1); // remember the maximum length so far
    }

    return maxLength;
  }

  public static void main(String[] args) {
    System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("aabccbb"));
    System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("abbbb"));
    System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("abccde"));
  }
}