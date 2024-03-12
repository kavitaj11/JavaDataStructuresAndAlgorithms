package com.algorithms.greedy;

import java.util.Arrays;
//Given “n” block chains of varying lengths, find the minimum cost of connecting them.
//The cost to connect two pipes is equal to the sum of their lengths.
// We need to connect the pipes with minimum cost.

//The time complexity for this solution is O(nlogn),
// because of the optimized built-in Java sort function.
class MinimumCost {
 
 public static int minCost(int[] pipes) {
  
  int cost = 0;
  int n = pipes.length;
  Arrays.sort(pipes); //Sorting the array
  for (int i = 0; i < n - 1; i++) {
   int prev_cost = cost; // store previous cost for later use
   cost = (pipes[i] + pipes[i + 1]); //find current cost
   pipes[i + 1] = cost; //insert in array
   cost = cost + prev_cost; //add with previous cost
  }
  return cost;
 }
}

class Main{
  public static void main(String[] args) {
  int[] pipes = {4, 3, 2, 6 };
  System.out.println("Total cost for connecting pipes is " + MinimumCost.minCost(pipes));
  int[] pipes1 = {1, 1, 2, 6};
  System.out.println("Total cost for connecting pipes1 is " + MinimumCost.minCost(pipes1));
  }
}