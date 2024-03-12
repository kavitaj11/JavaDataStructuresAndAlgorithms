package com.math.examples;

class PascalTriangle {
 /*
 Pascal’s triangle is a triangular array of binomial coefficients.
  To make the triangle, we start with a “1” in the top most row
  and keep placing numbers in the subsequent rows below it to make a triangular pattern.
  Each number in the row is obtained by adding the numbers directly above it.
  */

 //Given below is a recursive implementation of Pascal’s triangle.
 //The time complexity of this solution is O(n),
 // since starting from the first line, we use previous lines to calculate the current line.
 public static int[] pascalTriangleRecursive(int lineNumber) {
  
  int currentLineSize = lineNumber;
  int previousLineSize = currentLineSize - 1;
  int[] currentLine = new int[currentLineSize]; // Create container for current line values.

  if (lineNumber == 1) {
   
   currentLine[0] = 1;
   System.out.println(1);
   return currentLine;
  } 
  else {

   
   int[] previousLine = pascalTriangleRecursive(lineNumber - 1); // We'll calculate current line based on previous one.

   // Let's go through all elements of current line except the first and last one
   // (since they were and will be filled with 1's)
   // calculate current coefficient based on previous line.
   for (int numIndex = 0; numIndex < currentLineSize; numIndex++) {
    
    int leftCoefficient = (numIndex - 1) >= 0 ? previousLine[numIndex - 1] : 0;
    int rightCoefficient = numIndex < previousLineSize ? previousLine[numIndex] : 0;
    currentLine[numIndex] = leftCoefficient + rightCoefficient;
    System.out.print(leftCoefficient + rightCoefficient + " ");
   }
   System.out.println();
   return currentLine;
  }
 }
 public static void main(String args[]) {
  
  int n = 10; // play around with this number to see how the triangle grows
  pascalTriangleRecursive(n);
 }
}