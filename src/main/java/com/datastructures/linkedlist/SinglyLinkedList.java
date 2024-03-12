package com.datastructures.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class SinglyLinkedList<T> {

    // Checks whether the value x is present
    // in linked list
    public boolean search(ListNode<T> head, T x) {
        // Base case
        if (head == null)
            return false;
        // If key is present in current node,
        // return true
        if (head.getData() == x)
            return true;

        // Recur for remaining list
        return search(head.getNext(), x);
    }

    public ListNode<T> getNthNodeFromEnd(ListNode<T> head, int n) {
        int counter = 0;

        if(head != null) {
            getNthNodeFromEnd(head.getNext(), n);
            counter++;
            if(n==counter){
                return head;
            }
        }
        return null;
    }


    public ListNode reverseListIterative(ListNode head) {
        //initially Current is head
        ListNode current = head;
        //initially previous is null
        ListNode prev = null;
        while (current != null) {
            //Save the next node
            ListNode next = current.getNext();
            //Make current node points to the previous
            current.setNext(prev);
            //update previous
            prev = current;
            //update current
            current = next;
        }
        return prev;
    }

    // using a temporary buffer O(n)
    public void removeDuplicates(ListNode<T> head) {
        Map<T, Boolean> mapper = new HashMap<>();
        ListNode<T> curr = head;
        ListNode<T> next;
        while (curr.getNext() != null) {
            next = curr.getNext();
            if(mapper.get(next.getData()) != null) {
                // already seen it, so delete
                curr.setNext(next.getNext());
            } else {
                mapper.put(next.getData(), true);
                curr = curr.getNext();
            }
        }
    }

    private class ListNode<T> {
        private ListNode next;
        private T data;

        // Creates an empty node.
        public ListNode() {
        }

        // Creates a node storing the specified data.
        public ListNode(T data) {
            next = null;
            this.data = data;
        }

        // Returns the node that follows this one.
        public ListNode<T> getNext() {
            return next;
        }

        // Sets the node that follows this one.
        public void setNext(ListNode node) {
            next = node;
        }

        // Returns the data stored in this node.
        public T getData() {
            return data;
        }

        // Sets the data stored in this node.
        public void setdata(T elem) {
            data = elem;
        }

        // Sets the data stored in this node.
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
}
