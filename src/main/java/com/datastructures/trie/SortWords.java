package com.datastructures.trie;

import java.util.ArrayList;
import java.util.Arrays;

//If you are given an array of strings, can you sort its elements using a Trie data structure?
//In this problem, you have to implement the sortArray() function
// to sort the elements of an array of strings in alphabetical order.
/*
Sample Input #
String keys[] = {"the", "a", "there", "answer", "any",
                     "by", "bye", "their","abc"};
Sample Output #
{"a", "abc", "answer", "any", "by", "bye", "the", "their", "there"}

Since the children array for each node stores alphabets in alphabetical order,
the tree itself is ordered from top to bottom.
All we need to do is make a pre-order traversal
(think of a as the left most child and z as the right most child)
and store the words in a list.

Time Complexity #
We first insert the nodes into the graph and then traverse all the existing nodes.
Hence, the bottleneck worst case time complexity is O(n).
 */
public class SortWords {

  //Solution: Pre-Order Traversal #
  //Recursive Function to generate all words in alphabetic order
  public static void getWords(TrieNode root, ArrayList< String > result, int level, char[] str) {
    //Leaf denotes end of a word
    if (root.isEndWord) {
      //current word is stored till the 'level' in the character array
      String temp = "";
      for (int x = 0; x < level; x++) {
        temp += Character.toString(str[x]);
      }
      result.add(temp);
    }

    for (int i = 0; i < 26; i++) {
      if (root.children[i] != null) {
        //Non-null child, so add that index to the character array
        str[level] = (char)(i + 'a');
        getWords(root.children[i], result, level + 1, str);
      }
    }
  }
  public static ArrayList<String> sortArray(String[] arr){
    ArrayList<String> result = new ArrayList<String>();

    //Creating Trie and Inserting words from array
    Trie trie = new Trie();
    for (int x = 0; x < arr.length ; x++)
      trie.insert(arr[x]);

    char[] char_arr = new char[20];
    getWords(trie.getRoot(),result,0,char_arr);  
    return result;
  }

  public static void main(String args[]) {
    // Input keys (use only 'a' through 'z' and lower case)
    String keys[] = {"the", "a", "there", "answer", "any",
                     "by", "bye", "their","abc"};
    
    Trie t = new Trie();

    System.out.println("Keys: "+ Arrays.toString(keys));

    // Construct trie

    for (int i = 0; i < keys.length ; i++)
      t.insert(keys[i]);


    ArrayList < String > list = sortArray(keys);
    for(int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }


  }

}