package com.string.examples;

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
// The runtime complexity of recursive solution is quadratic, O(3^{m+n}), where m and n are lengths of the input strings.

//Hints #
//A more efficient method is to use a two-dimensional array.
// It would never repeat the same distance calculation.

//The runtime complexity of this solution is quadratic, O(m×n),
//The Memory complexity of this solution is linear, O(n),
// where m and n are lengths of the input strings.

class LevenshteinDistance {
 static int minimum(int a, int b, int c) {
  return Math.min(Math.min(a, b), c);
 }

 public static int levenshteinDistance(String str1, String str2) {

  //degenerate cases
  if (str1 == str2)
   return 0;

  if (str1.length() == 0)
   return str2.length();

  if (str2.length() == 0)
   return str1.length();

  // for all i and j, d[i,j] will hold the Levenshtein distance between
  // the first i characters of str1 and the first j characters of str2;
  // note that d has (m+1)*(n+1) values
  int[][] d = new int[str1.length() + 1][str2.length() + 1];

  // source prefixes can be transformed into empty string by
  // dropping all characters
  for (int i = 0; i <= str1.length(); i++) {
   d[i][0] = i;
  }

  // target prefixes can be reached from empty source prefix
  // by inserting every character
  for (int j = 1; j <= str2.length(); j++) {
   d[0][j] = j;
  }

  int cost;
  for (int i = 1; i <= str1.length(); i++) {
   for (int j = 1; j <= str2.length(); j++) {

    if (str1.charAt(i - 1) == str2.charAt(j - 1))
     cost = 0; // no operation required
    else
     cost = 1;

    d[i][j] = minimum(
     d[i - 1][j] + 1, // a deletion 
     d[i][j - 1] + 1, // an insertion
     d[i - 1][j - 1] + cost); // a substitution 						
   }
  }

  return d[str1.length()][str2.length()];
 }

 public static void main(String[] args) {
  String str1 = "kitten";
  String str2 = "sitting";
  int ld = levenshteinDistance(str1, str2);
  System.out.println("LD(" + str1 + ", " + str2 + ") = " + ld);
 }
}