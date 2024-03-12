package com.algorithms.sorting;

public class SelectionSortStable {

    //an in-place comparison sort.
    //O(n2) time complexity, making it inefficient on large lists, and
    //generally performs worse than the similar insertion sort.

    // Sort an array using a stable selection sort.
    public static void selectionSortStable( int[] data ){
        for ( int start = 0; start < data.length - 1; ++start ){
            insert( data, start, findMinimumIndex( data, start ) );
        }
    }


    // Insert the data into the array, shifting the array as necessary.
    private static void insert( int[] data, int start, int minIndex ){
        if ( minIndex > start ){
            int tmp = data[minIndex];
            System.arraycopy( data, start, data, start + 1, minIndex - start );
            data[start] = tmp;
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


}
