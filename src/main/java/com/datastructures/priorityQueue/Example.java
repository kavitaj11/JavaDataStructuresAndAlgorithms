package com.datastructures.priorityQueue;

import java.util.Iterator;
import java.util.PriorityQueue;

/*
Priority Queue implements a queue interface
 and is a special queue where elements are ordered as per natural ordering.
 It does not follow the FIFO order.
  To change the natural ordering of the priority queue, we can use the custom comparator.

Priority queues are mainly used for Printer scheduling, CPU task scheduling, etc.
The heap (min or max) is also implemented using Priority Queues.

Some methods supported are:
add() - Add elements at the tail of queue.
peek() - View the head of the queue without removing it. Returns Null if the queue is empty.
remove() - Removes and returns the head of the queue.
 Returns null if the queue is empty. Throws an exception when queue is empty.
poll() - Removes and returns the head of the queue.

 */
public class Example {
 public static void main(String args[]) {
  //Creating empty priority queue 
  PriorityQueue< String > pq = new PriorityQueue < String > ();

  //Adding items using add() 
  pq.add("C");
  pq.add("C++");
  pq.add("Java");
  pq.add("Python");

  // Printing highest priority element 
  System.out.println("Highest priority element:" + pq.peek());

  // Printing all elements 
  System.out.println("The elements in queue are:");
  Iterator itr = pq.iterator();
  while (itr.hasNext())
   System.out.println(itr.next());

  // Removing the top priority element 
  pq.poll();
  //printing the queue
  System.out.println("After removing an element with poll function:");
  Iterator < String > itr2 = pq.iterator();
  while (itr2.hasNext())
   System.out.println(itr2.next());

  // Removing element using remove() 
  pq.remove("Java");
  System.out.println("After removing an element with remove function:");
  Iterator < String > itr3 = pq.iterator();
  while (itr3.hasNext())
   System.out.println(itr3.next());

  // Check if an element is present using contains() 
  boolean temp = pq.contains("C");
  System.out.println("Priority queue contains C or not?: " + temp);
 }
}