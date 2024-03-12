package com.algorithms.recursion.simplerecursiveprograms;

public class FindMaxInArray {

        // Find max value in an unsorted array of ints.
        public static int findMax (int [] A, int startIndex) {
            if (startIndex == A.length - 1) {
                return A[startIndex];
            } else {
                return Math.max(A[startIndex],
                        findMax(A, startIndex + 1));
            }
        }

}
