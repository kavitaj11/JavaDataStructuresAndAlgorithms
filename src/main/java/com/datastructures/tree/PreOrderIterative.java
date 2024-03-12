package com.datastructures.tree;

import java.util.ArrayList;
import java.util.Stack;

public class PreOrderIterative {
    public ArrayList<Integer> preorderTraversal(BinaryTreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
        Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
        s.push(root);
        while(!s.isEmpty()){
        	BinaryTreeNode tmp = s.pop();
            res.add(tmp.data);
            // pay more attention to this sequence. 
            if(tmp.right != null)
                s.push(tmp.right);
            if(tmp.getLeft() != null)
                s.push(tmp.getLeft());  
        }
        return res;
    }
}