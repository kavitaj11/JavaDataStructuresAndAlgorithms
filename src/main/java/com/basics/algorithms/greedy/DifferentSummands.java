package com.basics.algorithms.greedy;

import java.util.*;

/**
 * Represent a given positive integer n as a sum of as many pairwise distinct positive integers as possible.
 * Constraints: 1≤n≤10^9.
 * Example: 6=1+2+3.
 * Example: 8=1+2+5.
 */

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        int i = 1;
        while (n > 0) {
            if (n - i > i || n == i) {
                summands.add(i);
                n -= i;
                i++;
            }
            else if (n - i <= i) i++;
        }
        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

