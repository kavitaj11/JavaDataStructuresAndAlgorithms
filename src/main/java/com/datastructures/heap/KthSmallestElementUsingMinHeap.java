//Build minHeap of the given array. 
//Extract the minimum element/root and add it to the result
//Keep removing elements and repeatedly build minHeap till we reach K.
/*
Explanation #
We first create a max-heap out of the given array by inserting the list elements into an empty heap. Then we then call buildMaxHeap()on the heap kk times save the output in a list and return it.

Time Complexity #
The time complexity of creating a heap is O(n)O(n) and removing min is O(klogn)O(klogn).

Therefore, we can say that the total time complexity is O(n+klogn)O(n+klogn).*/
class KthSmallestElementUsingMinHeap 
{
  public static int[] findKSmallest(int[] arr, int k) 
  {
    int arraySize = arr.length;
    if(k <= arraySize)
    {
      int[] result = new int[k];   
      for (int i = 0; i < k; i++) 
      {
        result[i] = removeMin(arr, arraySize);
        --arraySize;
      }
      return result;
    }
    System.out.println("Value of k = " + k+ "out of bounds!");
    return arr;
  }

  public static int removeMin(int[] heapArray, int heapSize)
  {
    buildMinHeap(heapArray, heapSize);
    int min = heapArray[0];
    heapArray[0] =  heapArray[heapSize-1];
    return min;
  }
  private static void buildMinHeap(int[] heapArray, int heapSize) {

    // swap largest child to parent node 
    for (int i = (heapSize - 1) / 2; i >= 0; i--) {
      minHeapify(heapArray, i, heapSize);
    }
  }

  private static void minHeapify(int[] heapArray, int index, int heapSize) {
    int smallest = index;

    while (smallest < heapSize / 2) { // check parent nodes only
      int left = (2 * index) + 1; //left child
      int right = (2 * index) + 2; //right child
      if (left < heapSize && heapArray[left] < heapArray[index]) {
        smallest = left;
      }

      if (right < heapSize && heapArray[right] < heapArray[smallest]) {
        smallest = right;
      }

      if (smallest != index) { // swap parent with largest child
        int temp = heapArray[index];
        heapArray[index] = heapArray[smallest];
        heapArray[smallest] = temp;
        index = smallest;
      } else {
        break; // if heap property is satisfied
      }

    } //end of while
  }

  public static void main(String args[]) {
    int[] input = {9, 4, 7, 1, -2, 6, 5};
    int[] output = findKSmallest(input, 2);

    for(int i = 0; i < output.length; i++) 
      System.out.println(output[i]);
  }

}
