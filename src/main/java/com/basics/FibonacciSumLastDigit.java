package com.basics;

import java.util.*;

public class FibonacciSumLastDigit {
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }


    /** to find the last digit of the sum of n Fibonacci Numbers
     * We know that the recurrence of last digits in Fibonacci series is after every 60 numbers (60 is the Pisano number).
     * In addition to this, we know that "the sum of the first n Fibonacci numbers is the (n + 2)nd Fibonacci number minus 1."
     * Using these 2 facts, we can find the last digit of sum of n Fibonacci series efficiently which is suitable for large values of n.
     */

        private static long getFibonacciSumLastDigit(long n) {
            if (n == 1 || n == 0) {
                return n;
            }
            //to find ((n+2) mod 60)th  Fibonacci number
            n = (n + 2) % 60;
            ArrayList<Long> fib = new ArrayList<>();
            fib.add(0L);
            fib.add(1L);
            for (int i = 2; i <= n; i++)
                fib.add((fib.get(i - 1) % 10 + fib.get(i - 2) % 10) % 10);

            //if the last digit of n+2 th Fibonacci number is 0--> 0 meaning 10,
            //the last digit of sum of first n Fibonacci series becomes 10-1=9. Hence return 9.
            if (fib.get((int) n) == 0) {
                return 9;
            }

            //else return the last digit of sum of n Fibonacci series which is F(n+2)-1
            //We need not find the entire sum, just the last digit is sufficient.
            return (fib.get((int) n) % 10) - 1;
        }

        static int getFibonacciSumLastDigitOptimized(long n) {

        // The first two Fibonacci numbers
        int f0 = 0;
        int f1 = 1;

        // Base case
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        else
        {

            // Pisano period for % 10 is 60
            int rem = (int) (n % 60);

            // Checking the remainder
            if(rem == 0)
                return 0;

            // The loop will range from 2 to two terms after the remainder
            for(int i = 2; i < rem + 3; i++)
            {
                int f = (f0 + f1) % 60;
                f0 = f1;
                f1 = f;
            }

            int s = f1 - 1;
            return s;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long c = getFibonacciSumLastDigit(n);
        //long s = (int)Math.abs(getFibonacciSumLastDigit(n)%10);
        System.out.println(c);
    }
}

