package com.algorithms.sorting;

public class BubbleSort {

    //Bubble Sort algorithm:
    // in first pass, we move largest element to end (Assuming sorting in increasing order).
    // In second pass, we move second largest element to second last position
    // and so on.

    private static int bubbleSort(int[] a, int n) {
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            // Track number of elements swapped during a single array traversal
            int numberOfSwaps = 0;

            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    swap(a, j , j + 1);
                    numberOfSwaps++;
                }
            }
            cnt += numberOfSwaps;

            // If no elements were swapped during a traversal, array is sorted
            if (numberOfSwaps == 0) {
                break;
            }
        }

        return cnt;
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /*Recursion Idea:
    *Base Case: If array size is 1, return.
    *Do One Pass of normal Bubble Sort. This pass fixes last element of current subarray.
    *Recur for all elements except last of current subarray.
    */
    // A recursive function to implement bubble sort
    static void bubbleSortRecursive(int arr[], int n)
    {
        // Base case
        if (n == 1)
            return;

        // One pass of bubble sort. After
        // this pass, the largest element
        // is moved (or bubbled) to end.
        for (int i=0; i<n-1; i++)
            if (arr[i] > arr[i+1])
            {
                // swap arr[i], arr[i+1]
                swap(arr, i , i + 1);
            }

        // Largest element is fixed,
        // recur for remaining array
        bubbleSort(arr, n-1);
    }



    public static void main(String[] args) {
        int[] a={5,8,4,6,2,9,10};
        int n=5;
        int numberOfSwaps = bubbleSort(a, n);
        System.out.println("Array is sorted in "+ numberOfSwaps +" swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[n-1]);
    }
}
