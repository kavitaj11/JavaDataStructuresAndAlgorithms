//package com.basics.datastructures.bst;

import java.util.*;
import java.io.*;

public class tree_orders {

    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
	
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeOrders {
		int n;
		int[] key, left, right;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		/**
		 * Steps: 
		 * Start at root vertex 0.
		 * Push all nodes `along the path` from root to left-most into stack.
		 * While stack is not empty, print top.
		 * Push all nodes along the path from its right-subtree to left-most.
		 */
		List<Integer> inOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
			Stack<Integer> stack = new Stack<>();
			pushAll(stack, 0);
			while (! stack.isEmpty()) {
				int top = stack.pop();
				result.add(key[top]);
				top = right[top];  // Move pointer to right subtree
				pushAll(stack, top);
			}
			return result;
		}
		/*Push all nodes on the path root->left-most into stack */
		private void pushAll(Stack<Integer> stack, int root) {
			while (root != -1) {
				stack.push(root);
				root = left[root];
			}
		}

		/**
		 * Steps: 
		 * Push root into stack.
		 * While stack is not empty, pop & print top.
		 * Push its non-null right, left child into stack.
		 */
		List<Integer> preOrder() {
			List<Integer> result = new ArrayList<Integer>();
			Stack<Integer> stack = new Stack<>();
			stack.push(0);
			while (! stack.isEmpty()) {
				int top = stack.pop();
				result.add(key[top]);
				if (right[top] != -1) stack.push(right[top]);
				if (left[top]  != -1) stack.push(left[top]);
			}
			return result;
		}

		/**
		 * Steps:
		 * Push root into stack and while stack is not empty, pop top.
		 * Insert top at the head (reverse-order).
		 * Push its non-null left, right child into stack.
		 */
		List<Integer> postOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
			Stack<Integer> stack = new Stack<>();
			stack.push(0);
			while (! stack.isEmpty()) {
				int top = stack.pop();
				result.add(0, key[top]);
				// BZ: add top's children, instead of root
				if (left[top]  != -1) stack.push(left[top]);
				if (right[top] != -1) stack.push(right[top]);
			}
			return result;
		}
	}



	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
