package com.datastructures.tree;

public class SumOfElementsInBinaryTreeRecursive {
	public int addBT(BinaryTreeNode  root) {
		if(root == null) return 0;
		else return(root.getData() + addBT(root.getLeft()) +  addBT(root.getRight()));
	}
}
