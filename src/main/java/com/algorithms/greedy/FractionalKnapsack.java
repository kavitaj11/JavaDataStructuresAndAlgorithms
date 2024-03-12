package com.algorithms.greedy;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
/*
Greedy is an algorithmic paradigm where the solution is built piece by piece.
The next piece that offers the most obvious and immediate benefit is chosen.
The greedy approach always makes the choice that maximizes the profit and minimizes the cost at any given point.
It means that a locally-optimal choice is made in the hope that it leads to a globally optimal solution.

A real life example #
For example:

You just got a new piggy bank to have some savings for your college admission.
The bank is small, and can only contain a fixed weight.
 Therefore, you have to be smart and choose the maximum value vs weight ratio for adding anything into it.
 You have a fixed number of coins and have to pick them one at a time until the capacity of the bank is reached.

This is also called the Fractional Knapsack Problem.
The local optimal strategy is to choose the item that has maximum value vs weight ratio.
This strategy also leads to an optimal global solution because we are allowed to take fractions of an item.
 */

/*

Problem Statement #
You are given the capacity of a knapsack W and a list of ​”​​n​”​​ items that each have a certain value.
Fractions of each item can be placed in the knapsack.
Your goal is to implement a function for getting the maximum possible total value of V in the knapsack.

A brute force approach would try picking items in all possible proportions.
Since the items can be picked in fractions,
there is an infinite number of possible fractions of each time. This clearly is not tractable.

The following is an efficient greedy solution.
The key to solving this problem is that we can easily break the items to maximize the total value of the knapsack.
Therefore, we can easily grab the item with the greatest ratio and break off the largest piece.
The remainder of the bag can be filled with smaller ratio pieces, utilizing the maximum space.

Here, we calculate the ratio value/weight for each item and sort the items on the basis of this ratio.
Then, we take the items with the highest ratio
and add them to the knapsack until we cannot add the next item as a whole.
At the end, add as much of the next item as you can.

Time complexity #
Since the only step that takes major time is sorting algorithm,
in the worst case, the whole solution takes O(n∗lg(n)), where n is the total number of items available.

 */
class FractionalKnapsack {
 private static double getMaxValue(double[] w, double[] v, double c) {
  int totalItems = v.length;
  ItemValue[] iVal = new ItemValue[totalItems];

  for (int i = 0; i < totalItems; i++) {
   iVal[i] = new ItemValue(w[i], v[i], i);
  }

  Arrays.sort(iVal, new Comparator< ItemValue >() //sorting items by value;
   {
    public int compare(ItemValue sampleObj1, ItemValue sampleObj2) {
     return sampleObj2.cost.compareTo(sampleObj1.cost);
    }
   });
  double totalValue = 0;
  for (ItemValue i: iVal) // iterator to traverse items list
  {
   double currWeight = (double) i.w;
   double currValue = (double) i.v;

   if (c - currWeight >= 0) // this item can be picked as whole 
   {
    c = c - currWeight;
    totalValue += currValue;

    // uncomment the following line to see the step by step working of this function
    // System.out.println("c, v, t = " + c + ", " + currValue + ", " + totalValue);
   } else // item can't be picked as whole
   {
    double fraction = ((double) c / (double) currWeight); // only a fraction of it can be obtained!
    totalValue += (currValue * fraction);
    c = (double)(c - (currWeight * fraction));

    // uncomment the following line to see the step by step working of this function
    // System.out.println("c, v, t = " + c + ", " + currValue + ", " + totalValue + "\n");
    break;
   }
  }
  return totalValue;
 }

 static class ItemValue // item value class 
 {
  Double cost;
  double w, v, i;

  public ItemValue(double weight, double value, int index) // constructor
  {
   w = weight;
   v = value;
   i = index;
   cost = new Double(v / w);
  }
 }

public static void main(String[] args) {
 DecimalFormat decimal = new DecimalFormat("#.##"); // this class rounds off the double upto 2 decimal places

 double[] weights = {2, 1, 6, 0.5, 0.25, 7}; 
 double[] values = {50, 70, 100, 50, 30, 99}; 
 int capacity = 10;

 double maxValue = getMaxValue(weights, values, capacity);
 System.out.println("Maximum value we can obtain = " + decimal.format(maxValue));
}
}