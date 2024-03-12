package com.datastructures.arrays;

public class KthSmallest {
	public static int partition(int[] A, int start, int end) {
	    int pivot = A[start];
	    int pivotPosition = start++;
	    while (start <= end) {
	        // scan for values less than the pivot
	        while ((start <= end) && (A[start] < pivot)) {
	            start++;
	        }
	 
	        // scan for values greater than the pivot
	        while ((end >= start) && (A[end] >= pivot)) {
	            end--;
	        }	 
	        if (start > end) {
	            // swap the end uncoformed 
	            // element with the pivot
	            swap(A, pivotPosition, end); 
	        }
	        else {
	            // swap unconformed elements:
	            // start that was not lesser than the pivot 
	            // and end that was not larger than the pivot
	            swap(A, start, end);
	        }
	    }
	    return end;
	}
	 
	@SuppressWarnings("unused")
	private static int orderStatisticRecursive(int[] A, int k, int start, int end) {
	            
	    int pivotPosition = partition(A, start, end);
	    if ( pivotPosition == k - 1) {
	        return A[k - 1];
	    }
	    if (k - 1 < pivotPosition ) {
	        return orderStatisticRecursive(A, k, start, pivotPosition - 1);
	    }
	    else {
	        return orderStatisticRecursive(A, k, pivotPosition + 1, end);
	    }
	}
	 
	// iterative version
	private static int orderStatistic(int[] A, int k, int start, int end) {
	    int pivotPosition = partition(A, start, end);
	 
	    while (pivotPosition != k - 1) {
	        if (k - 1 < pivotPosition) {
	            end = pivotPosition - 1;
	        }
	        else {
	            start = pivotPosition + 1;
	        }
	        
	        pivotPosition = partition(A, start, end);
	    }
	    
	    return A[k - 1];
	}
	public static int kthSmallest(int[] A, int k)
	{
	    return orderStatistic(A, k, 0, A.length - 1);
	}
	
	public static void swap(int[] A, int i, int j){
		int temp = A[i];
		A[i]= A[j];
		A[j] = temp;
	}


	public static void main(String[] args){
		int[] arr={2,4,7,3,9,12,5,23,56};
		System.out.println(kthSmallest(arr, 4));
	}


}