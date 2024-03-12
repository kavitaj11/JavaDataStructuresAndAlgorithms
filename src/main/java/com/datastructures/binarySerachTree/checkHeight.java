package com.datastructures.binarySerachTree;

//Given the root to a Binary Search Tree, write a function to find the height of the tree.
/*
Here, we return -1 if the given node is None;
then, we call the findHeight() function on the left and right sub-trees
and return the one that has a greater value plus 1.
This method also works for finding the height of a simple binary tree.

Time Complexity #
The time complexity of the code is O(n) as all the nodes of the entire tree have to be traversed.
 */
class checkHeight {
  public static int findHeight(Node root) {
    //Base case, leaf nodes have 0 height
    if (root == null) return -1;
    else {
      return 1 + Math.max(findHeight(root.getLeftChild()),findHeight(root.getRightChild()));
      // Find Height of left subtree right subtree
      // Return greater height value of left or right subtree (plus 1)
    }
  }

  public static void main(String args[]) {

    binarySearchTree bsT = new binarySearchTree();


    bsT.add(6);
    bsT.add(4);
    bsT.add(9);
    bsT.add(5);
    bsT.add(2);
    bsT.add(8);
    bsT.add(12);
    
    System.out.println(findHeight(bsT.getRoot()));

  }
}