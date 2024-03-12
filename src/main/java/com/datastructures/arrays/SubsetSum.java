package com.datastructures.arrays;/*
We are given a set of n positive integers and we have to find all the possible subsets of integers
that sum up to a number K.

Solutions: Guess number of subsets of a set of size n and use combinations.
Runtime complexity #
The runtime complexity of this solution is exponential, O(2^n),
 where n is the number of integers in the given set.

Memory complexity #
The memory complexity of this solution is constant, O(1).

We know that for a set of n elements there are 2^n subsets.
For example, a set with 3 elements will have 8 subsets.
Here is the algorithm we will use:
n = size of given integer set
subsets_count = 2^n
for i = 0 to subsets_count
  form a subset using the value of 'i' as following:

    bits in number 'i' represent index of elements to choose from original set,
    if a specific bit is 1 choose that number from original set and add it to current subset,
    e.g. if i = 6 i.e 110 in binary means that 2nd and 3rd elements in original array need to be picked.

  if subset elements sum up to K (required sum), add current subset to list of all subsets
  
Note that the ordering of bits for picking integers from the set does not matter,
i.e., picking integers from left to right would produce the same output as picking integers from right to left.
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class SubsetSum {
  static int getBit(int num, int bit) {
    int temp = (1<< bit);
    temp = temp & num;
    if (temp == 0) {
      return 0;
    }
    return 1;
  }

  static List<HashSet<Integer>> getKSumSubsets(List<Integer> v, Integer targetSum) {
    
    List<HashSet<Integer>> subsets = new ArrayList<HashSet<Integer>>();
    
    int subsets_count = (int)(Math.pow((double) 2, (double) v.size()));
    
    for (int i = 0; i < subsets_count; ++i) {
      HashSet<Integer> set = new HashSet<Integer> ();
      int sum = 0;

      for (int j = 0; j < v.size(); ++j) {
        if (getBit(i, j) == 1) {
          sum += v.get(j);
          if (sum > targetSum) {
            break;
          }
          set.add(v.get(j));
        }
      }
      if (sum == targetSum) {
        subsets.add(set);
      }
    }
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
