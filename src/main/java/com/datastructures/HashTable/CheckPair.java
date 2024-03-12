package com.datastructures.HashTable;


import java.util.HashMap;

//If you are given an array, can you find two pairs such that their sum is equal?
//Each element in arr is summed with all other elements one by one and store the pair.
// The sum becomes the key in the hMap. At every key, we store the integer pair whose sum generated that key.
//Whenever a sum is found such that its key in the hMap already has an integer pair stored in it,
// we can conclude that this sum can be made by two different pairs in the array.
// These two pairs are then returned in the result array.
//
//Time Complexity #
//The nested loop over n elements means that this algorithm’s runtime is O(n2).
class CheckPair {

  public static String findPair(int[] arr) {

    String result = "";

    // Create HashMap with Key being sum and value being a pair i.e key = 3 , value = {1,2}
    // Traverse all possible pairs in given arr and store sums in map
    // If sum already exist then print out the two pairs.
    HashMap< Integer,int[] > hMap = new HashMap < >();

    for (int i = 0; i < arr.length; ++i) {
      for (int j = i + 1; j < arr.length; ++j) {
        int sum = arr[i] + arr[j]; //calculate sum

        if (!hMap.containsKey(sum)) {
          // If sum is not present in Map then insert it alongwith pair
          hMap.put(sum, new int[] {arr[i],arr[j]});
        }
        else {
          //Sum already present in Map
          int[] prev_pair = hMap.get(sum);

          // Since array elements are distinct, we don't
          // need to check if any element is common among pairs
          result += "{" + prev_pair[0] + "," + prev_pair[1] + "}{" + arr[i] + "," + arr[j] + "}";

          return result;
        }
      }
    }//end of for
    return result;
  }
  
  public static void main(String args[]) {
   
     int[] arr = {3, 4, 7, 1, 12, 9};
     System.out.println(findPair(arr));
    
  }
}