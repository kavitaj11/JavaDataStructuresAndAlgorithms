package com.datastructures.map;//Hash Set  =>  HashSet<Integer> hSet = new HashSet<>();
//HashMap   =>  HashMap<Integer,String> hMap = new HashMap<>();  
//HashTable =>  Hashtable<Integer,String> hTable = new Hashtable<>();  
//Hash Set Functions => {add(), remove(), contains()}
//Hash Map and Table Functions => {put(key,value), get(key), remove(key), containsKey(key), containsValue(value)}
/*

Explanation #
The naive solution would be to iterate the array in a nested loop, summing each element with all the elements succeeding it.

A hash table makes things much simpler.

We basically have to check for 3 conditions:

If 0 exists in the array
If the sum becomes zero in the iteration
If the sum reverts back to a value which was already a key in the hash table
Any of these three conditions confirms the existence of a subarray that sums up to be zero.

Time Complexity #
As always, a linear iteration over n elements means that the algorithm’s time complexity is O(n).
*/

import java.util.HashMap;

class FindIfSubsetSumEqualsZeroExists {

  public static boolean findSubZero(int[] arr) {

    //Use HashMap to store Sum as key and index i as value till sum has been calculated.
    //Traverse the array and return true if either 
    //arr[i] == 0 or sum == 0 or HashMap already contains the sum
    //If you completely traverse the array and havent found any of the above three
    //conditions then simply return false.
    HashMap< Integer,Integer > hMap = new HashMap < >();

    int sum = 0;

    // Traverse through the given array
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];

      if (arr[i] == 0 || sum == 0 || hMap.get(sum) != null) return true;

      hMap.put(sum, i);
    }

    return false;
  }

  public static void main(String args[]) {

    int[] arr = {6, 4, -7, 3, 12, 9};
    System.out.println(findSubZero(arr));

  }
}
