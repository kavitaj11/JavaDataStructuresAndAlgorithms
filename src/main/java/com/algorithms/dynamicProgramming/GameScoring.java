package com.algorithms.dynamicProgramming;

//Imagine a game where a player can score 1, 2 or 4 runs.
// Given a score, n, find the total number of ways to score n runs.

/*Hints #
        Use Fibonacci numbers.
        Use the memoization technique.
        Use principles of dynamic programming.
 */

class GameScoring{

  //Solution 1: Recusrsive solution using memoization
  //Runtime complexity #
  //The runtime complexity of this solution is linear, O(n)
  //Memory complexity #
  //The memory complexity of this solution is linear, O(n)
  /*
  Memoization is an optimization technique used to make programs faster
  and improve their performance by storing the results of expensive function calls
  and returning the cached result when the same input occurs again.
  It saves the computed results for possible later reuse, rather than recomputing them.

  The scoring options are 1, 2 and 4.
  To find the number of ways a player can score nn runs, the recurrence relation is as follows:

  S(n)=S(n-1)+S(n-2)+S(n-4)

  We’ll build the solution top-down and store the found results in a result array.
  This is classic memoization.
  */

    public static int scoringOptionsRec(int n, int[] result) {
      if(n < 0){
        return 0;
      }

      if(result[n] > 0) {
        return result[n];
      }

      //Memoize
      result[n] = scoringOptionsRec(n - 1, result) +
              scoringOptionsRec(n - 2, result) +
              scoringOptionsRec(n - 4, result);

      return result[n];
    }

    public static int scoringOptions(int n) {
      if(n <= 0) {
        return 0;
      }

      int[] result = new int[n + 1];

      for (int i = 0; i < n + 1; i++) {
        result[i] = -1;
      }
      result[0] = 1;

      scoringOptionsRec(n, result);

      return result[n];
    }


  //Solution 2: dynamic programming
  /*
  Runtime complexity #
  The runtime complexity of this solution is linear, O(n)

  Memory complexity #
  The memory complexity of this solution is constant, O(1)

  In this solution, we’ll use the dynamic programming approach
  to building the solution bottom-up by storing the results in a fixed-size array.

  The maximum score is 4, so we need to save the last four ways to calculate the number of ways for a given n.
  On each iteration, the result will be the sum of values present at the 3rd, 2nd and 0th index of the result array.
  This is because the result at n is the sum of values at n-1, n-2 and n-4.
  We’ll slide the result towards the left and save the current result at the last index.
   */


  // Scoring options are 1, 2, and 4
  public static int scoringOptionsDynamic(int n) {
    if(n <= 0)
      return 0;

    // Max score is 4. Hence we need to save
    // last 4 ways to calculate the number of ways
    // for a given n
    int[] result = new int[4];

    //save the base case on last index of the vector
    result[3] = 1;

    for(int i = 1; i <= n; i++) {
      int sum = result[3] + result[2] + result[0];

      //slide left all the results in each iteration
      //result for current i will be saved at last index
      result[0] = result[1];
      result[1] = result[2];
      result[2] = result[3];
      result[3] = sum;
    }

    return result[3];
  }


  public static void main(String[] args) {
   System.out.println("Number of ways score 5 can be reached = " + scoringOptionsDynamic(5));
  }



}  