package com.datastructures.tree;

import java.util.ArrayList;
import java.util.Stack;


//Since all the traversals visit all tree nodes,
// the time complexity of all the traversals you have mentioned is O(n).
//
//For iterative algorithms,
// the space complexity is O(n) as you will be creating a data structure
// preferably queue or stack, for storing n tree nodes.

public class InOrderIterative {
    public ArrayList<Integer> inorderTraversal(BinaryTreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
        BinaryTreeNode currentNode = root;
        boolean done = false;
        while(!done){
            if(currentNode != null){
                s.push(currentNode);
                currentNode = currentNode.getLeft();
            }else{
                if(s.isEmpty())
                    done = true;
                else{
                	currentNode = s.pop();
                    res.add(currentNode.data);
                    currentNode = currentNode.right;
                }
            }
        }
        return res;
    }
}