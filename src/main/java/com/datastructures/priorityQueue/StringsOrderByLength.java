package com.datastructures.priorityQueue;

import java.util.*;
 
public class StringsOrderByLength {
    public static void main(String[] args) {
        // A custom comparator that compares two Strings by their length.
        Comparator<String> customComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        };
        // Create a Priority Queue with a custom Comparator
        PriorityQueue<String> colorsPriorityQueue = new PriorityQueue<>(customComparator);
 
        // Add items to a Priority Queue
        colorsPriorityQueue.add("Red");
        colorsPriorityQueue.add("Green");
        colorsPriorityQueue.add("Blue");
        colorsPriorityQueue.add("Cyan");
        colorsPriorityQueue.add("Magenta");
        colorsPriorityQueue.add("Yellow");
 
// Printing all elements 
        System.out.println("\nThe PriorityQueue elements with custom Comparator:"); 
        Iterator iter1 = colorsPriorityQueue.iterator(); 
        while (iter1.hasNext()) 
            System.out.print(iter1.next() + " "); 
    }
}