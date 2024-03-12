package com.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
An anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.

Problem statement #
Given an array of strings that contains anagrams, write a function to print those anagrams.
Input #
The input is an array of strings.
Remember that spaces count as characters! So " abc" and “cab”
are technically not anagrams since " abc" has spaces which “cab” does not.
Output #
The output is a string array where all the anagrams are grouped together.

Sample input #
"cat", "dog", "tac", "god", "act",  "tom marvolo riddle ","abc", "def",  "cab", "fed", "clint eastwood ",
"i am lord voldemort", "elvis", "old west action",  "lives"
Sample output #
"[cat, tac, act][abc, cab][def, fed][clint eastwood , old west action][tom marvolo riddle ,
i am lord voldemort][elvis, lives][dog, god]"
Note: The sequence in the groups should be the same in the output as in the input.
For example, “cat” should come before “tac” as it does in the input string.

 */
class Anagram {

 /*

 First, this solution makes a copy of the array
 and sorts each string in the copy using the built-in sort() function of arrays.
  Then, it saves the sorted array into a HashMap called map.
  The key, in this case, is the sorted string,
  and the value is a vector of the indices of where it exists in the array.
  So, the hash table would have entries that look like:
                                          eilsv <-> {7, 9}
                                          abc <-> {1, 3}
 Sorting one word takes O(klogk) time in the average case using quickSort
 (where k is the length of the longest word in the given array),
 so sorting nn words would take O(n×klogk).
 The rest of the operations use hashing, which is all in constant time and, therefore, doesn’t count.
  */
 public static String groupAnagrams(String arr[]) {
  HashMap< String, List< String >> myMap = new HashMap < > ();

  for (int i = 0; i < arr.length; i++) // traverse all the words  
  {
   String word = arr[i];
   char[] letters = word.toCharArray(); // convert the given String Array's each index value to char array
   Arrays.sort(letters); // now sort all the words (based of letter's ASCII values using built in sort function for Arrays)
   String newWord = new String(letters); //  and then re-convert each word to separate String

   if (myMap.containsKey(newWord)) // calculate hashcode of string after sorting  
    myMap.get(newWord).add(word);
   else {
    List < String > totalWords = new ArrayList< >();
    totalWords.add(word); // Add words for the specific hashcode  
    myMap.put(newWord, totalWords);
   }
  }
  String x = "";
  for (String s: myMap.keySet()) // print values if size is > 1,
   // If you want to print non-anagrams, then print the values with size = 1
  {
   List < String > values = myMap.get(s);
   if (values.size() > 1)
    x = x + values;
  }
  return x;
 }
 public static void main(String[] args) {
  String arr[] = {
   "cat",
   "dog",
   "tac",
   "god",
   "act",
   "tom marvolo riddle ",
   "abc",
   "def",
   "cab",
   "fed",
   "clint eastwood ",
   "i am lord voldemort",
   "elvis",
   "old west action",
   "lives"
  };
  System.out.println(groupAnagrams(arr));
 }
}