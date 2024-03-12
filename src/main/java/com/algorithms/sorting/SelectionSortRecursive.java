package com.algorithms.sorting;

public class SelectionSortRecursive {
   /* selection sort algorithm sorts an array by repeatedly finding the minimum element (considering ascending order)
     from unsorted part and putting it at the beginning.
     The algorithm maintains two subarrays in a given array.

            1) The subarray which is already sorted.
            2) Remaining subarray which is unsorted.

    In every iteration of selection sort, the minimum element (considering ascending order) from the unsorted subarray is picked
    and moved to the sorted subarray.

  */
    //The best, average, and worst-case running times for selection sort are all O(n^2)
    //in-place algorithm
    //not stable

    // Sort an array using a recursive selection sort.
    public static void selectionSortRecursive(int[] data) {
        selectionSortRecursive(data, 0);
    }

    // Sort a subset of the array starting at the given index.
    private static void selectionSortRecursive(int[] data, int start) {
        if (start < data.length - 1) {
            swap(data, start, findMinimumIndex(data, start));
            selectionSortRecursive(data, start + 1);
        }
    }

    // Find the position of the minimum value starting at the given index.
    private static int findMinimumIndex(int[] data, int start) {
        int minPos = start;

        for (int i = start + 1; i < data.length; ++i) {
            if (data[i] < data[minPos]) {
                minPos = i;
            }
        }
        return minPos;
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

