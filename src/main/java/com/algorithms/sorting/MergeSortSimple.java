package com.algorithms.sorting;

import static com.algorithms.sorting.InsertionSort.insertionSort;

public class  MergeSortSimple {
    // Sort an array using a simple but inefficient merge sort.

    //The best, average, and worst-case running times for merge sort are all O(n log(n)),
    // which is great when you need a guaranteed upper bound on the sorting time.
    // However, merge sort requires O(n) additional storage—substantially more than many other algorithms.
    // Typical (maximally efficient) merge sort implementations are stable but not in-place.

    public static void mergeSortSimple( int[] data ){
        if ( data.length < 2 ){
            return;
        }
        //A hybrid merge sort occurs when a different sorting algorithm is used to sort subsets below a specified minimum size.
        //This is a common optimization because insertion sort has lower overhead than merge sort and
        // typically has better performance on very
        //
        if ( data.length < 10 ){ // some small empirically determined value
            insertionSort( data );
            return;
        }

       // Split the array into two subarrays of approx equal size.
        int   mid = data.length / 2;
        int[] left = new int[ mid ];
        int[] right = new int[ data.length - mid ];

        System.arraycopy( data, 0, left, 0, left.length );
        System.arraycopy( data, mid, right, 0, right.length );

        // Sort each subarray, then merge the result.
        mergeSortSimple( left );
        mergeSortSimple( right );

        merge( data, left, right );
    }

     // Merge two smaller arrays into a larger array.
     private static void merge( int[] dest, int[] left, int[] right ){
        int destIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;
         // Merge arrays while there are elements in both
         while ( leftIndex < left.length && rightIndex < right.length ){
             if ( left[ leftIndex ] <= right[ rightIndex ] ){
                 dest[ destIndex++ ] = left[ leftIndex++ ];
             } else {
                 dest[ destIndex++ ] = right[ rightIndex++ ];
             }
         }

         // Copy rest of whichever array remains
         while ( leftIndex < left.length )
             dest[ destIndex++ ] = left[ leftIndex++ ];

         while ( rightIndex < right.length )
             dest[ destIndex++ ] = right[ rightIndex++ ];
     }


}
