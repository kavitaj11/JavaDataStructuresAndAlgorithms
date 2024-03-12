package com.basics;

import java.util.Scanner;

public class Fibonacci {
    private static long calc_fib(int n) {
        int[] fib = new int[n + 1];
        if( n==0)
            return 0;
        else if(n==1)
            return 1;
        else if(n>=1) {
            fib[0] = 0;
            fib[1] = 1;
            for (int i = 2; i < n + 1; ++i) {
                fib[i] = fib[i - 1] + fib[i - 2];
            }
        }

        return fib[n];
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(calc_fib(n));
    }
}
