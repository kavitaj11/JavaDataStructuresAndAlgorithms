package com.datastructures.arrays;/*
Runtime complexity #
The runtime complexity of this solution is exponential, O(2^n), where nn is the number of integers in the given set.

Memory complexity #
The memory complexity of this solution is logarithmic, O(logn).

This recursive solution has O(logn) memory complexity as it will consume memory on the stack.
In this solution, we will recursively generate subsets of the given list. 
While generating a subset,
if the subset’s sum is already greater than the KK,
we will drop/skip that subset and continue generating the rest of the subsets.
*/


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class SubsetSumUsingRecursion{
  static void getKSumSubsetsRec(List<Integer> list, List<Integer> partialList, Integer targetSum, List<HashSet<Integer>> sets) {
    int sum = 0;
    for (Integer x : partialList) {
      sum += x;
    }

    if (sum == targetSum && partialList.size() > 0) {
      sets.add(new HashSet<Integer>(partialList));
    }
    else if (sum > targetSum) {
      return;
    }
    else {
      for (int i = 0; i < list.size(); ++i) {
        ArrayList<Integer> newPartialList = new ArrayList<Integer>(partialList);
        newPartialList.add(list.get(i));

        List<Integer> new_list = list.subList(i+1, list.size());

        getKSumSubsetsRec(new_list, newPartialList, targetSum, sets);
      }
    }
  }

  static List<HashSet<Integer>> getKSumSubsets(List<Integer> list, Integer targetSum) {
  	List<Integer> partialList = new ArrayList<Integer>();
    List<HashSet<Integer>> subsets = new ArrayList<HashSet<Integer>>();
  	getKSumSubsetsRec(list, partialList, targetSum, subsets); 
    
    return subsets;
  }
  
  public static void main(String[] args) {
    // initializing vector
    int[] myints = {8, 13, 3, 22, 17, 39, 87, 45, 36};
    List<Integer> vec = new ArrayList<Integer> ();
    for (Integer i: myints) {
      vec.add(i);
    }

    // computing subsets
    List<HashSet<Integer>> subsets = new ArrayList<HashSet<Integer>> ();
    subsets = getKSumSubsets(vec, 125);

    System.out.print("[");
    // printing subsets
    for (int i = 0; i < subsets.size(); ++i) {
      System.out.print("{");
      for (Integer it: subsets.get(i)) {
        System.out.print(it + ", ");
      }
      System.out.print("} ");
    }
    System.out.print("]");
  }
}  
