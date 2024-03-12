package com.algorithms.dynamicProgramming;


//Find the nth Fibonacci number in the Fibonacci sequence.
/*
Hints #
You may use recursion for a brute force solution.
Think along the lines of dynamic programming for an optimized solution.
 */

class Fib{

  /*
  Solution 1: recursive
  The runtime complexity of this solution is exponential, O(2^n)
  The memory complexity of this solution is linear, O(n).
  as each recursive call consumes memory on the stack.
   */
  //This solution is simple to implement but it is highly inefficient.

    static int getFibonacciRec(int n) {

      if (n == 0 || n == 1) {
        return n;
      }

      return getFibonacciRec(n - 1) + getFibonacciRec(n - 2);
    }

    /*
    Solution 2: iterative #
    The runtime complexity of this solution is linear, O(n)
    The memory complexity of this solution is constant O(1).
     */
    //This problem exhibits the properties of overlapping subproblems and optimal substructure,
    //which can be solved using dynamic programming.
    //For calculating F(n), we only need to track F{n-1) and F{n-2} and we will have a linear solution.

    static int getFibonacciDynamic(int n) {

    if (n == 0 || n == 1) {
      return n;
    }

    int n1 = 1; 
    int n2 = 0; 
    int res = 0;

    for (int i = 2; i <= n; ++i) {
      res = n1 + n2;
      n2 = n1;
      n1 = res;
    }

    return res;
  }
  
  public static void main(String[] args) {
    int[] inputs = {1, 5, 7, 10};
    
    for (int i = 0; i < inputs.length; i++){ 
     System.out.println("getFibonacci(" + inputs[i] + ") = " + getFibonacciDynamic(inputs[i]));
    }
  }
}  