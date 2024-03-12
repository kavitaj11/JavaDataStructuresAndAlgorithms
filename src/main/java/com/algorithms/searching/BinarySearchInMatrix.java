package com.algorithms.searching;
//Sorted Matrix Search
//Given a M*N matrix in which each row and each column is sorted in ascending order,
// write a method to find an element in matrix.

public class BinarySearchInMatrix {

//Solution#1: Naive Solution
//do binary search on every row to find the element
//complexity: O(Mlog(N)), since there are M rows and it takes O(Log(N) time to earch each one
     class BinarySearchInMatrixSol1 {
        boolean findElement(int[][] matrix, int elm) {
            int row = 0;
            int col = matrix[0].length - 1;
            while (row < matrix.length && col >= 0) {
                if (matrix[row][col] == elm) {
                    return true;
                } else if (matrix[row][col] > elm) {
                    col--;
                } else {
                    row++;
                }
            }
            return false;
        }
    }

//Solution#2: Binary Search
//do binary search on every row to find the element

     class BinarySearchInMatrixSol2 {
        Coordinate findElement (int[][] matrix, int x){
            Coordinate origin= new Coordinate(0,0);
            Coordinate dest= new Coordinate(matrix.length-1, matrix[0].length-1);
            return findElement(matrix, origin, dest, x);
        }
        Coordinate findElement(int[][] matrix, Coordinate origin, Coordinate dest, int x) {
            if (!origin.inbounds(matrix) || !dest.inbounds(matrix)) {
                return null;
            }
            if (matrix[origin.row][origin.column] == x) {
                return origin;
            } else if (!origin.isBefore(dest)) {
                return null;
            }

            //Set the start of diagonal and end to the end of the diagonal.
            //Since the grid may not be square, the end of the diagonal may not equal dist.
            Coordinate start = (Coordinate) origin.clone();
            int diagDist = Math.min(dest.row - origin.row, dest.column - origin.column);
            Coordinate end = new Coordinate(start.row + diagDist, start.column + diagDist);
            Coordinate p = new Coordinate(0, 0);

            //Do binary search on the diagonal, looking for the first element
            while (start.isBefore(end)) {
                p.setToAverage(start, end);
                if (x > matrix[p.row][p.column]) {
                    start.row = p.row + 1;
                    start.column = p.column + 1;
                } else {
                    end.row = p.row - 1;
                    end.column = p.column - 1;
                }
            }

            //Split the grid into quadrants. Search the bottom left and top right
            return partitionAndSearch(matrix, origin, dest, start, x);
        }

        Coordinate partitionAndSearch(int[][] matrix, Coordinate origin, Coordinate dest, Coordinate pivote, int x){
            Coordinate lowerLeftOrigin= new Coordinate(pivote.row, origin.column);
            Coordinate lowerLeftDest=new Coordinate(dest.row, pivote.column-1);
            Coordinate upperRightOrigin=new Coordinate(origin.row, pivote.column);
            Coordinate upperRightDest=new Coordinate(pivote.row-1, dest.column);

            Coordinate lowerleft=findElement(matrix, lowerLeftOrigin, lowerLeftDest, x);
            if(lowerleft==null){
                return findElement(matrix, upperRightOrigin, upperRightDest, x);
            }
            return lowerleft;
        }


    }

    public class Coordinate{
         public int row, column;
         public Coordinate(int r, int c){
             row=r;
             column=c;
         }
         public boolean inbounds(int[][] matrix) {
             return row >= 0 && column >= 0 && row < matrix.length && column < matrix[0].length;
         }

         public boolean isBefore(Coordinate p){
             return row<p.row && column<p.column;
         }

         public Object clone(){
             return new Coordinate(row, column);
         }

         public void setToAverage(Coordinate min, Coordinate max) {
             row = (min.row + max.row) / 2;
             column = (min.column + max.column) / 2;
         }
     }


}