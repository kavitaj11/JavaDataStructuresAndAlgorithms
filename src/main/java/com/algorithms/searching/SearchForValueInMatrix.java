package com.algorithms.searching;
/*
Problem: We are given a 2D array where all elements in any individual row or column are sorted. 
In such a matrix, we have to search or find the position of, a given key. 

Solution: One simple solution is to scan the whole 2D array for the key in O(mn)O(mn) time. 
However, there are better solutions with less time complexity that use the sorted property of matrix.
We can use a binary search on each row to find if the key exists in a row or not. 
The time complexity of this solution is O(m * log n)O(m∗logn). 
Though it looks good, we have an even better solution.

Here is the algorithm that we are going to use.
 We start from the upper right corner of the matrix and compare its value with the key.
If they are equal, we have found the position of the key. 
If the key is smaller than the current element, we move one position to the left. 
If the key is larger than the current element, we move one position down. 
The reason for doing so is because the matrix is sorted, moving left would result in lower values than the current value 
and moving down would result in higher values than the current value. 
We continue this process until either we find the element or go out of the boundary of the matrix (which indicates that the key does not exist).
This solution will visit mm + nn elements at most in the matrix, giving us a time complexity of O(m + n)O(m+n). 
Also note that we cannot do our searching procedure from the top left or the bottom right corner since in both cases, 
the keys at either side of that corner are increasing or decreasing respectively. 
Note that we can start the search from the bottom left corner as well, which would result in similar results as starting from the top right.

Search in Matrix using Divide and conquer
Runtime complexity #
The runtime complexity of this solution is O(m+n) where m is the number of rows and n is the number of columns.

Memory complexity #
The memory complexity of this solution is constant, O(1).
*/

class IntPair {
  public int first;
  public int second;
  public IntPair(int first, int second) {
    this.first = first;
    this.second = second;
  }
}
class SearchForValueInMatrix{
  public static IntPair searchInMatrix(int matrix[][], int value) {
     int M = matrix.length; //rows
    int N = matrix[0].length; // columns

    // Let's start searching from top right.
    // Alternatively, searching from bottom left
    // i.e. matrix[M-1][0] can also work.

    int i = 0, j = N-1;

    while (i < M && j >= 0) {
      if (matrix[i][j] == value) {
        return new IntPair(i, j);
      }
      else if (value < matrix[i][j]) {
        // search left
        --j;
      }
      else {
        // search down.
        ++i;
      }
    }  

    return new IntPair(-1, -1);

  }
  public static void main(String[] args) {
    int [] [] matrix = new int [] [] {
      {2, 4, 9, 13, 15},
      {3, 5, 11, 18, 22},
      {6, 8, 16, 21, 28},
      {9, 11, 20, 25, 31},
    };

    IntPair val_loc = searchInMatrix(matrix, 8);
    System.out.println("Verifying at " + val_loc.first + "," +
            val_loc.second + ": " + matrix[val_loc.first][val_loc.second]);
  }
}  

