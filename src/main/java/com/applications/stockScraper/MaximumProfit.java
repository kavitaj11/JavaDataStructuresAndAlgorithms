package com.applications.stockScraper;

//Time complexity #
//Since the complete array is traversed only once, the time complexity will be O(n).
//
//Space complexity #
//The space complexity will be O(1) because we are using constant space.

/*
We have extracted the stock increase and decrease percentages over a number of consecutive days.
This will be represented as an array of numbers, one for each consecutive day,
holding the increase or decrease in stock price on the given day.
 We can use this data to find the maximum profit that could have been made for the given time period.
 Sometimes, the maximum profit might be negative, indicating a period of minimum loss.
  For simplicity and to avoid fractional values,
  we are rounding the increase and decrease percentages to their nearest value.

We’ll be provided with an array of positive and negative integers.
The indexes will indicate the day number and the integer value will indicate
the stock increase or decrease percentage on that day.
We have to return the maximum value that can be obtained by adding the sub-array elements.

 */
class MaximumProfit {
  public static int maxProfit(int[] A) {
    if (A.length < 1) {
      return 0;
    }

    int currMax = A[0];
    int globalMax = A[0];
    for (int i = 1; i < A.length; ++i) {

      if (currMax < 0)
        currMax = A[i];
      else
        currMax += A[i];

      if (globalMax < currMax)
        globalMax = currMax;
    }

    return globalMax;
  }
  
  public static void main(String[] args) {
    // Driver code
    
    int[] stocks = new int[]{-4, 2, -5, 1, 2, 3, 6, -5, 1};
    System.out.println("Maximum Profit: " + maxProfit(stocks) + "%");
  }
}

/*
The basic idea is to scan the entire array and at each position find the maximum sum of the sub-array ending there.
 This is achieved by keeping a currentMax for the current array index and a globalMax.

Let’s see how we might implement this functionality:

1. Initialize a currentMax and a globalMax and assign them the first value of the array.

2. Traverse the array starting with the second element.

3. For each element, check whether currentMax is less than zero:
    If it is less than zero, assign it the current element as its value
    Otherwise, add the current element in the currentMax

4. Similarly, for each element check if globalMax is less than currentMax
  then assign globalMax equal to the value of currentMax

 */