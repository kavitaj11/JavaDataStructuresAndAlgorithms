package com.algorithms.sorting;

public class QuickSortOptimized {
    public static void quicksortOptimized(int[] data) {

        quicksortOptimized(data, 0, data.length - 1);
    }

    public static void quicksortOptimized(int[] data, int left, int right) {
        int pivotValue = data[(int) ((((long) left) + right) / 2)];
        int i = left;
        int j = right;

        while (i <= j) {
            // Find leftmost value greater than or equal to the pivot.
            while (data[i] < pivotValue) i++;
            // Find rightmost value less than or equal to the pivot.
            while (data[j] > pivotValue) j--;

            // Swap the values at the two indices if those indices have not yet crossed.
            if (i <= j) {
                swap(data, i, j);   i++;  j--;
            }
        }
        // Apply the algorithm to the partitions we made, if any.
        if (left < j) {
            quicksortOptimized(data, left, j);
        }
        if (i < right) {
            quicksortOptimized(data, i, right);
        }
    }


    // Swap two elements in an array.
    private static void swap(int[] data, int index1, int index2) {
        if (index1 != index2) {
            int tmp = data[index1];
            data[index1] = data[index2];
            data[index2] = tmp;
        }
    }

}