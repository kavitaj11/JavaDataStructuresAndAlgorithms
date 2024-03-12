//package com.basics;

import java.util.*;

public class FibonacciLastDigit {

    private static int getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current  = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % 10;
    }

    private static int getFibonacciLastDigit(int n) {
        int[] last_digits = new int[n + 1];
        last_digits[0] = 0;
        last_digits[1] = 1;
        for(int i = 2; i <= n; i++) {
            last_digits[i] = (
                    last_digits[i - 2] % 10 +
                            last_digits[i - 1] % 10 ) % 10;
        }
        return last_digits[n];
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}


