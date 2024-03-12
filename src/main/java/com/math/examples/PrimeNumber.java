package com.math.examples;

public class PrimeNumber {
    //checks whether an int is prime or not.
    // 2 is the “oddest” prime – it happens to be the only even prime number.
    // Because of this, we need only check 2 separately,
    // then traverse odd numbers up to the square root of n.
    boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}
