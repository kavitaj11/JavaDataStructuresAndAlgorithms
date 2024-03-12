package com.datastructures.priorityQueue;

import java.util.*;

//By default, the priority queue in Java is min Priority queue with natural ordering.
// To make it max,  we have to use a custom comparator
// so that head of the queue returns the greatest element in the queue.

public class MaxPriorityQeueExample{
    public static void main(String args[]){ 
        //declare a PriorityQueue object with custom comparator to generate max PQ
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer lhs, Integer rhs) {
                if (lhs < rhs) return +1;
                if (lhs.equals(rhs)) return 0;
                    return -1;
            }
        });
        //add element to the PriorityQueue
        pq.add(8);  
        pq.add(6);  
        pq.add(4); 
        pq.add(2);  
        pq.add(12);  
        pq.add(10);
        //display the max PriorityQueue
        System.out.println("The max Priority Queue contents:");
        Integer val = null;
        while( (val = pq.poll()) != null) {
            System.out.print(val + " ");
        }
    }  
}