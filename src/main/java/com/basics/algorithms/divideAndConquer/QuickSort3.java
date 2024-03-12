package com.basics.algorithms.divideAndConquer;
 

// idea from Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne p.298, 299
public class QuickSort3 {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sort(int[] A) {
        if (A == null || A.length <= 1) return;
        quicksort(A, 0, A.length - 1);
    }

    private void quicksort(int[] A, int start, int end) {
        if (start >= end)   return;

        // choose the middle element as the pivot
        int mid = start + (end - start) / 2;
        int pivot = A[mid];

        // move pivot to the front
        swap(A, start, mid);

        // lt: index to store next element < pivot
        // gt: index to store next element > pivot
        int lt = start, gt = end, i = start + 1;
        // 坑1: i <= gt not i <= end
        while (i <= gt) {
            // move elements < pivot to the front
            if (A[i] < pivot)    swap(A, i++, lt++);
            // NOTE: don't move i, as we don't know whether value of the element
            // from the end that got swap to current i and we need to check it
            // 坑2: i does not immediately increment
            else if (A[i] > pivot)    swap(A, i, gt--);
            // keep elements == pivot on the same positition
            else    i++;
        }

        // Invariant: A[start .. lt - 1] < A[lt .. gt] = pivot < A[gt + 1 .. end]
        quicksort(A, start, lt - 1);
        quicksort(A, gt + 1, end);
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
