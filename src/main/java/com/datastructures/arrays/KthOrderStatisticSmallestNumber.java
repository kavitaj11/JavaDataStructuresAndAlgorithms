package com.datastructures.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

/*Find the kth Smallest element in an unsorted array.
 Note that it is the kth largest element in the sorted order, not the kth distinct element.
 */
public class KthOrderStatisticSmallestNumber {
    
    public static void findKthSmallest(int[] nums, int k) {
        System.out.println(sortSolution(nums,k));
        System.out.println(maxHeapSolution(nums,k));
        System.out.println(quickSelectSolution(nums,k));
    }
    // O(n log n) runtime, O(1) memory
    private static int sortSolution(int[] nums, int k){
        if(nums==null || nums.length==0)return 0;
        Arrays.sort(nums);
        return nums[k-1];
    }
    // O(n log k) runtime, O(k) memory
    private static int maxHeapSolution(int[] nums, int k){
        if(nums==null || nums.length==0) return 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);
        // put first k numbers in the max heap
        for (int i = 0; i < k; i++)
            maxHeap.add(nums[i]);

        // go through the remaining numbers of the array, if the number from the array is smaller than the
        // top (biggest) number of the heap, remove the top number from heap and add the number from array
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(nums[i]);
            }
        }

        // the root of the heap has the Kth smallest number
        return maxHeap.peek();
    }
    
    // O(n)average O(n^2) worst  runtime, O(1) memory
    // Quick select is quicksort algorithm without sorting complete array
    // but just finding correct positions of pivots
    // to find answer ( basically instead of sorting left and right halves,
    // just scrap one half which is not needed)
    private static int quickSelectSolution(int[] nums, int k){
        if(nums==null || nums.length==0)return 0;
        // index from start
        k = k-1;
        int low=0,high=nums.length-1;
        int pivot;
        while(low<high){
            // get pivot
            pivot = partition(nums,low,high);
            // answer lies to right
            if(pivot<k) low = pivot+1;
            // answer lies to left
            else if(pivot>k) high = pivot-1;
            // answer found
            else return nums[pivot];
        }
        return nums[low];
    }
    private static int partition(int[] nums, int l, int h) {
        // Place elements smaller than pivot to left and then place pivot
        int pivot = h;
        for(int i=l;i<h;i++){
            if(nums[i]<=nums[pivot]){
                swap(nums,l,i);
                l++;
            }
        }
        swap(nums,l,h);
        return l;
    }

    private static void swap(int[] a, int i, int j){
        int temp = a[j];
        a[j]=a[i];
        a[i]=temp;
    }


    public static void main(String[] args){
        int[] arr={2,4,7,3,9,12,5,23,56};
         findKthSmallest(arr, 5);
    }
}
