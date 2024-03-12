package com.datastructures.tree;

public class PreOrderRecursive {
	public void PreOrder(BinaryTreeNode root){
		if(root != null) {
			System.out.println(root.data);		
			PreOrder(root.getLeft());
			PreOrder(root.right);
		}
	}
}