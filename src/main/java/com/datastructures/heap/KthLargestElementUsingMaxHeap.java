package com.datastructures.heap;//Build maxHeap of the given array.
//Extract the maximum element/root and add it to the result
//Reduce the length of array and repeatedly build maxHeap till we reach K.
/*Explanation #
We first create a max-heap out of the given array by inserting the list elements into an empty heap.
 Then we then call buildMaxHeap()on the heap kk times save the output in a list and return it.

Time Complexity #
The time complexity of creating a heap is O(n) and removing min is O(klogn).

Therefore, we can say that the total time complexity is O(n+klogn).*/

class KthLargestElementUsingMaxHeap 
{
  public static int[] findKLargest(int[] arr, int k) {
    int arraySize = arr.length;
    if ( k <= arraySize) {      
      int[] result = new int[k];	// build the result array of size = k
      for (int i = 0; i < k; i++) {
        buildMaxHeap(arr, arraySize);
        result[i] = arr[0];
        arr[0] = arr[--arraySize];
      }
      return result;
    }
    System.out.println("Value of k = " + k + "Out of Bounds");
    return arr;
  }

  private static void buildMaxHeap(int[] heapArray, int heapSize) {

    // swap largest child to parent node 
    for (int i = (heapSize - 1) / 2; i >= 0; i--) {
      maxHeapify(heapArray, i, heapSize);
    }
  }

  private static void maxHeapify(int[] heapArray, int index, int heapSize) {
    int largest = index;

    while (largest < heapSize / 2) { // check parent nodes only
      int left = (2 * index) + 1; //left child
      int right = (2 * index) + 2; //right child
      if (left < heapSize && heapArray[left] > heapArray[index]) {
        largest = left;
      }

      if (right < heapSize && heapArray[right] > heapArray[largest]) {
        largest = right;
      }

      if (largest != index) { // swap parent with largest child
        int temp = heapArray[index];
        heapArray[index] = heapArray[largest];
        heapArray[largest] = temp;
        index = largest;
      } else {
        break; // if heap property is satisfied
      }

    } // end of while
  }

  public static void main(String args[]) {
    int[] input = {9, 4, 7, 1, -2, 6, 5};
    int[] output = findKLargest(input, 2);

    for(int i = 0; i < output.length; i++) 
      System.out.println(output[i]);
  }

}
