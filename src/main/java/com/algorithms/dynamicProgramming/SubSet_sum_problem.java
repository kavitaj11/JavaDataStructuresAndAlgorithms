package com.algorithms.dynamicProgramming;

import java.util.ArrayList;

// A Java program to count all subsets with given sum.
public class SubSet_sum_problem {
    // dp[i][j] is going to store true if sum j is possible with array elements from 0 to i.
    static boolean[][] dp;

    static void findAllSubsets(int[] arr, int sum) {
        int n=arr.length;
        if (n == 0 || sum < 0) return; // Sum 0 can always be achieved with 0 elements
        dp = new boolean[n][sum + 1];
        for (int i=0; i<n; ++i) {
            dp[i][0] = true;
        }
        // Sum arr[0] can be achieved with single element
        if (arr[0] <= sum)
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; ++i) // Fill rest of the entries in dp[][]
            for (int j = 0; j < sum + 1; ++j)
                dp[i][j] = (arr[i] <= j) ? (dp[i-1][j] || dp[i-1][j-arr[i]]) : dp[i - 1][j];

            if (!dp[n-1][sum]) { System.out.println("There are no subsets with sum "+ sum);
            return;
        }
        ArrayList<Integer> p = new ArrayList<>();
        printSubsetsRec(arr, n-1, sum, p); //recursively traverse dp[][] to find all paths from dp[n-1][sum]
    }

    // A recursive function to print all subsets with the help of dp[][]. Vector p[] stores current subset.
    static void printSubsetsRec(int arr[], int i, int sum, ArrayList<Integer> p) {
        // If we reached end and sum is non-zero. We print p[] only if arr[0] is equal to sun
        // OR dp[0][sum] is true.
        if (i == 0 && sum != 0 && dp[0][sum]) { p.add(arr[i]); System.out.println(p);; p.clear();return; }
        // If sum becomes 0
        if (i == 0 && sum == 0) { System.out.println(p); p.clear(); return; }
        // If given sum can be achieved after ignoring current element.
        if (dp[i-1][sum]) {
            ArrayList<Integer> b = new ArrayList<>(); // Create a new vector to store path
            b.addAll(p); printSubsetsRec(arr, i-1, sum, b);
        }
        // If given sum can be achieved after considering current element.
        if (sum >= arr[i] && dp[i-1][sum-arr[i]]) {
            p.add(arr[i]); printSubsetsRec(arr, i-1, sum-arr[i], p);
        }
    }


    //Driver Program to test above functions
    public static void main(String args[])
    {
        int arr[] = {1, 2, 3, 4, 5, 8, 2, 9, 6};
        int sum = 10;
        findAllSubsets(arr, sum);
    }
}