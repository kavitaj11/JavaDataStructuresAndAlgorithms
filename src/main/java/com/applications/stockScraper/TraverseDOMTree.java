package com.applications.stockScraper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


//Time complexity #
//The time complexity of the above algorithm will be O(n). This is because we traverse each node once.
//
//Space complexity #
//The space complexity of the above algorithm will be O(n)
// because we need to return a list containing the level order traversal.
// We will also need O(n) space for the queue. Since we can have a maximum of n - 1 nodes at any level
// (this can happen only at the lowest level), we will need O(n) space to store them in the queue.

class TreeNode {
  String val;
  List<TreeNode> children;

  TreeNode(String x) {
    val = x;
    children = new ArrayList<>();
  }
};

public class TraverseDOMTree {
  public static List<List<String>> traverse(TreeNode root) {
    List<List<String>> result = new ArrayList<List<String>>();
    if (root == null)
      return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      List<String> currentLevel = new ArrayList<>(levelSize);
      for (int i = 0; i < levelSize; i++) {
        TreeNode currentNode = queue.poll();
        // add the node to the current level
        currentLevel.add(currentNode.val);
        // insert the children of current node in the queue
        queue.addAll(currentNode.children);

        // if (currentNode.left != null)
        //   queue.offer(currentNode.left);
        // if (currentNode.right != null)
        //   queue.offer(currentNode.right);
      }
      result.add(currentLevel);
    }

    return result;
  }

  public static void main(String[] args) {
    // Driver Code

    TreeNode root = new TreeNode("body");
    (root.children).add(new TreeNode("div"));
    (root.children).add(new TreeNode("h1"));
    (root.children).add(new TreeNode("div"));
    (root.children.get(0).children).add(new TreeNode("h2"));
    (root.children.get(0).children.get(0).children).add(new TreeNode("ul"));
    (root.children.get(0).children).add(new TreeNode("h3"));
    (root.children.get(0).children.get(1).children).add(new TreeNode("a"));
    (root.children.get(0).children.get(1).children).add(new TreeNode("p"));
    (root.children.get(0).children.get(1).children).add(new TreeNode("table"));
    (root.children.get(2).children).add(new TreeNode("p"));
    (root.children.get(2).children).add(new TreeNode("a"));
    (root.children.get(2).children).add(new TreeNode("p"));

    List<List<String>> result = traverse(root);
    System.out.println("Web nodes at each level: " + result);
  }
}
