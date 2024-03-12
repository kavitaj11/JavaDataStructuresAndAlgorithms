package com.string.examples;//package com.basics.algorithms.dynamicProgramming;

import java.util.Scanner;

/*
Given two strings, compute the Levenshtein distance between them,
meaning the minimum number of edits required to convert one string to the other.

The Levenshtein distance, LD, is a measure of the difference between two strings, s1 and s2
It is the minimum number of deletions, insertions, or substitutions required to transform s1 into s2

 It is also known as the edit distance.

Let’s see a few examples below:

If s1= ants and s2= ant then LD(s1, s2)=0
as no transformations are required because the strings are already identical.

If s1=tests and s2=texts then LD(s1,s2) =1
as one substitution of x for s is required to transform s1​​ into s2

If s1=ants and s2=aunts then LD(s1, s2) =1
as one insertion of uu after aa is required to transform s1 into s2

If s1 =mins and s2=maxs then LD(s1,s2) =2
as two substitutions are required to transform s1 into s2
 */

//The greater the Levenshtein distance, the more different the strings are.
//The recursive implementation is straightforward,
// but very inefficient because it computes the Levenshtein distance of the same substrings many times.
// The runtime complexity of recursive solution is quadratic, O(3^{m+n}),
// where m and n are lengths of the input strings.

//Hints #
//A more efficient method is to use a two-dimensional array.
// It would never repeat the same distance calculation.

//The runtime complexity of this solution is quadratic, O(m×n),
//The Memory complexity of this solution is linear, O(n),
// where m and n are lengths of the input strings.

class EditDistance {
  public static int EditDistance(String s, String t) {
    int m = s.length(), n = t.length();
    // match "" with the other string
    int[][] table = new int[m + 1][n + 1];
    // initialize first row/col by i/j as distance from ""
    for(int j = 1; j <= n; j++) table[0][j] = j;
    for(int i = 1; i <= m; i++) table[i][0] = i;
    // bottom-up from all smaller subproblems row-by-row
    for(int i = 1; i <= m; i++) {
      for(int j = 1; j <= n; j++) {
        int insertion = table[i][j - 1] + 1,
                deletion  = table[i - 1][j] + 1,
                mismatch  = table[i - 1][j - 1] +
                        (s.charAt(i - 1) == t.charAt(j - 1) ? 0 : 1);
        // BZ: the ith char should have index i - 1
        table[i][j] = Math.min(insertion,
                Math.min(deletion, mismatch));
      }
    }
    return table[m][n];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
