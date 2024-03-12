package com.algorithms.divideAndConquer;

import java.util.Arrays;

class MissingNumber {
 /*
A naive solution to this problem would be:
Traverse through the whole list starting from the first index.
Find out the difference between the current and the last index element.
If the difference is greater than 1, it means the integer between the current and last index is missing.
Return index+1 (as the array is contiguous and starts from 1, the missing number will be equal to index+1).

Time complexity #
This solution iterates through every single element
and takes the same approach as finding an element in an unsorted array.
It makes no use of the fact that the array has contiguous and sorted elements.
Therefore, the time complexity of this method is O(n).
  */
 public static int missingNumberNaive(int[] arr, int size) {
  int missing = -1; // if no element is missing
  int last = arr[0]; // start off from the first element
  if(last != 1)
  {
      return 1;
  }
  for (int i = 1; i < size; i++) { // traverse through the whole array
   if (arr[i] - last > 1) {
    missing = last + 1;
    break;
   }
   last++;
  }
  return missing;
 }



 //Solution #2: Efficient #
 //An efficient solution to this problem works on the divide and conquer principle, just like binary search
 //Since this approach follows the same process as a binary search would,
 // the time complexity of this solution is O(log n).
  // Performing a binary search like technique to find the missing number in the array
  public static int missingNumber(int arr[], int size) {

   int leftLimit = 0, rightLimit = size - 1; // initialize limits

   // Keeping in check the Boundary Cases!
   if (arr[leftLimit] != 1) // if '1' is not present at 0th index
    return 1;

   while (leftLimit <= rightLimit) // binary search
   {
    int middle = (leftLimit + rightLimit) / 2;

    // Element at index `i` should be `i+1` (e.g. 1 at index 0).
    // If this is the first element  which is not `i`+ 1, then  missing element is middle+1
    if (arr[middle] != middle + 1 && arr[middle - 1] == middle)
     return middle + 1;

    // If this is not the first missing element search in left subarray
    if (arr[middle] != middle + 1)
     rightLimit = middle - 1; // update rightLimit to search only left

     // if it follows index+1 property then search in right side
    else
     leftLimit = middle + 1; // update leftLimit to search only right
   }
   return -1; // if no element missing
  }

 public static void main(String args[]) {
  int[] input = {1,2,4};

  System.out.println(missingNumber(input, input.length));

  int[] input1 = {1,2,4};
  int[] input2 = {1,2,3,4,6};
  int[] input3 = {2,3,4,5,6};
  int[] input4 = {1,2,3,4,5,6,7,8,9,10};

  System.out.println("Find the Missing Number!");
  System.out.println(Arrays.toString(input1) + " --> " + missingNumber(input1, input1.length));
  System.out.println(Arrays.toString(input2) + " --> " + missingNumber(input2, input2.length));
  System.out.println(Arrays.toString(input3) + " --> " + missingNumber(input3, input3.length) + "\t\t\t\t\t\t Corner Case I - Handeled");
  System.out.println(Arrays.toString(input4) + " --> " + missingNumber(input4, input4.length) + "\t\t\t Corner Case II - Handeled");

 }
}

