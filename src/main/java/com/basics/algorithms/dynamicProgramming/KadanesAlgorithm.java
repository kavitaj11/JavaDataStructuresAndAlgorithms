package com.basics.algorithms.dynamicProgramming;

//a simple dynamic programming based algorithm which is the optimal solution to the Maximum Sum Subarray Problem.

//Maximum Sum Subarray –
// Given an array A[1, 2, 3, … n]. We need to find that subarray A'[i, i + 1, i + 2, … j]
// where 1 ≤ i ≤ j ≤ n, where the sum of all the elements in A’ is maximum.
//
//In simpler terms, we need to find that contiguous portion of an array where the sum is maximum.
//Kadane’s Algorithm states that,
//The maximum sum sub-array ending with A[i], is
// either the element A[i] itself, or A[i] combined with the maximum sum sub-array ending with A[i – 1],
// whichever is the greater one.

public class KadanesAlgorithm {

    public static void main(String[] args) {
        int[] arr = {1, -22, 10, 8, 2, 3, 6, 0, 0};
        System.out.println(maxSubArray(arr));
    }

    public static int maxSubArray(int[] arr) {
        int maxSum, maxGlobalSum;

        maxSum = maxGlobalSum = arr[0];

        for (int i = 1; i < arr.length; ++i) {
            maxSum = arr[i] > arr[i] + maxSum ? arr[i] : arr[i] + maxSum;
            maxGlobalSum = maxSum > maxGlobalSum ? maxSum : maxGlobalSum;
            System.out.println("for "+arr[i]+ " ,maxSum= "+maxSum+ " ,maxGlobalSum= "+maxGlobalSum);
        }

        return maxGlobalSum;
    }

}