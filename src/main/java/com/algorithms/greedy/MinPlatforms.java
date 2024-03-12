package com.algorithms.greedy;

import java.util.Arrays;
//Given the arrival and departure times of trains,
// calculate the number of platforms required for a train station.
/*
Problem statement #
Implement a function that returns the minimum number of platforms
that are required for the trains so that none of them wait.
Input #
The input is two arrays that represent the arrival and departure times of trains that stop.
Output #
The output is the minimum number of platforms required.
 */

//The problem is to find the maximum number of trains that are at the given railway station at a time.
// An iterative solution would be to take every interval one by one
// and find the number of intervals that overlap with it.
// Keep track of the maximum number of intervals that overlap, and then return the maximum value
//Since this algorithm moves iteratively, the time complexity is O(n^2)

//We can solve the same problem in less time with a slight optimization. Just be greedy!!
/*
The idea is to consider all events in sorted order. Once we have all events in sorted order,
we can trace the number of trains at any given time
and check the trains that have arrived but not departed.
 That way we know how many platforms we need at that time.
 Take the maximum of all such instances
 */
//The time complexity is O(nlogn)
//because we use sorted arrays
// and greedily keep track of the trains that have arrived but haven’t left yet.

class MinPlatforms {
 public static int findPlatform(int[] arrival, int[] departure) {

  int n = arrival.length;

  //sorting both arrays
  Arrays.sort(arrival);
  Arrays.sort(departure);

  //plat_needed indicates number of platforms needed at a time
  int plat_needed = 1;
  int result = 1;
  int i = 1;
  int j = 0;

  //Similar to merge in merge sort to process all events in sorted order
  while ((i < n) && (j < n)) {
   //If next event arrives in sorted order, increment count of platforms needed
   if (arrival[i] < departure[j]) {
    plat_needed += 1;
    i += 1;
    //Update result if needed
    if (plat_needed > result) {
     result = plat_needed;
    }
   } else {
    //Else decrement count of platforms needed
    plat_needed -= 1;
    j += 1;
   }
  }
  return result;
 }


    public static void main(String[] args){

        //Example 1
        int arrival[] = {900, 940, 950, 1100, 1500, 1800};
        int departure[] = {910, 1200, 1120, 1130, 1900, 2000};
        int answer  = MinPlatforms.findPlatform(arrival, departure);
        System.out.println("Minimum Number of Platforms Required for Plan1 = " + answer);


        System.out.println();

        // Example 2
        int arrival1[] = {200, 210, 300, 320, 350, 500};
        int departure1[] = {230, 240, 320, 430, 400, 520};
        int answer2 = MinPlatforms.findPlatform(arrival1, departure1);
        System.out.println("Minimum Number of Platforms Required for Plan2 = " + answer2);

    }
}
