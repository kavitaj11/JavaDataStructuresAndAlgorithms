package com.algorithms.greedy;

//Given a positive fraction, break it down into its Egyptian fractions.
/*
Problem statement #
Every positive fraction can be represented as the sum of its unique unit fractions.
A fraction is a unit fraction if the numerator is 1 and the denominator is a positive integer.
For example, 1/3 is a unit fraction. Such a representation is called Egyptian fraction.
Input #
The inputs are two variables, numerator and denominator.
Output #
The output is a string in the format 1/d1 , 1/d2 ...
 */

//The time complexity is O(log n)
// because each time we reduce the fraction using recursive calls as the reduction is in the form of division.
class EgyptianFraction
{
    public static void printEgyptianFraction(int numerator, int denominator) 
    {
      //if either numerator or denominator is zero
      if (denominator == 0 || numerator == 0){
        return;
      }
      //numerator divides denominator -> fraction in 1/n form
      if (denominator % numerator == 0) {
        System.out.print("1/" + denominator / numerator);
        return;
      }
      //denominator can divide numerator -> number not a fraction 
      if (numerator % denominator == 0) {
        System.out.println(numerator / denominator);
        return;
      }
      //if numerator greater than denominator 
      if (numerator > denominator) {
        System.out.println(numerator / denominator + " , ");
        printEgyptianFraction(numerator % denominator, denominator);
        return;
      }
      //denominator  greater than numerator here
      int n = denominator / numerator + 1;
      System.out.print("1/" + n + " , ");
      //call function recursively for remaining part  
      printEgyptianFraction(numerator * n - denominator, denominator * n);
}



  public static void main(String[] args){

    //Example 1
    int numerator = 6, denominator = 14;
    System.out.print("Egyptian EgyptianFraction Representation of " + numerator + "/" + denominator + " is\n ");
    EgyptianFraction.printEgyptianFraction(numerator, denominator);
    System.out.println();

    //Example 2
    numerator = 2;
    denominator = 3;
    System.out.print("Egyptian EgyptianFraction Representation of " + numerator + "/" + denominator + " is\n ");
    EgyptianFraction.printEgyptianFraction(numerator, denominator);

  }
}

