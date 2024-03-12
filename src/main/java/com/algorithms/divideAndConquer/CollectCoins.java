package com.algorithms.divideAndConquer;

import java.util.Arrays;

/*
Coin collection in minimum steps #
Suppose you are given many adjacent piles of vertically stacked coins.

You are required to calculate the minimum number of steps needed
to collect these coins (minimum number of straight lines that pass through all the coins).
In one step you can collect either one horizontal or one vertical line of coins,
and collected coins should be continuous.
Problem statement #
Given an integer array representing the height of each stack of coins and the number of coins,
calculate the minimum number of straight lines that pass through all the coins.
Input #
The inputs are:
int[]height = [3, 1, 1, 5, 1]; Each value of this array corresponds to the height of the stack.
In the given array, there are 5 stacks of coins.
In the first stack, there are 3 coins, then one in the second, another one in the third, and so on.
int n = 5, The size of the height array or, in other words, the number of given stacks.

Output #
The output is int output = 3. All the above coins can be collected by no less than 3 moves.

 */
//Each time we are calling each subarray and finding the minimum of that.
// The total time complexity of the solution is O(n^2)
/*
If we start horizontally from the bottom, we can get rid of the minimum height coin rows,
 while collecting the maximum possible number of coins because the bottom rows are guaranteed to be filled.
  Suppose that we are working on the coin stacks from the left stack, say left, to the right stack, right,
  in each recursion step.
Choose the minimum height index min.
Remove min horizontal lines after which the stack will be broken into two parts:
left to min
min + 1 to right
Call the recursive method on both subarrays
Since coins may also be collected using vertical lines,
choose the minimum result of recursive calls.
Using right – left vertical lines, we can always collect all the coins vertically.
 */
class CollectCoins {

 
 // Utility method, called recursively to collect coins from `l` to `r`
 // using the height array assuming that h height has already been collected
 //
 public static int minimumStepsUtil(int left, int right, int h, int height[]) {
  
  if (left >= right) // base case: all coins already collected 
   return 0;

  int min = left;
  for (int i = left; i < right; i++) // finding minimum height index
  {
   if (height[i] < height[min])
    min = i;
  }

  return Math.min(right - left, minimumStepsUtil(left, min, height[min], height) +
   minimumStepsUtil(min + 1, right, height[min], height) + height[min] - h);
 }

 // calls the recursive utility function and returns the minimum number of steps using height array
 public static int minimumSteps(int height[], int N) {
  
  return minimumStepsUtil(0, N, 0, height);
 }

 // driver program to test the above functions
 public static void main(String args[]) {
  int[][] inputs = {{3, 1, 1, 5, 1},{ 4, 2, 1, 5, 2 }}; // you can always play around with these input values to see changing outputs
  for (int i = 0; i < inputs.length; i++) {
   System.out.println("Min Steps for " + Arrays.toString(inputs[i]) + " ---> " + minimumSteps(inputs[i], inputs[i].length));
  }
 }
}