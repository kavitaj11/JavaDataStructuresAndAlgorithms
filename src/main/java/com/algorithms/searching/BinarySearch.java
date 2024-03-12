package com.algorithms.searching;

public class BinarySearch {

    //A binary searching is O(log(n))
    // because half of the searching space is eliminated (in a sense, searched) on each iteration.
    // This is more efficient than a simple searching through all the elements, which would be O(n).
    // However, to perform a binary searching, the array must be sorted, an operation that is usually O(n log(n)).
    //

    //Iterative Binary Search
    int iterBinarySearch( int[] array, int target ) {
        int lower = 0, upper = array.length - 1;
        int center, range;

        while ( true ){
            range = upper - lower;
            if ( range < 0 ){
                throw new RuntimeException("Element not in array");

            }
            if ( array[lower] > array[upper] ){
                throw new RuntimeException("Array not sorted");
            }
            center = ( range / 2 ) + lower;
            if ( target == array[center] ){
                return center;
            } else if ( target < array[center] ){
                upper = center - 1;
            } else {
                lower = center + 1;
            }
        }
    }

    //Recursive Binary Search
    int binarySearch( int[] array, int target ) {
        return binarySearch( array, target, 0, array.length - 1 );
    }


    int binarySearch( int[] array, int target, int lower, int upper ) throws RuntimeException {
        int center, range;

        range = upper - lower;
        if ( range < 0 ){
            throw new RuntimeException("Element not in array");
        }

        if ( array[lower] > array[upper] ){
            throw new RuntimeException("Array not sorted");
        }
        center = ( range / 2 ) + lower;
        if ( target == array[center] ){
            return center;
        } else if ( target < array[center] ){
            return binarySearch( array, target, lower, center - 1 );
        } else {
            return binarySearch( array, target, center + 1, upper );
        }
    }



}
