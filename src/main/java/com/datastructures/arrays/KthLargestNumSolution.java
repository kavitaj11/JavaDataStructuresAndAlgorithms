package com.datastructures.arrays;

import java.util.PriorityQueue;

public class KthLargestNumSolution {
    public static int findKthLargest(int[] nums, int k) {

        return find(nums, k-1, 0, nums.length-1);
    }

    public static int findKthSmallest(int[] nums, int k) {

        return find(nums, nums.length-k, 0, nums.length-1);
    }
    
    static int find(int[] nums, int k, int left, int right) {
        int orgL = left;
        int orgR = right;
        int pivot = (nums[left] + nums[right])/2;
        while(left <= right) {
            while(nums[left] > pivot)
                left++;
            while(nums[right] < pivot)
                right--;
            if (left<=right){
                swap(nums,left,right);
                left++;
                right--;
            }
        }
        if (left<=k)  find(nums, k, left, orgR); 
        if (right >= k) find(nums,k, orgL, right);
        return nums[k];
    }

    public static void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i]= A[j];
        A[j] = temp;
    }


    public static int findKthLargestUsingQuickSort(int[] nums, int k) {
        return quicksort(nums, 0, nums.length - 1, k);
    }
    public static int findKthSmallestUsingQuickSort(int[] nums, int k) {
        return quicksort(nums, 0, nums.length - 1, nums.length-k+1);
    }
    public static int quicksort(int[] nums, int start, int end, int k) {
        int low = start;
        int pivot = nums[end];
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, low, i);
                low++;
            }
        }
        swap(nums, low, end);
        if (low == nums.length - k) {
            return nums[low];
        }
        if (low > nums.length - k) {
            return quicksort(nums, start, low - 1, k);
        }
        else {
            return quicksort(nums, low + 1, end, k);
        }
    }


    public static int findKthLargestUsingPQ(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;

        PriorityQueue<Integer> queue = new PriorityQueue<>(); // min-heap

        for (int i = 0; i < nums.length; i++) {
            if (i < k || nums[i] > queue.peek()) queue.offer(nums[i]);
            if (queue.size() > k) queue.poll();
        }

        return queue.poll();
    }
    public static void main(String[] args){

        int[] arr={2,4,7,3,9,12,5,23,56};
        System.out.println(findKthLargestUsingQuickSort(arr, 2));
        System.out.println(findKthSmallestUsingQuickSort(arr, 4));
        System.out.println(findKthLargestUsingPQ(arr, 2));
        System.out.println(findKthSmallest(arr, 2));
    }
}