package com.datastructures.trie;

import java.util.Arrays;

//If you are given a dictionary and a word,
// can you use a Trie to find if the given word can be formed by combining two dictionary words?
//If you are given a dictionary and a word, can you use a Trie to find
// if the given word can be formed by combining two dictionary words?
/*
Function Prototype: #
boolean isFormationPossible(String[] dict, String word);
Sample Input #
String dict[] = {"the" ,"hello", "there", "answer", "any", "Dragon",
                 "world", "their", "inc"};
String word = "helloworld"
Sample Output #
true
helloworld can be formed by combining hello and world from the “dictionary”, so the program returns true.

At the beginning of the algorithm, all the keys are inserted into the trie.
 Then, we iterate through the word, dividing it into two strings called first and second.
 We search for the two strings in the trie and if both are found,
 word can be formed with two words from the given dict.

Time Complexity #
We perform the insert operation m times for a dictionary of size m. After that,
the search operation runs on the word in the sequence:

"h", "he", "hel", "hell"...
If the length of the longest word in the dictionary is h,
then the time taken for trie construction is O(m×h).
Let the length of the word being searched be n. Then, the lookup phase takes O(n).
So, overall, the given solution is O(mh + n) or O(max(mh,n)).
 We could argue that in some applications, the trie is constructed only once,
 and then many many lookups are performed.
 So, the cost of trie creation is amortized over all the lookups.
 In that case, the complexity reduces to O(n).
 */
class DictWordFormation {
  
  public static boolean isFormationPossible(String[] dict,String word) {
    if(word.length() < 2 || dict.length < 2) {
      return false;
    }

    //Create Trie and insert dictionary elements in it
    Trie trie = new Trie();

    for(int i = 0; i < dict.length; i++) {
      trie.insert(dict[i]);
    }

    for(int i = 0; i < word.length(); i++) {
      //Slice the word into two strings in each iteration
      String first = word.substring(0, i);
      String second = word.substring(i, word.length());

      //If both substrings are present in the trie, the condition is fulfilled
      if(trie.search(first) && trie.search(second)) {
        return true;
      }
    }

    return false;
  }

  public static void main(String args[]){
    // Input dict (use only 'a' through 'z' and lower case)
    String dict[] = {"the", "hello", "there", "answer","any", "dragon", "world", "their",  "inc"};

    Trie t = new Trie();

    System.out.println("Keys: "+ Arrays.toString(dict));

    if(isFormationPossible(dict, "helloworld"))
      System.out.println("true");
    else
      System.out.println("false");

  }


}