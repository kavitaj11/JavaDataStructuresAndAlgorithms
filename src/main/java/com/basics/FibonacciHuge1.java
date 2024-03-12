package com.basics;

import java.util.*;

public class FibonacciHuge1 {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }

    private static long getFibonacciHugeOptimized(long n, long m) {
        // store moduloes only in one pisano period, but size is unknown
        List<Integer> modulos = new ArrayList<>();
        modulos.add(0);
        modulos.add(1);
        int i = 0;
        // must check both current and next modular
        while(! (i > 0 && modulos.get(i) == 0 && modulos.get(i + 1) == 1)) {
            modulos.add((int) ((
                    modulos.get(  i  ) % m +
                            modulos.get(i + 1) % m ) % m));
            i++;
        }
        // i is currently pisano period; loop to Fn % i again?
        // Time-Space tradeoff: store each value in the period.
        return modulos.get((int) (n % i));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeOptimized(n, m));
    }
}


