/*
package com.datastructures.linkedlist;*/
/*
Explanation #
This is, perhaps, the most efficient way of removing duplicates from a linked list.

Every node we traverse is added to the visitedNodes set. If we reach a node that already exists in the set, it must be a duplicate.

prevNode is used to keep track of the preceding node. This allows us to easily manipulate the previous and next nodes during the deletion of our current node.

Time Complexity #
This is a linear algorithm, hence, the time complexity is O(n)O(n).
*//*


import com.datastructures.linkedlist.SinglyLinkedList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


class RemoveDuplicatesUsingHashSet {
    public static <V> void removeDuplicatesWithHashing(SinglyLinkedList<V> list) {
        SinglyLinkedList<V>.Node current = list.getHeadNode();
        SinglyLinkedList<V>.Node prevNode = list.getHeadNode();
        //will store all the elements that we observe once
        HashSet<V> visitedNodes = new HashSet<V>();

        if (!list.isEmpty() && current.nextNode != null) {
            while (current != null) {
                //check if already visited then delete this node
                if (visitedNodes.contains(current.data)) {
                    //deleting the node by undating the pointer of previous node
                    prevNode.nextNode = current.nextNode;
                    current = current.nextNode;
                } else {
                    //if node was not already visited then add it to the visited set
                    visitedNodes.add(current.data);
                    //moving on to next element in the list
                    prevNode = current;
                    current = current.nextNode;
                }
            }
        }
    }
    public static void main(String args[]) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(); // created linked list

        for(int i = 1; i <= 8; i++) {
            list.insertAtHead(i);      // inserting data in list
        }
        //inserting duplicates
        list.insertAtHead(2);
        list.insertAtHead(4);
        list.insertAtHead(1);
        
        System.out.println("List before deleting duplicates from list :");
        list.printList();
        removeDuplicatesWithHashing(list); // calling removeDuplicatesWithHashing function
        System.out.println("List after deleting duplicates from list :");
        list.printList();
    }


}
*/
