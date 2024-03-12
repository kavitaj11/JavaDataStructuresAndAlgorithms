package com.algorithms.searching;

//count the frequency of a number in a sorted array of integers.
/*
It draws upon the fact that if an element exists in a sorted array,
all of its occurrences exist contiguously.
It simply uses a binary search to find one such occurrence
 and counts the ones that are present on its left and right (if any).

Time complexity #
The time complexity of the binary search algorithm used in this function is O(logn)
and the time required to count the adjacent duplicates is O(count),
 which makes it O(logn + count).
 However, the time complexity is still O(n) in the worst case.

 */
class CountElementOccurance
{
  public static int calcFreq(int[]arr, int key) 
  { 
    int arrSize = arr.length;
    int index = binarySearch(key, arr, arrSize);
    
    if (index == -1)   // If element is not present 
      return 0; 

    int count = 1; // Initialzing a count
    
    int start = index - 1; // Counting the ones present on the left
    while (start >= 0 && arr[start] == key) {
      count++;
      start--; // Decrement the start index if found
    }
    
    int end = index + 1;  // Counting the ones present on the right
    while (end < arrSize && arr[end] == key) {
      count++;
      end++; // Increment the end index if found
    }
    return count; 
  }


  static int binarySearch(int s, int arr[], int arrSize) {
    if (arrSize <= 0)
      return -1;

    if (arrSize == 1) {
      if (arr[0] == s)
        return 0;
      else
        return -1;
    }

    int start = 0;
    int end = arrSize - 1;
    int mid;

    while (start <= end) {
      mid = (end + start) / 2;
      if (arr[mid] < s)
        start = mid + 1;
      else if (arr[mid] > s)
        end = mid - 1;
      else
        return mid;
    }
    return -1;
  }


  /*
  This solution works by searching for the index at which the first occurrence of the element exists
  and the index at which the second one exists.
   If both indexes are found,
   the number of times each appears is simply their difference plus 1.

Time complexity #
The time complexity is O(logn).

   */

    public static int calcFreqOptimized(int arr[], int key)
    {
      // Finding the index of the first occurrence
      int arrSize = arr.length;
      int start = 0, end = arrSize - 1, mid, result = -1;
      while (start <= end) {
        mid = (start + end) / 2;
        if (arr[mid] == key) {
          result = mid;
          end = mid - 1;
        } else if (arr[mid] > key) {
          end = mid - 1;
        } else if (arr[mid] < key) {
          start = mid + 1;
        }
      }

      // Finding the index of the second occurrence
      int start1 = 0, end1 = arrSize - 1, mid1, result1 = -1;
      while (start1 <= end1) {
        mid1 = (start1 + end1) / 2;
        if (arr[mid1] == key) {
          result1 = mid1;
          start1 = mid1 + 1;
        } else if (arr[mid1] > key) {
          end1 = mid1 - 1;
        } else if (arr[mid1] < key) {
          start1 = mid1 + 1;
        }
      }

      // If one of them is not found, return their difference
      if (result == -1 || result1 == -1)
        return (result1 - result);

      return (result1 - result + 1);
    }

  public static void main(String args[]) 
  {
    int arr[] = {-5,-3,0,1,3,3,3,3,4,5};
    int key = 3;
    System.out.println("The key \""+ key + "\" occurs " + calcFreq(arr, key) + " times in the Array.");
  }
}
