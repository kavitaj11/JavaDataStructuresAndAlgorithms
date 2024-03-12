package com.math.examples;

public class  Fibonacci {
     //Dynamic Programming Bottom Up Approach
     //We start solving the problem with the smallest possible inputs and store it for future.
     //Now as we calculate for the bigger values use the stored solutions (solution for smaller problems).
     //optimized versions of Fibonacci using dynamic programming like this:
        public int fibDP(int x) {
            int[] fib = new int[x + 1];
            fib[0] = 0;
            fib[1] = 1;

            for (int i = 2; i < x + 1; i++) {
                fib[i] = fib[i - 1] + fib[i - 2];
            }

        return fib[x];
    }


    //Using Recursion
    public int fibonacci(int n)
    {
        return (n<=1)? n:fibonacci(n-1)+fibonacci(n-2);
    }


/*
    If you have fibonacci with value "10" in your recursion,
    you are basically saying (the finonacci for 10 is the fibonacci for 9 + fibonacci for 8)
    And then fo fibonacci 9 you say - it is fibonacci 8 + fibonacci 7 etc.
    I think it is obvious that it will continue to an almost full binary tree.
    And you can see that for every level the number of nodes is doubled,
     therefore for fib(10) it will repeat itself 10 times having at the bottom almost 2^10,
     therefore for fib(n) it will be 2^n.

    How to make it effective in recursive alghoritm?
    Well you can see right from the picture that i.e. fib(7) is solved three times.
    So you have to remember the fib(n) once you calculated it.
    It can be a global variable or you can pass reference to an object through the recursive call.

    Then you dont just say "fib(n-1) and fib(n-2)", you first look "is fib(n-1) counted"?
    And if so, instead of doing recursion, use the calculated value.


    */
}
