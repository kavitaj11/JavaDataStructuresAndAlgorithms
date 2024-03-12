package com.datastructures.arrays;

import java.util.Arrays;

class CheckRemoveEven {
	public static int[] removeEven(int[] arr) {
		int oddElements = 0;

		//Find number of odd elements in arr
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 != 0) oddElements++;
		}

		//Create result array with the size equal to the number of odd elements in arr
		int[] result = new int[oddElements];
		int result_index = 0;

		//Put odd values from arr to the resulted array
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 != 0) 
        result[result_index++] = arr[i];
		}

		return result;
	}


  public static void main(String args[]) {
    int[] arr = {4, 8, 9, 5, 6, 4, 5, 3, 2, 6}; //declaration and instantiation
    System.out.print("Before removing Even Numbers: "+Arrays.toString(arr));
    int[] newArr =  removeEven(arr); // calling removeEven
    System.out.print("After removing Even Numbers: "+Arrays.toString(newArr));
  }
}