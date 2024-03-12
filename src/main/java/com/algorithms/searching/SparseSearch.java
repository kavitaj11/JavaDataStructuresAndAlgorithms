package com.algorithms.searching;

/*
Problem statement #
Implement a function that returns the index of the target string in a sorted and sparsed array of strings.
Input #
A sparsed, sorted array of strings and the target string whose location needs to be found
Output #
Index (location) of the target string

Sample input #
array = {"", "educative", "", "", "",  "hello", "", "learning", "world", "", "", ""};
target = "educative"
Sample output #
1
 */
class SparseSearch
{
  //Solution #1: Brute force #
  /*
  In this solution, we traverse the entire array of strings
  and stop only if we find the target string or reach the end of the array.

  Time complexity #
  The time complexity is the time it takes to traverse the entire array, i.e., O(n).
   */
  public static int searchForStringBruteForce(String[] array, String target)
  {
    //traverse array
    for (int i = 0; i < array.length; i++) {
      //check if current value equals to target string
      if (array[i].equals(target)) {
        //return the index value
        return i;
      }
    }
    return -1;
  }

  //Solution #2: Modified binary search
  //We know that the array of strings given to us is sparsely filled
  // and also sorted lexicographically; this means that we can use binary search.
  // However, we need to make a slight modification.
  //if the middle of the array contains an empty string,
  // just move middle to the closest non-empty string.
  //
  //Time complexity #
  //The solution runs in O(logn).
  public static int searchStringUsingModifiedBinarySearch(String[] array, String target) {
    return modifiedBinarySearch(array, 0, array.length - 1, target);
  }


  public static int modifiedBinarySearch(String[] arr, int low, int high, String target) {
      if (low > high)
        return -1;
      //calculating mid value
      int mid = (low + high) / 2;

      // Our modification
      if (arr[mid] == "") {
        int i = mid - 1;
        int j = mid + 1;
        while (true) {
          if (i < low && j > high)
            return -1;

          if (i >= low && arr[i] != "") {
            mid = i;
            break;
          } else if (j <= high && arr[j] != "") {
            mid = j;
            break;
          }
          i--;
          j++;
        }
      }

      // Now perform simple Binary Search
      if (arr[mid].equals(target))
        return mid;
      else if (arr[mid].compareTo(target) > 0)
        return modifiedBinarySearch(arr, low, mid - 1, target);
      else
        return modifiedBinarySearch(arr, mid + 1, high, target);
    }



  public static void main(String args[]) 
  {
    String [] array = {"", "educative", "", "", "",  "hello", "", "learning", "world", "", "", ""};
    String [] targetArray = {"educative", "learning"};
    
    for(int i = 0; i < 2; i++) {
      System.out.println(targetArray[i] + ": " + searchStringUsingModifiedBinarySearch(array, targetArray[i]));
    }
  }
}