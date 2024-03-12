package com.math.examples;

public class Factorial {

    public static int getFactorial(int n) {
        int i, val = 1;
        for (i = n; i > 1; i--)  /* n = 0 or 1 falls through */ {
            val *= i;
        }
        return val;
    }

    public static int getFactorialRec(int n) {
        if(n<0)
            throw new IllegalArgumentException();
        if (n == 0)
            return 1;
        else
            return n * getFactorialRec(n - 1);
    }


    public static void main(String[] args) {

        System.out.println("Factorial of 25: " + getFactorialRec(-25));
    }

    /*The time complexity for one recursive call would be:

    T(n) = T(n-1) + 3   (3 is for As we have to do three constant operations like
    multiplication,subtraction and checking the value of n in each recursive
    call)

            = T(n-2) + 6  (Second recursive call)
            = T(n-3) + 9  (Third recursive call)
            .
            .
            .
            .
            = T(n-k) + 3k
            till, k = n

    Then,

            = T(n-n) + 3n
     = T(0) + 3n
     = 1 + 3n
    To represent in Big-Oh notation,
    T(N) is directly proportional to n,
     The time complexity of recursive factorial is O(n).
     As there is no extra space taken during the recursive calls,the space complexity is O(N).
*/
}

