package com.datastructures.heap;

import java.util.ArrayDeque;
import java.util.Queue;

/*
Given a binary search tree (BST), efficiently convert it into a mean-heap.
In other words, convert a binary search tree into a complete binary tree
where each node has a higher value than it's parent's value.
 */

// A class to store a binary tree node
class Node {
    int key;
    Node left, right;
    Node(int key) {
        this.key = key;
    }
}

/*
Case 1: BST is a complete binary tree
 */
class ConvertCompleteBSTToMeanHeap {
    // Recursive function to insert a key into a BST
    public static Node insert(Node root, int key) {
        // if the root is null, create a new node and return it
        if (root == null) {
            return new Node(key);
        }

        // if the given key is less than the root node, recur for the left subtree
        if (key < root.key) {
            root.left = insert(root.left, key);
        }
        // if the given key is more than the root node, recur for the right subtree
        else {
            root.right = insert(root.right, key);
        }

        return root;
    }

    // Helper function to perform level order traversal on a binary tree
    public static void printLevelOrderTraversal(Node root) {
        // base case: empty tree
        if (root == null) {
            return;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            while (n-- > 0) {
                Node front = q.poll();
                System.out.print(front.key + " ");
                if (front.left != null) {
                    q.add(front.left);
                }
                if (front.right != null) {
                    q.add(front.right);
                }
            }
            System.out.println();
        }
    }

    // Function to perform inorder traversal on a given binary tree and
    // enqueue all nodes (in encountered order)
    public static void inorder(Node root, Queue<Integer> keys) {
        if (root == null) {
            return;
        }
        inorder(root.left, keys);
        keys.add(root.key);
        inorder(root.right, keys);
    }

    // Function to perform preorder traversal on a given binary tree.
    // Assign each encountered node with the next key from the queue
    public static void preorder(Node root, Queue<Integer> keys) {
        // base case: empty tree
        if (root == null) {
            return;
        }
        // replace the root's key value with the next key from the queue
        root.key = keys.poll();

        // process left subtree
        preorder(root.left, keys);

        // process right subtree
        preorder(root.right, keys);
    }

    // Function to convert a BST into a min-heap
    public static void convert(Node root) {
        // maintain a queue to store inorder traversal on the tree
        Queue<Integer> keys = new ArrayDeque<>();

        // fill the queue in an inorder fashion
        inorder(root, keys);

        // traverse tree in preorder fashion, and for each encountered node,
        // dequeue a key and assign it to the node
        preorder(root, keys);
    }

    public static void main(String[] args) {
        int[] keys = { 5, 3, 2, 4, 8, 6, 10 };

        /* Construct the following BST
                   5
                 /   \
                /     \
               3       8
              / \     / \
             /   \   /   \
            2     4 6    10
        */

        Node root = null;
        for (int key: keys) {
            root = insert(root, key);
        }

        convert(root);
        printLevelOrderTraversal(root);
    }
}



class Convert_IncompleteBST_To_MeanHeap {
    // Recursive function to insert a key into a BST
    public static Node insert(Node root, int key) {
        // if the root is null, create a new node and return it
        if (root == null) {
            return new Node(key);
        }

        // if the given key is less than the root node, recur for the left subtree
        if (key < root.key) {
            root.left = insert(root.left, key);
        }
        // if the given key is more than the root node, recur for the right subtree
        else {
            root.right = insert(root.right, key);
        }

        return root;
    }

    // Helper function to perform level order traversal on a binary tree
    public static void printLevelOrderTraversal(Node root)
    {
        // base case: empty tree
        if (root == null) {
            return;
        }

        Queue<Node> q = new ArrayDeque<>();
        q.add(root);

        while (!q.isEmpty())
        {
            int n = q.size();
            while (n-- > 0)
            {
                Node front = q.poll();
                System.out.print(front.key + " ");

                if (front.left != null) {
                    q.add(front.left);
                }

                if (front.right != null) {
                    q.add(front.right);
                }
            }

            System.out.println();
        }
    }

    // Insert a tree node at the front of a linked list
    public static Node push(Node node, Node head)
    {
        // initialize head pointer of the linked list
        if (head == null)
        {
            head = node;
            head.right = null;
            return head;
        }

        // update the right child of the node to point to the current head of the list
        node.right = head;

        // update head pointer to point to the given node
        head = node;

        return head;
    }

    // Function to convert a BST into a sorted linked list
    public static Node convertTreeToList(Node root, Node head)
    {
        // base case: empty tree
        if (root == null) {
            return head;
        }

        // process right child first
        head = convertTreeToList(root.right, head);

        // Insert the current root node at the front of the linked list
        head = push(root, head);

        // process left child
        head = convertTreeToList(root.left, head);

        // set left child as null
        // (since the right child of the linked list acts as a next pointer)
        root.left = null;

        return head;
    }

    // Function to convert a sorted linked list into a min-heap
    public static Node convertListToMinHeap(Node heapRef, Node head)
    {
        // base case: empty linked list
        if (head == null) {
            return heapRef;
        }

        // construct a queue to store the parent nodes
        Queue<Node> q = new ArrayDeque<>();

        // root node of the min-heap would be the front node in the sorted list
        heapRef = head;

        // enqueue root node
        q.add(heapRef);

        // advance the linked list to the next node
        head = head.right;

        // unlink the root node from the unprocessed linked list by
        // setting its right child as null
        heapRef.right = null;

        // loop till the end of the list is reached
        while (head != null)
        {
            // dequeue next node
            Node parent = q.poll();

            /* Assign the next node of the linked list to the left child of the
               parent node */

            // process next node in the linked list
            Node next = head;

            // enqueue next node
            q.add(next);

            // advance the linked list to the next node
            head = head.right;

            // unlink the next node from the unprocessed linked list by
            // setting its right child as null
            next.right = null;

            // set the next node as the left child of the parent
            parent.left = next;


            /* Assign the next node of the linked list to the right child of the
               parent node (if any) */

            if (head != null)
            {
                // process next node in the linked list
                next = head;

                // enqueue next node
                q.add(next);

                // advance the linked list to the next node
                head = head.right;

                // unlink the next node from the unprocessed linked list by
                // setting its right child as null
                next.right = null;

                // set the next node as the right child of the parent
                parent.right = next;
            }
        }

        return heapRef;
    }

    // Function to convert a BST into a min-heap without using
    // any auxiliary space
    public static Node convert(Node root)
    {
        // points to the head of the linked list
        Node head = null;

        // Convert the BST into a sorted linked list
        head = convertTreeToList(root, head);

        // Convert the sorted list into a min-heap
        root = convertListToMinHeap(root, head);

        return root;
    }

    public static void main(String[] args)
    {
        int[] keys = { 5, 3, 2, 4, 8, 10 };

        /* Construct the following BST
                   5
                 /   \
                /     \
               3       8
              / \       \
             /   \       \
            2     4      10
        */

        Node root = null;
        for (int key: keys) {
            root = insert(root, key);
        }

        root = convert(root);
        printLevelOrderTraversal(root);
    }
}
