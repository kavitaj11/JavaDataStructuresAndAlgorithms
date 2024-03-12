package com.datastructures.binarySerachTree;

//Given the root to a Binary Search Tree and a node value "k",
// write a function to find the ancestor of that node.

/*
This solution conducts a search for k in the BST until a null node or k itself is reached.
If k is reached, the ancestors are returned; otherwise, an empty string is returned.

Time Complexity #
The time complexity of this solution is O(h), where h is the height,
since a path from the root to k is traced.
Therefore, worst-case time complexity would be O(n), for a skewed tree.
 */
class IterativeAncesstors
{ 
	// Iterative Function to print all ancestors of a given key 
	public static String findAncestors(Node root, int k) { 
		
		String result = ""; 
		Node tempNode = root; 
		while(tempNode != null && tempNode.getData() != k){ 
			result = result + tempNode.getData() + ","; 
			if(k <= tempNode.getData()){ 
				tempNode = tempNode.getLeftChild(); 
			} else{ 
				tempNode = tempNode.getRightChild(); 
			} 
		} 
		if(tempNode == null){ 
			return ""; 
		} 
		return result; 
	}

	// Driver code 
	public static void main(String[] args) 
	{ 		
		binarySearchTree tree = new binarySearchTree(); 		
		/* Construct a binary tree like this
				6
			   / \
			  4   9
			 / \  |  \
			2	5 8	  12
					  / \
					 10  14 
		*/
		tree.add(6); 
		tree.add(4); 
		tree.add(9); 
		tree.add(2); 
		tree.add(5); 
		tree.add(8); 
		tree.add(8); 
		tree.add(12); 
		tree.add(10); 
		tree.add(14); 

		int key = 10; 
		System.out.print(key + " --> ");
		System.out.print(findAncestors(tree.getRoot(), key)); 

		System.out.println();
		key = 5; 
		System.out.print(key + " --> ");
		System.out.print(findAncestors(tree.getRoot(), key)); 
	} 
} 