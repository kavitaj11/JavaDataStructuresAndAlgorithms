package com.datastructures.tree;

public class InOrderRecursive {
	public void InOrder(BinaryTreeNode root){
		if(root != null) {
			InOrder(root.getLeft());
			System.out.println(root.data);
			InOrder(root.right);
		}
	}
}