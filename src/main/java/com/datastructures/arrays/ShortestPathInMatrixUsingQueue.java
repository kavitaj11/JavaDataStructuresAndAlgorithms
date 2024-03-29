package com.datastructures.arrays;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInMatrixUsingQueue {
   //output shortest path length between two points in a matrix
   private static int[][] move = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

   private static int shortestPath3(int[][] matrix, int x1, int y1, int x2, int y2) {
       if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
           return -1;
       }
       int m = matrix.length;
       int n = matrix[0].length;
       if (!isValid(matrix, m, n, x1, y1) || !isValid(matrix, m, n, x2, y2)) {
           return -1;
       }
       Queue<Integer> queue = new LinkedList<>();
       boolean[][] visited = new boolean[m][n];
       int res = 0;
       queue.offer(x1 * n + y1);
       visited[x1][y1] = true;//if we can modify the board, use matrix[x1][y1] = 1;
       while (!queue.isEmpty()) {
           int size = queue.size();
           for (int i = 0; i < size; i++) {
               int key = queue.poll();
               int x = key / n;
               int y = key % n;
               if (x == x2 && y == y2) {
                   return res;
               }
               for (int k = 0; k < move.length; k++) {
                   int nextX = x + move[k][0];
                   int nextY = y + move[k][1];
                   if (isValid(matrix, m, n, nextX, nextY) && !visited[nextX][nextY]) {//matrix[nextX][nextY] != 1
                       queue.offer(nextX * n + nextY);
                       visited[nextX][nextY] = true;//matrix[nextX][nextY] = 1
                   }
               }
           }
           res++;
       }
       return -1;
   }

   private static boolean isValid(int[][] matrix, int m, int n, int i, int j) {
       if (i < 0 || i >= m || j < 0 || j >= n) {
           return false;
       }
       return true;
   }

   public static void main(String[] args){

       int[][] test = new int[][]{{1, 7}, {4, 8}, {7, 1}, {0, -1}};
       System.out.println(shortestPath3(test, 1, 1, 3,1));
   }
}
