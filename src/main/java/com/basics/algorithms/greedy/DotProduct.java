package com.basics.algorithms.greedy;

import java.util.*;
/*
Given two sequences 𝑎 1 , 𝑎 2 , . . . , 𝑎 𝑛 (𝑎 𝑖 is the profit per click of the 𝑖-th ad)
 and 𝑏 1 , 𝑏 2 , . . . , 𝑏 𝑛 (𝑏 𝑖 is the average number of clicks per day of the 𝑖-th slot),
 we need to partition them into 𝑛 pairs (𝑎 𝑖 , 𝑏 𝑗 ) such that the sum of their products is maximized.
Input Format. The integer 𝑛,
 a sequence of integers 𝑎 1 , 𝑎 2 , . . . , 𝑎 𝑛 ,
 a sequence of integers 𝑏 1 , 𝑏 2 , . . . , 𝑏 𝑛 .
Constraints. 1 ≤ 𝑛 ≤ 10 3 ; −10 5 ≤ 𝑎 𝑖 , 𝑏 𝑖 ≤ 10 5 for all 1 ≤ 𝑖 ≤ 𝑛.

 */
public class DotProduct {
  
    // a = profit per click
    // b = click per day
    private static long maxDotProduct(Integer[] a, Integer[] b) {
        long result = 0;
        int slots = a.length;

        Arrays.sort(a, Collections.reverseOrder());
        Arrays.sort(b, Collections.reverseOrder());

        for (int slot = 0; slot < slots; slot++) { // for every slot
            result += ((long)a[slot] * b[slot]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}

