package com.algorithms.searching;

class SearchMatrix {

 //Solution #1: Brute force #
 /*
 This is a simple linear search of a 2-D matrix.
 We use two for loops to iterate over the entire matrix
 and see if a value equal to the given target is found.
Time complexity #
Since we use two nested for loops, the time complexity is O(n*m),
where n is the number of rows and m is the number of columns.
  */
 public static Object findKeyBruteForce(int[][] matrix, int numberOfRows, int numberOfColumns, int target) {
  for (int i = 0; i < numberOfRows; i++) {
   for (int j = 0; j < numberOfColumns; j++) {
    if (matrix[i][j] == target)
     return true;
   }
  }
  return false;
 }

 //Solution #2: Moving directions #
 /*
  This is an efficient solution that works on the following optimization:
  start from the lowest-left element.
  Check if the target is greater than the current position if so, move to the right,
  else move upwards.
  The matrix is sorted, so we don’t need to search the entire matrix.
  Instead, we optimize our searching in such a way that the whole matrix does not need to be traversed.

  Time complexity #
  Since we traverse the matrix only once and at each step and we eliminate an entire row or column,
  the time complexity is now O(n + m).
  */
 public static Object findKey(int[][] matrix, int numberOfRows, int numberOfColumns, int target) {
  int i = numberOfRows - 1;
  int j = 0;

  while (i >= 0 && j < numberOfColumns) {
   if (matrix[i][j] == target)
    return true;

   if (target > matrix[i][j])
    j++;
   else
    i--;
  }
  return false;
 }

 //Solution #3: Binary search #
  /*
  Here, we just forget about the 2D structure. Since all elements are sorted,
  we just do a binary search imagining a “flattened” version of the array.
  When an array element has to be accessed, convert the 1D index into row and column numbers
  using simple division and remainder formulae.

  Time complexity #
  The time complexity using binary search is O(logn + logm)
   */

  public static Object findKeyUsingBinarySearch(int[][] matrix, int numberOfRows, int numberOfColumns, int target) {
   int min = 0;
   int max = numberOfRows * numberOfColumns - 1;

   while (min <= max)
   {
    int middle = (min + max) / 2;
    int row = middle / numberOfColumns;
    int col = middle % numberOfColumns;
    if (target < matrix[row][col])
    {
     max = middle - 1;
    }
    else if (target > matrix[row][col])
    {
     min = middle + 1;
    }
    else
    {
     return true;
    }

   }
   return false;
  }



 public static void main(String args[]) {
  
  int[][] matrix = {
      {10, 11, 12, 13},
      {14, 15, 16, 17},
      {27, 29, 30, 31},
      {32, 33, 39, 50}
    };

   // Example 1
  Object x = findKey(matrix, 4, 4, 80);
  System.out.println("Search for 80 returned: " + x);
  
  // Example 2
  x = findKey(matrix, 4, 4, 15);
  System.out.println("Search for 15 returned: " + x);
 }
}