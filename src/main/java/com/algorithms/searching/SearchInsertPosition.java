package com.algorithms.searching;

class SearchInsertPosition
{
//write a function to find the appropriate position to insert a new element in a sorted array.

  /*
  Problem statement #
Given a sorted array and a target value,
return the index where the target value would be if it were inserted in order.
If the target index is already present in the array,
return the index of where it is found. You may assume that no duplicates are in the array.
Sample input #
  int arr[] = {1,3,5,6};
  int target = 5;
Sample output #
   2
  This solution is a simple modification of the binary search algorithm.
  It simply keeps track of the variable mid with another one called pos.
  It starts by traversing the array and calculating the mid value.
   If it equals target, it simply returns the mid value.
    If the value at the mid index of the array is greater than target,
     that means the value must be in the left half of the array.
     Otherwise, it must be in the right half.
      Based on that, the start and end positions are changed
       and the new mid value is calculated.
       The process repeats until the target is found.

Time complexity #
This has the same time complexity as binary search: O(log(n)).
   */
  public static int insertPosition(int[] arr, int target) 
  {   
    int arrSize = arr.length;
    //checking of size of array is less than 1
    if(arrSize < 1)
        return -1;  
    int start = 0;
    int end = arrSize - 1;
    int mid = 0, pos = 0;
    //traversing the array
    while(start <= end) 
    {
        //calculating the mid value
        mid = start + (end-start)/2;  
        //if mid value equals target return the mid index   
        if(arr[mid] == target)
        return mid;
        //if mid value greater than target serach in the left half 
        else if(arr[mid] > target)
        {
            end = mid - 1;
            pos = mid;
        } 
        //otherwise search in the right half
        else 
        {
            start = mid + 1;
            pos = mid + 1;
        }
    }
    return pos;
  }

  public static void main(String args[]) 
  {
    int[]arr = {0, 1, 2, 3, 5, 6};
    
    // Example 1
    System.out.println("Index to Insert " + "\"5\" is " + insertPosition(arr, 5));
    
    // Example 2
    System.out.println("Index to Insert " + "\"3\" is " + insertPosition(arr, 3));

    // Example 3
    System.out.println("Index to Insert " + "\"7\" is " + insertPosition(arr, 7));  
  }
}