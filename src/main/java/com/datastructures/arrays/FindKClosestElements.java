package com.datastructures.arrays;

import java.util.*;

/*
Given a sorted number array and two integers ‘K’ and ‘X’,
find ‘K’ closest numbers to ‘X’ in the array.
 Return the numbers in the sorted order. ‘X’ is not necessarily present in the array.

 The time complexity of the above algorithm is O(logN + K*logK)
 We need O(logN) for Binary Search
 and O(K*logK) to insert the numbers in the Min Heap, as well as to sort the output array.
 */
class Entry {
    int key; int value;
    public Entry(int key, int value) {
        this.key = key;  this.value = value;
    }
}
public class FindKClosestElements {

    public static List<Integer> findClosestElements(int[] arr, int K, Integer X) {
            int index = binarySearch(arr, X);
            int low = index - K, high = index + K;
            low = Math.max(low, 0); // 'low' should not be less than zero
            high = Math.min(high, arr.length - 1); // 'high' should not be greater the size of the array
            PriorityQueue<Entry> minHeap = new PriorityQueue<>((n1, n2) -> n1.key - n2.key);
            // add all candidate elements to the min heap, sorted by their absolute difference from 'X'
            for (int i = low; i <= high; i++)
                minHeap.add(new Entry(Math.abs(arr[i] - X), i));
            // we need the top 'K' elements having smallest difference from 'X'
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < K; i++) result.add(arr[minHeap.poll().value]);
            Collections.sort(result);
            return result;
        }

        private static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (low > 0) {
            return low - 1;
        }
        return low;
    }

    public static void main(String[] args){
        int[] arr={2,4,7,3,9,12,5,23,56};
        System.out.println(findClosestElements(arr, 3, 50));
    }


}
