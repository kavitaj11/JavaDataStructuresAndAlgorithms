package com.datastructures.heap;

import java.util.PriorityQueue;

public class KthLargestElementUsingPriorityQueue {

    //Time complexity of n*log(k) is an improvement to Solution using Arrays.sort
    // For Arrays.sort, the time is O(nlog(n)).
    // The problem of this solution is that sorting all elements is not necessary
    // and is a overkill for getting just one element..
    // However, this solution requires O(k) space complexity and
    // it is also maintained k-element heap.
    public static  int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(k);
        for(int i: nums){
            q.offer(i);

            if(q.size()>k){
                q.poll();
            }
        }

        return q.peek();
    }

    public static void main(String[] args){

        int[] arr={2,4,7,3,9,12,5,23,56};
        System.out.println(findKthLargest(arr, 4));
    }
}
