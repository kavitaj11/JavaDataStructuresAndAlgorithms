package com.datastructures.priorityQueue;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;


/*
Time Complexity #
The time complexity of the Insert will be O(logn) because we inserted in the heap.
 The time complexity of the Find Median will be O(1) because we can find the median
 from the top elements of the heaps.

Memory complexity #
The memory complexity will be O(n) because we will be storing all the numbers at any time.
 */
public class MedianOfRunningArray {
    private PriorityQueue<Integer> p1;
    private PriorityQueue<Integer> p2;

    //Constructor
    public MedianOfRunningArray() {
        this.p1 = new PriorityQueue<>(Collections.reverseOrder()); //Max Heap
        this.p2 = new PriorityQueue<>(); //Min Heap
    }
   //Inserting lower half of array to max Heap and upper half to min heap
    public void insert(Integer e) {
        p2.add(e);
        if(p2.size() - p1.size() > 1)
            p1.add(p2.remove());
    }
    // Returns median at any given point
    public Integer median() {
        if(p1.size()==p2.size())
            return (p1.peek() + p2.peek())/2;
        return  p1.size()>p2.size() ? p1.peek() : p2.peek();
    }

    public static void main(String[] args) {
        MedianOfRunningArray p = new MedianOfRunningArray();
        int arr[] = {10, 7, 4, 9, 2, 3, 11, 17, 14};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        for(int i = 0 ; i < 9 ; i++) {
            p.insert(arr[i]);

        }
        System.out.print(p.median() + " ");
    }

}