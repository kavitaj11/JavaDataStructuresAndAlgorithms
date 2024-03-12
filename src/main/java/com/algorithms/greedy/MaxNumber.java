package com.algorithms.greedy;

//Given the number of digits and the sum of the digits, find the largest number that can be created.
/*
We can solve the problem using the greedy approach.

The idea is to fill all the digits one by one,
from the leftmost to the rightmost digit
(or from the most significant digit to the least significant digit),
then compare the remaining sum with 9.
If the remaining sum is more than 9, place 9 at the current index;
otherwise, place the remaining sum at the current index.
 Since we fill the digits from left to right, we put the highest digits on the left side.
 Therefore, getting the largest number.

This is a greedy approach. We use the highest number 9 and put it in the leftmost available slot.
This idea works because our goal is to find the largest number and 9, being the largest digit, helps us do so.

This approach fails in certain conditions, l
ike when the sum is less than the number of digits or if a number is not possible in the constraints given.

The time complexity is O(n) because we can simply find the solution in one iteration
 */
class MaxNumber {
 public static void findLargestNumber(int number_of_digits, int sum_of_digits) {
  int[] result = new int[number_of_digits];
  // If sum of digits is 0, then a number is possible only if number of digits is 1. 
  if (sum_of_digits == 0) {
   if (number_of_digits == 1) {
    System.out.print(0);
   } else {
    System.out.print("None");
   }
   return;
  }
  // sumOfDigits is greater than the maximum possible sum. 
  if (sum_of_digits > 9 * number_of_digits) {
   System.out.print("None");
   return;
  }
  // Fill from most significant digit to least significant digit! 
  for (int i = 0; i < number_of_digits; i++) {
   // Place 9 to make the number largest 
   if (sum_of_digits >= 9) {
    result[i] = 9;
    sum_of_digits -= 9;
   }
   // If remaining sum becomes less than 9, then fill the remaining sum 
   else {
    result[i] = sum_of_digits;
    sum_of_digits = 0;
   }
  }
  for (int i = 0; i < number_of_digits; i++) {
   System.out.print(result[i]);
  }
 }

    public static void main(String[] args) {
        int sumOfDigits = 20;
        int numberOfDigits = 3;

        System.out.println("If sum of digits is 20 and number of digits is 3 then ");
        MaxNumber.findLargestNumber(numberOfDigits, sumOfDigits);
        System.out.println();

        //Example 2
        sumOfDigits = 100;
        numberOfDigits = 2;

        System.out.println("If sum of digits is 100 and number of digits is 2 then ");
        MaxNumber.findLargestNumber(numberOfDigits, sumOfDigits);

    }
}

