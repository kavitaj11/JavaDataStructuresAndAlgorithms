package com.algorithms.sorting;
import java.util.Arrays;

/*
Problem statement #
Implement a function that sorts an array of 0's,1's and 2's.
This is called the Dutch national flag problem.
Since the number O can be represented as the blue color, 1 as the white color, and 2 as the red color,
 we want the array to form the Dutch flag.

Sample input #
arr = {2, 0, 0, 1, 2, 1, 0}
Sample output #
0 0 0 1 1 2 2
Try solving this problem inplace and in linear time without using any extra space!
 */

class DutchFlag
{
    //Solution #1: Brute force #
    //sorting in ascending order using selection sort
    /*
   used simple selection sort to rearrange the array in ascending order.
    This method is naive and does not cater to the fact that we have just three different digits for this problem.

    Time complexity #
    The time complexity is also too high, i.e. O(n^2)
    */
    public static int[] selectionSort(int[] lst) {
      int size = lst.length;
      int index = 0;
      for (int i = 0; i < size; i++) {
        //finding the minimum value's index
        index = find_min(lst, i, size);
        //swapping it with current value
        int temp = lst[i];
        lst[i] = lst[index];
        lst[index] = temp;
      }
      return lst;
    }

    //function to find the index of the minimum value
    public static int find_min(int[] lst, int start, int end) {
      int min = lst[start];
      int index = start;

      for (int i = start; i < end; i++) {
        if (lst[i] < min) {
          min = lst[i];
          index = i;
        }

      }
      return index;
    }


  //Solution #2: Three-way partitioning
  /*
  In this solution, there are three possible values of the element at the mid; 0, 1, or 2.
  If the value at the mid index is equal to 0,
  swap this value with the leftmost value and increment both the indexes.
  If the value at the mid index is equal to 2,
  swap this value with the rightmost index and decrement the rightmost index.
  If the value at the mid index is equal to 1, simply increment the mid index.
   Time complexity #
   Since the algorithm is in place and traverses the complete array in the while loop once,
   the time complexity is O(n)
   */
  public static void dutchNationalFlag(int [] arr) 
  {
    int i = 0;
    int mid = 0;
    int j = arr.length - 1;
    
    while (mid <= j) 
    {
      //if value at mid equals to zero
      if (arr[mid] == 0)
        swap(arr, i++, mid++);
      //if value at mid equals to two
       else if (arr[mid] == 2) 
        swap(arr, mid, j--);
      //if value at mid equals to one
       else if (arr[mid] == 1) 
        mid++;
    }
  }

  static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String args[]) 
  {
    int[] arr = {2, 0, 0, 1, 2, 1};
    dutchNationalFlag(arr);
    System.out.println(Arrays.toString(arr)); // import java.util.Arrays; for this functionality
  }
}
