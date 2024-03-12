package com.math.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
This solution is based on the 3SUM problem.
Here is an overview of the approach where we try to find any elements matching the criteria a^2+ b^2+c^2

Sort the list of integers.
Iterate the list from start to end. Select the current element as c^2
In order to find the other two elements (a^2+ b^2 = c^2​​ ),
we’ll traverse the list from start and end till start < end.
We can take advantage of the fact that the list is sorted.
While traversing the list, we’re looking for 3 numbers that sum up to 0: a^2+ b^2 + (-c^2) = 0.
a^2 = list[start] * list[start]
b^2 = list[end] * list[end]
If the current values of start & end iterators make such a triplet, we’ll save it.
a^2 + b^2+ (-c^2) > 0, we’ll decrement the end iterator in hope of hitting the target sum.
Remember, (list[start] <= list[end]).
If a^2+ b^2 + (-c^2) < 0, we’ll increment the start iterator.


 */

//Runtime complexity #
//The runtime complexity of this solution is quadratic, O(n^2)
//Memory complexity #
//The memory complexity of this solution is logarithmic, O(log n)
class PythagoreanTriplets{
  static List<int[]> findPythagoreanTriplets(int[] arr) {
    int n = arr.length;
    List<int[]> triplets = new ArrayList<int[]>();
    Arrays.sort(arr);

    for (int i = 0; i < n; ++i) {
      if (arr[i] == 0) continue;

      int c2 = arr[i] * arr[i];

      int j = 0;
      int k = n - 1;

      while (j < k) {
        if (j == i || arr[j] == 0) {
          j += 1;
          continue;
        }

        if (k == i || arr[k] == 0) {
            k -= 1;
              continue;
        }
        
        int a2 = arr[j] * arr[j];
        int b2 = arr[k] * arr[k];

        if (a2 + b2 == c2) {
          triplets.add(
            new int[]{arr[j], arr[k], arr[i]});
          break;
        }
        else if (a2 + b2 + (-c2) > 0) {
          k -= 1;
        }
        else {
          j += 1;
        }
      }
    }
    return triplets;
  }
  public static void main(String[] argv) {
    int[] l1 = {4,16,1,2,3,5,6,8,25,10};
    
    List<int[]> t1 = findPythagoreanTriplets(l1);

    System.out.println("Original: " + Arrays.toString(l1));
    String result = "";
    
    for (int[] a : t1) {
      result += "[";
      for (int x : a) {
        result += Integer.toString(x) + ",";
      }
      result = result.replaceAll(",$", "");
      result += "]";
    }
    System.out.println("Pythagorean triplets: " + result); 
  } 
  
}