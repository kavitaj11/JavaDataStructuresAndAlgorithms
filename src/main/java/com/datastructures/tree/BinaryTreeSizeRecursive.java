package com.datastructures.tree;

public class BinaryTreeSizeRecursive {
	// Returns the total number of nodes in this binary tree (include the root in the count).
	public int size(BinaryTreeNode root) {
		int leftCount = root.left == null ? 0 : size(root.left);
		int rightCount = root.right == null ? 0 : size(root.right);
		return 1 + leftCount + rightCount;
	}
}
