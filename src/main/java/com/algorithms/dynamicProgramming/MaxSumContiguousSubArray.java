package com.algorithms.dynamicProgramming;

/**
 * Program to implement Kadane’s Algorithm to calculate maximum contiguous subarray sum of an array
 * Time Complexity: O(n)
 * The memory complexity of this solution is constant, O(1).
 *
 * The basic idea of Kadane’s algorithm is to scan the entire array
 * and at each position find the maximum sum of the subarray ending there.
 * This is achieved by keeping a current_max for the current array index and a global_max.
 * The algorithm is as follows:
 * current_max = A[0]
 * global_max = A[0]
 * for i = 1 -> size of A
 *     if current_max is less than 0
 *         then current_max = A[i]
 *     otherwise
 *         current_max = current_max + A[i]
 *     if global_max is less than current_max
 *         then global_max = current_max
 */

public class MaxSumContiguousSubArray {

		static int findMaxSumSubArray(int[] A) {
			if (A == null || A.length == 0) {
				return 0;
			}
			if (A.length == 1) {
				return A[0];
			}

			int currMax = A[0];
			int globalMax = A[0];
			for (int i = 1; i < A.length; ++i) {

				if (currMax < 0) {
					currMax = A[i];
				} else {
					currMax += A[i];
				}

				if (globalMax < currMax) {
					globalMax = currMax;
				}
			}

			return globalMax;
		}

	/**
	* This method implements Kadane's Algorithm
	* 
        * @param arr The input array
	* @return The maximum contiguous subarray sum of the array
        * 
        */
	static int largestContiguousSum(int arr[]){
		int i,len=arr.length,cursum=0,maxsum=Integer.MIN_VALUE;
		if(len==0)	//empty array
			return 0;
		for(i=0;i<len;i++){
			cursum+=arr[i];
			if(cursum>maxsum){
				maxsum=cursum;
			}
			if(cursum<=0){
				cursum=0;
			}
		}
		return maxsum;
	}

	/**
	 * Main method
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
	/*	Scanner sc=new Scanner(System.in);
		int n,arr[],i;
		n=sc.nextInt();
		arr=new int[n];
		for(i=0;i<n;i++){
			arr[i]=sc.nextInt();
		}
		int maxContSum=largestContiguousSum(arr);
		System.out.println(maxContSum);
		sc.close();
		*/
		int[] v = new int[]{1, 22, 10, 8, 72, 3, 6, 5, 8};
		System.out.println("Sum of largest subarray: " + findMaxSumSubArray(v));
		}
}


