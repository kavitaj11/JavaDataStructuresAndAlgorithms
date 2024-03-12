package com.datastructures.arrays;/*
Simplest approach to find the shortest path in a 2D array would be to use BFS technique in the following way.
Problem:
Given a 2D array with values as ‘S’, ‘D’, ‘1’ and ‘0’.
- S is the Source
- D is the Destination
- 1 marks the valid path
- 0 marks the obstacle
Find the shortest distance from S to D avoiding all the obstacles.
Approach:
Starting from the source ‘S’, add it to the queue
Remove the first node from the queue and mark it visited so that it should not be processed again.
For the node just removed from the queue in step 2, check all the neighboring nodes
If the x & y value of the node is within the values of given matrix and the node has not been marked visited yet, add it to the queue along with the distance of this node from the source ‘S’
repeat steps 2 to 4 until you reach to the node which is equal to ‘D’ (Destination)
If there is a path available from S to D, the distance will be displayed, other wise it will print ‘-1’
*/


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathInMatrixWithObstacles {
	
	 public static void main(String args[])
	    {
	        char[][] matrix = {
	            {'S', '0', '1', '1'},
	            {'1', '1', '0', '1'},
	            {'0', '1', '1', '1'},
	            {'1', '0', 'D', '1'}
	        };
	        
	        int path = pathExists(matrix);
	        
	       System.out.println(path);
	    }

	private static int pathExists(char[][] matrix) {
		
		Node source = new Node(0, 0, 0);
		Queue<Node> queue = new LinkedList<Node>();
		
		queue.add(source);
		
		while(!queue.isEmpty()) {
			Node poped = queue.poll();
			
			if(matrix[poped.x][poped.y] == 'D' ) {
				return poped.distanceFromSource;
			}
			else {
				matrix[poped.x][poped.y]='0';
				
				List<Node> neighbourList = addNeighbours(poped, matrix);
				queue.addAll(neighbourList);
			}	
		}
		return -1;
	}

	private static List<Node> addNeighbours(Node poped, char[][] matrix) {
		
		List<Node> list = new LinkedList<Node>();
		
		if((poped.x-1 >= 0 && poped.x-1 < matrix.length) && matrix[poped.x-1][poped.y] != '0') {
			list.add(new Node(poped.x-1, poped.y, poped.distanceFromSource+1));
		}
		if((poped.x+1 >= 0 && poped.x+1 < matrix.length) && matrix[poped.x+1][poped.y] != '0') {
			list.add(new Node(poped.x+1, poped.y, poped.distanceFromSource+1));
		}
		if((poped.y-1 >= 0 && poped.y-1 < matrix.length) && matrix[poped.x][poped.y-1] != '0') {
			list.add(new Node(poped.x, poped.y-1, poped.distanceFromSource+1));
		}
		if((poped.y+1 >= 0 && poped.y+1 < matrix.length) && matrix[poped.x][poped.y+1] != '0') {
			list.add(new Node(poped.x, poped.y+1, poped.distanceFromSource+1));
		}		
		return list;
	}
}
class Node {
    int x;
    int y;
    int distanceFromSource;
    
    Node(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.distanceFromSource = dis;
    }
}
