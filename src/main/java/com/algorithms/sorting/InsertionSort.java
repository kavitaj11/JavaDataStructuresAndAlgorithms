package com.algorithms.sorting;

public class InsertionSort {

    //The best-case running time for insertion sort is O(n),
    // which occurs when the list is already sorted.
    // This means insertion sort is an efficient way to add a few new elements to a presorted list.

    //The average and worst cases are both O(n2),
    // however, so it’s not the best algorithm to use for large amounts of randomly ordered data.
    //It is a stable, in-place sorting algorithm especially suitable for sorting small data sets
    // and is often used as a building block for other, more complicated sorting algorithms.

    public static void insertionSort( int[] data ){
        for ( int startIndex = 1; startIndex < data.length; ++startIndex ){
            int val = data[startIndex];
            for ( int i = startIndex - 1; i >= 0; --i ){
                if ( data[i] > val ){
                    data[i + 1] = data[i];
                    data[i] = val;
                } else {
                    break;
                }
            }
        }
    }


}
