package com.datastructures.tree;

import java.util.LinkedList;
import java.util.Queue;

public class DeepestNode {
	// The last node processed from queue in level order is the deepest node in binary tree.
	public BinaryTreeNode deepestNodeinBinaryTree(BinaryTreeNode root) {
		BinaryTreeNode tmp = null;
    	if(root == null)
            return null;
        Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
        	tmp = q.poll();
            if(tmp.getLeft() != null)
                q.offer(tmp.getLeft());
            if(tmp.right != null)
                q.offer(tmp.right);
        }
		return tmp;
	}
}
