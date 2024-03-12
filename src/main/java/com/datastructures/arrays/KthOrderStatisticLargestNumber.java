package com.datastructures.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

/*Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
Example 1:
Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthOrderStatisticLargestNumber {
    
    public static void findKthLargest(int[] nums, int k) {
        System.out.println(sortSolution(nums,k));
        System.out.println(minHeapSolution(nums,k));
        System.out.println(quickSelectSolution(nums,k));
    }
    // O(n log n) runtime, O(1) memory
    private static int sortSolution(int[] nums, int k){
        if(nums==null || nums.length==0)return 0;
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
    // O(n log k) runtime, O(k) memory
    private static int minHeapSolution(int[] nums, int k){
        if(nums==null || nums.length==0)return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
    
    // O(n)average O(n^2) worst  runtime, O(1) memory
    // Quick select is quicksort algorithm without sorting complete array
    // but just finding correct positions of pivots
    // to find answer ( basically instead of sorting left and right halves,
    // just scrap one half which is not needed)
    private static int quickSelectSolution(int[] nums, int k){
        if(nums==null || nums.length==0)return 0;
        // index from start
        k = nums.length-k;
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
         findKthLargest(arr, 5);
    }
}
