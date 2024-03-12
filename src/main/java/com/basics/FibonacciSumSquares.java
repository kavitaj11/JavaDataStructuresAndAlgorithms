package com.basics;

import java.util.*;

public class FibonacciSumSquares {
    private static long getFibonacciSumSquaresNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current * current;
        }

        return sum % 10;
    }

    /**
     * To find the last digit of sum of squares of n Fibonacci terms.
     * To do this efficiently, we need not square each term and then add it.
     * We know that, the sum of squares of n Fibonacci terms is equal to the Product of nth and (n+1)th Fibonacci terms.
     * Also using the property of periodicity of last digit recurrence in Fibonacci series, we can solve this problem efficiently for
     * large values of n.
     */

     private static long getFibonacciSumSquaresLastDigit(long n) {
            if (n <= 1)
                return n;

            long current = calc_fib(n % 60);
            long next = calc_fib((n + 1) % 60);

            //We need not know what is the actual product to find the last digit.
            // Just the last digit of F(n) and F(n+1) are sufficient.
            //This reduces the multiplication complexity greatly! ;)
            return ((current % 10) * (next % 10) % 10);
        }

        //General code to find the nth Fibonacci term
        private static long calc_fib(long n) {
            if (n == 1 || n == 0) {
                return n;
            }

            long fib1 = 0;
            long fib2 = 1;
            long fib3 = 0;
            for (long i = 2; i <= n; i++) {
                fib3 = fib1 + fib2;
                fib1 = fib2;
                fib2 = fib3;
            }
            return fib3;
        }

    /*F1^2+..Fn^2 = Fn*Fn+1
    Now to calculate the last digit of Fn and Fn+1, we can apply the pisano period method.
    last digit can be calculated by %10 and pisano period for mod 10 is 60. so, % 60 is used in the code directly.
    */

    static int getFibonacci(long n)
    {
        int pre=0,cur=1;
        n = n %60;
        if(n==0){
            return 0;}
        else if (n == 1){
            return 1;
        }
        else{
            for (int i =2; i<=n; i++){
                int temp = (pre+cur)%60;
                pre = cur;
                cur = temp;
                // cout<<pre<<"\n"<<cur<<"\n";
            }
        }

        return(cur);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int a= getFibonacci(n);
        int b= getFibonacci(n+1);
        System.out.println((a*b)%10);
    }
}

