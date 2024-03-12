package com.datastructures.binarySerachTree;

import java.util.ArrayDeque;
import java.util.Queue;
 
// A class to store a binary tree node
class TNode{
    int data;
    TNode left, right;
    TNode(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

public class MeanHeapBinaryTree {
    // Function to check if a given binary tree is a min-heap or not
    public static boolean isHeap(TNode root) {
        // create an empty queue and enqueue the root node
        Queue<TNode> queue = new ArrayDeque<>();
        queue.add(root);
        // take a boolean flag, which becomes true when an empty left or right
        // child is seen for a node
        boolean isNullSeen = false;
 
        // loop till queue is empty
        while (!queue.isEmpty()) {
            // process front node in the queue
            TNode curr = queue.poll();
            // left child is non-empty
            if (curr.left != null) {
                // if either heap property is violated
                if (isNullSeen || curr.left.data <= curr.data) {
                    return false;
                }
                // enqueue left child
                queue.add(curr.left);
            }
            // left child is empty
            else {
                isNullSeen = true;
            }
 
            // right child is non-empty
            if (curr.right != null) {
                // if either heap property is violated
                if (isNullSeen || curr.right.data <= curr.data) {
                    return false;
                }
 
                // enqueue left child
                queue.add(curr.right);
            }
            // right child is empty
            else {
                isNullSeen = true;
            }
        }
        // we reach here only when the given binary tree is a min-heap
        return true;
    }
 
    public static void main(String[] args) {
        /* Construct the following tree
                   2
                 /   \
                /     \
               3       4
              / \     / \
             /   \   /   \
            5     6 8    10
        */

        TNode root = new TNode(2);
        root.left = new TNode(3);
        root.right = new TNode(4);
        root.left.left = new TNode(5);
        root.left.right = new TNode(6);
        root.right.left = new TNode(8);
        root.right.right = new TNode(10);
 
        if (isHeap(root)) {
            System.out.print("The given binary tree is a min-heap");
        }
        else {
            System.out.print("The given binary tree is not a min-heap");
        }
    }
}


