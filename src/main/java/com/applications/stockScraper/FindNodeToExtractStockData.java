package com.applications.stockScraper;

import java.util.*;

//we need to identify which nodes of the website’s DOM tree contain the stock data.
// The data we are looking for is the dates on which a certain stock price went up or down.
// Identifying stock data in arbitrary HTML can be hard, so we’ll use the following technique.
//
// we’ll traverse the DOM tree, assigning a score to nodes on
// how likely they are to be a date or a stock percentage based on the text inside of them.

// After this step, we’ll find two nodes:
// one node with a high date score and one with a high stock percentage score.
// We’ll calculate the LCA(Lowest Common Ancestor) of these two nodes.
// In most cases, the subtree of the LCA node will have all the dates and their respective stock percentages.
// This saves us time for searching the rest of the DOM tree.

//Time complexity #
//The time complexity will be O(n). In the worst case, we might have to visit all the nodes of the n-ary tree.
//
//Space complexity #
//The space complexity will be O(n).
// In the worst case, the stack, the parent pointer dictionary,
// and the ancestor set will each use n space. Also, the height of a skewed n-ary tree could be n.

class DOMTreeNode {
  int val;
  List<DOMTreeNode> children;

  DOMTreeNode(int x) {
    val = x;
    children = new ArrayList<>();
  }
}

class FindNodeToExtractStockData {
  public static int lowestCommonAncestor(DOMTreeNode root, DOMTreeNode a, DOMTreeNode b) {
    Deque<DOMTreeNode> stack = new ArrayDeque<>();

    Map<DOMTreeNode, DOMTreeNode> parent = new HashMap<>();

    parent.put(root, null);
    stack.push(root);

    while (!parent.containsKey(a) || !parent.containsKey(b)) {
      DOMTreeNode node = stack.pop();

      // Save the parent pointers while iterating
      for (DOMTreeNode child : node.children){
        parent.put(child, node);
        stack.push(child);
      }
    }

    Set<DOMTreeNode> ancestors = new HashSet<>();

    while (a != null) {
      ancestors.add(a);
      a = parent.get(a);
    }

    // The first ancestor of b which appears in
    // a's ancestor set() is their lowest common ancestor.
    while (!ancestors.contains(b))
      b = parent.get(b);

    return b.val;
  }

  public static void main(String[] args){
    // Driver Code

    DOMTreeNode root = new DOMTreeNode(1);
    root.children.add(new DOMTreeNode(2));
    root.children.add(new DOMTreeNode(3));
    root.children.add(new DOMTreeNode(4));
    root.children.get(0).children.add(new DOMTreeNode(5));
    root.children.get(0).children.get(0).children.add(new DOMTreeNode(10));
    root.children.get(0).children.add(new DOMTreeNode(6));
    root.children.get(0).children.get(1).children.add(new DOMTreeNode(11));
    root.children.get(0).children.get(1).children.add(new DOMTreeNode(12));
    root.children.get(0).children.get(1).children.add(new DOMTreeNode(13));
    root.children.get(2).children.add(new DOMTreeNode(7));
    root.children.get(2).children.add(new DOMTreeNode(8));
    root.children.get(2).children.add(new DOMTreeNode(9));

    DOMTreeNode a = root.children.get(0).children.get(1).children.get(2);
    DOMTreeNode b = root.children.get(0).children.get(0).children.get(0);
    int LCA = lowestCommonAncestor(root, a, b);
    System.out.print("\"" + LCA + "\"" + " is the lowest common ancestor of the nodes " + "\"" + a.val + "\"" + " and " + "\"" + b.val + "\"");
  }

}

/*
Let’s say that our two identified nodes are a and b.

We can save the parent nodes of each node while traversing the tree.
Then, we can store the parents of one of the nodes, say a, into a set.
As we go from the node b towards the root, the first ancestor of b that we find in the set is the LCA.
We can store the parent pointers in a dictionary for retrieval in constant time.
For backtracking, we can use the set data structure.

Let’s see how we might implement this functionality:

1. First, we’ll traverse the tree starting from the root node.

2. Then, we’ll store the parent of each node in the dictionary until the nodes a and b are not found.

3. If the nodes a and b are found:
    Traverse over the parents/ancestors of node a.
    For each parent node, add it to the ancestors set.

4. Similarly, we will traverse through the ancestors of node b.
   If the ancestor is present in the ancestors set for a,
   this is the first ancestor common in between a and b (while traversing upwards),
   and this is the LCA node.
 */