package com.algorithms.sorting;

public class QuickSortSimple {
    //Quicksort is a divide-and-conquer algorithm that involves choosing a pivot value from a data set
    // and then splitting the set into two subsets:
    // a set that contains all values less than the pivot and
    // a set that contains all values greater than or equal to the pivot.
    // The pivot/split process is recursively applied to each subset until there are no more subsets to split.
    // The results are combined to form the final sorted set.
    // Performing log(n) operations on each of n elements yields a combined best case complexity of O(n log(n)).
    // if your pivot choice is poor? worst-case complexity is O(n^2). This is the same as selection sort or insertion sort.
    // The average case complexity of quicksort is also O(n log(n)).
    // Most implementations of quicksort are not stable.


    public static void quicksortSimple( int[] data ){

        if ( data.length < 2 ){
            return;
        }
        int pivotIndex = data.length / 2;
        int pivotValue = data[ pivotIndex ];

        int leftCount = 0;

        // Count how many are less than the pivot
        for ( int i = 0; i < data.length; ++i ){
            if ( data[ i ] < pivotValue ) ++leftCount;
        }
        // Allocate the arrays and create the subsets
        int[] left = new int[ leftCount ];
        int[] right = new int[ data.length - leftCount - 1 ];

        int l = 0;
        int r = 0;

        for ( int i = 0; i < data.length; ++i ){
            if ( i == pivotIndex ) continue;
            int val = data[ i ];
            if ( val < pivotValue ){
                left[ l++ ] = val;
            } else {
                right[ r++ ] = val;
            }
        }
        // Sort the subsets
        quicksortSimple( left );
        quicksortSimple( right );

        // Combine the sorted arrays and the pivot back into the original array

        System.arraycopy( left, 0, data, 0, left.length );
        data[ left.length ] = pivotValue;
        System.arraycopy( right, 0, data, left.length + 1, right.length );
    }

}
