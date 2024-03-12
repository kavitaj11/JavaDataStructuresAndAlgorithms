package com.algorithms.dynamicProgramming;

//There are n stairs, a person standing at the bottom wants to reach the top.
// The person can climb either 1 stair or 2 stairs at a time.
// Count the number of ways, the person can reach the top.

/*
The person can reach nth stair from either (n-1)th stair or from (n-2)th stair.
 Hence, for each stair n, we try to find out the number of ways to reach n-1th stair and n-2th stair
 and add them to give the answer for the nth stair.
 Therefore the expression for such an approach comes out to be :
 ways(n) = ways(n-1) + ways(n-2)
The above expression is actually the expression for Fibonacci numbers, but there is one thing to notice, the value of ways(n) is equal to fibonacci(n+1).


ways(1) = fib(2) = 1
ways(2) = fib(3) = 2
ways(3) = fib(4) = 3
For a better understanding, let’s refer to the recursion tree below -:


Input: N = 4

                  fib(5)
           '3'  /        \   '2'
               /          \
           fib(4)         fib(3)
     '2'  /      \ '1'   /      \
         /        \     /        \
     fib(3)     fib(2)fib(2)      fib(1)
     /    \ '1' /   \ '0'
'1' /   '1'\   /     \
   /        \ fib(1) fib(0)
fib(2)     fib(1)


The runtime complexity of this solution is linear, O(n)
Auxiliary Space: O(1)
 */

class stairs {

    static int getFibonacci(int n) {

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



    public static long countNumOfWays(int n)
    {
        if(n <= 3)
            return 1l;
        long dp[] = new long[n+1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        for(int i=5;i<=n;i++)
        {
            dp[i] = dp[i-1]+dp[i-4];
        }
        return dp[n];


    }
    // Returns number of ways to reach s'th stair
    static int countWays(int s){
        return getFibonacci(s + 1);
    }
 
    /* Driver program to test above function */
    public static void main(String args[])
    {
        int s = 4;
        System.out.println("Number of ways = " + countWays(s));
    }
}