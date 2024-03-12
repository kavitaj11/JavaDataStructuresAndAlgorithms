package com.datastructures.arrays;

public class FindSecondMinimum {

    // Find minimum value in an unsorted array of integers.
    //Recursive
    public static int findMin (int [] A, int startIndex) {
        if (startIndex == A.length - 1) {
            return A[startIndex];
        } else {
            return Math.min(A[startIndex],
                    findMin(A, startIndex + 1));
        }
    }

    //Iterative
    public static int getMinIndex(int[] values) {
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int i=0; i<values.length; i++)
            if (values[i] < minValue) {
                minValue = values[i];
                minIndex = i;
            }
        return minIndex;
    }

    public static int getSecondMinIndex(int[] values) {
        int secondIdx = -1;
        int minIdx= getMinIndex(values);
        for(int i=0; i<values.length; i++) {
            if (i == minIdx) continue;
            if (secondIdx == -1 || values[i] < values[secondIdx])
                secondIdx = i;
        }
        return secondIdx;
    }


    /*
    An Efficient Solution can find the minimum two elements in one traversal. Below is complete algorithm.
    Algorithm:
    1) Initialize both first and second smallest as INT_MAX
    first = second = INT_MAX
    2) Loop through all the elements.
       a) If the current element is smaller than first, then update first
       and second.
       b) Else if the current element is smaller than second then update
    second
    Time Complexity: O(n)
     */
    /* Function to print first smallest and second smallest
     elements */
    static void print2Smallest(int arr[])
    {
        /* There should be atleast two elements */
        if (arr.length < 2) {
            System.out.println(" Invalid Input ");
            return;
        }
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length ; i ++) {
            /* If current element is smaller than first
              then update both first and second */
            if (arr[i] < first) {
                second = first;
                first = arr[i];
            }
            /* If arr[i] is in between first and second
               then update second  */
            else if (arr[i] < second && arr[i] != first)
                second = arr[i];
        }
        if (second == Integer.MAX_VALUE)
            System.out.println("There is no second smallest element");
        else
            System.out.println("The smallest element is " +
                    first + " and second Smallest element is " + second);
    }

    /* Driver program to test above functions */
    public static void main (String[] args)
    {
        int arr[] = {12, 13, 1, 10, 34, 1};
        print2Smallest(arr);
    }
}
