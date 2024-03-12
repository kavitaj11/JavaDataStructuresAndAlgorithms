package com.recursion.simplerecursiveprograms;

public class Factorial {


    public static int factorial (int n) {
        if (n < 0) {
            throw new IllegalArgumentException ("n must be non-negative");
        }
        if (n==0) {
            return 1;
        } else {
            return n * factorial (n-1);
        }
    }

    //1. If you are not careful with the program logic,
    // you may miss a basis case and go off into an infinite recursion. 
    // This is similar to an infinite loop!
    //Example: call to factorial with N < 0
    //So you should handle the check for negative numbers in your program

    //2. When the factorial program is run with certain inputs 13 and above,
    // we get incorrect results - negative numbers or results which do not match with the actual factorial of that number.
    // The reason is that the factorial of numbers greater than or equal to 13 is too large for the int data type.
    // We can use the long data type but still it wouldn't be large enough to hold the factorial of even higher numbers.
    // The solution is to use the BigInteger class which can handle arbitrarily large numbers

}
