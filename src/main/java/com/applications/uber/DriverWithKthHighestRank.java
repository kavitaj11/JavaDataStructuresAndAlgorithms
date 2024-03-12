package com.applications.uber;

import java.util.*;

/*
At Uber, each driver is assigned a rank based on the reviews they receive from their passengers.
Currently, the system prioritizes drivers with the highest rank and assigns them instant rides.
 We want to change this driver selection criterion such that
 drivers with lower ranks don’t get starved while the drivers with high ranks keep getting rides.
 The drivers’ ranks are maintained in an unsorted array.
  We’ll call a hidden API that will provide us with a number k.
  The value of this k can range from 1 to the size of our rank array. Once we have a value k, we need to find the kth highest driver rank.

We’ll be provided with an unsorted array of integers representing the divers’ ranks.
The drivers are represented by the index of this array.
The value of k will be made available from the hidden API.
 Our task will be to determine the kth highest rank, so the driver associated with that rank can be selected.

 */

//Time complexity #
//We may be storing all n elements in the heap in the worst case.
// The size of the heap is fixed at k, which is beside the point.
// However, each insert is log klogk, so n items being inserted results in O(n×logk) time complexity.
//
//Space complexity #
//The memory complexity will be O(k) because at any point we are storing k points in the Heap.

class DriverWithKthHighestRank {
  public static int kthHighestRank(int[] ranks, int k) {
    
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
    // Put first k ranks in the min heap
    for (int i = 0; i < k; i++)
      minHeap.add(ranks[i]);

    // Go through the remaining ranks of the array, if the rank from the array is greater than the
    // top (smallest) rank of the heap, remove the top rank from heap and add the rank from array
    for (int i = k; i < ranks.length; i++) {
      if (ranks[i] > minHeap.peek()) {
        minHeap.poll();
        minHeap.add(ranks[i]);
      }
    }

    // The root of the heap has the Kth largest rank
    return minHeap.peek();
  }

  public static void main(String[] args) {
    // Driver code

    int[] driverID = {1, 5, 12, 2, 11, 9, 7, 30, 20};
    int k = 3; // Supplied by a hidden API

    System.out.println("Driver with the rank " + kthHighestRank(driverID, k) + " is selected!"); 
  }
}

/*
we can use a heap to obtain the kth highest rank from our unsorted array.
We now have to select the largest number, so we’ll use a min-heap.
We’ll insert ranks of the first k drivers into the min-heap.

As we know, the root is the smallest element in a min-heap.
So, since we want to keep track of the kth highest element,
 we can compare every number with the root while iterating through all the numbers in the array.
 If any number is greater than the root element, we’ll take the root out and insert the greater number.
  This will ensure that we always have the k highest ranks in the Heap.
  In the end, we can simply return the root element as the kth highest rank.

 */