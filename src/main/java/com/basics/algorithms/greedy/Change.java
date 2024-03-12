package com.basics.algorithms.greedy;

import java.util.Scanner;

public class Change {

    private static int getChange(int n) {
        int numChanges = 0;
        int[] denominations = new int[] {10, 5, 1};
        while (n > 0) {
            int i = 0;
            while (i <= denominations.length-1 && denominations[i] > n) i++;
            n -= denominations[i];
            numChanges++;
        }
        return numChanges;
    }

    private static int getChangeNaive(int m) {
        int count = m / 10;
        m = m % 10;
        count += m / 5;
        m = m % 5;
        count += m / 1;
        return count;
    }

    private static int getChangeGreedy(int m) {
        int changes = 0, count = 0;
        int[] denominations = {1, 6, 10};
        int max = denominations.length - 1; // index pointing to next max denominations

        while (changes < m) {
            while (changes <= m && (m - changes) >= denominations[max]) {
                changes += denominations[max];
                count++;
            }
            max = max - 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChangeGreedy(m));

    }
}

