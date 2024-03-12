package com.datastructures.tree;

import java.util.ArrayList;
import java.util.Stack;

public class PostOrderIterative {
	   public ArrayList<Integer> postorderTraversal(BinaryTreeNode root) {
	        ArrayList<Integer> res = new ArrayList<Integer>();
	        if(root == null)
	            return res;
	        Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
	        s.push(root);
	        BinaryTreeNode prev = null;
	        while(!s.isEmpty()){
	        	BinaryTreeNode curr = s.peek();
	            if(prev == null || prev.getLeft() == curr || prev.right == curr){
	                // traverse from top to bottom, and if curr has left child or right child, 
	            	// push into the stack; otherwise, pop out. 
	                if(curr.getLeft() != null)
	                    s.push(curr.getLeft());
	                else if(curr.right != null)
	                    s.push(curr.right);
	            }else if(curr.getLeft() == prev){
	                if(curr.right != null)
	                    s.push(curr.right);
	            }else{
	                res.add(curr.data);
	                s.pop();
	            }
	            prev = curr;
	        }
	        return res;
	    }
}