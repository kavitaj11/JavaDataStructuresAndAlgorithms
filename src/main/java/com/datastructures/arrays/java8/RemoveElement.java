package com.datastructures.arrays.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class RemoveElement {

	//Basic approach: Using another array
	// Function to remove the element 
	public static int[] removeTheElement(int[] arr, int index) 	{
		if (arr == null || index < 0 || index >= arr.length) {
			return arr; 
		} 
		int[] anotherArray = new int[arr.length - 1];
		// Copy the elements except the index 
		// from original array to the other array 
		for (int i = 0, k = 0; i < arr.length; i++) { 
	    	if (i == index) continue;
			anotherArray[k++] = arr[i];
		}
		// return the resultant array 
		return anotherArray; 
	}

	//Using System.arrayCopy()
	public static int[] removeTheElementUsingArrayCopy(int[] arr, int index){
		if (arr == null || index < 0 || index >= arr.length) {
			return arr;
		}
		int[] anotherArray = new int[arr.length - 1];
		System.arraycopy(arr, 0, anotherArray, 0, index);
		System.arraycopy(arr, index + 1,	anotherArray, index,
				arr.length - index - 1);

		// return the resultant array
		return anotherArray;
	}


	//Using Java 8 IntStream
	//Get the array and the index.
	//Convert the array into IntStream using IntStream.range() method.
	//Remove the specified index element using filter() method.
	//Map and form a new array of the filtered elements using map() and toArray() methods.
	//Return the formed array.

	// Function to remove the element
	public static int[] removeTheElementUsingJava8(int[] arr, int index){
		if (arr == null || index < 0 || index >= arr.length) {
			return arr;
		}
		// return the resultant array
		return IntStream.range(0, arr.length).filter(i -> i != index)
				.map(i -> arr[i])
				.toArray();
	}


	//Using ArrayList
	public static int[] removeTheElementUsingArrayList(int[] arr, int index){
		if (arr == null || index < 0 || index >= arr.length) {
			return arr;
		}
		// Create ArrayList from the array
		List<Integer> arrayList = IntStream.of(arr).boxed()
				.collect(Collectors.toList());

		// Remove the specified element
		arrayList.remove(index);

		// return the resultant array
		return arrayList.stream().mapToInt(Integer::intValue).toArray();
	}


	// Driver Code 
	public static void main(String[] args) 
	{ 

		// Get the array 
		int[] arr = { 1, 2, 3, 4, 5 }; 

		// Print the resultant array 
		System.out.println("Original Array: "
						+ Arrays.toString(arr));

		// Get the specific index 
		int index = 2; 

		// Print the index 
		System.out.println("Index to be removed: "
						+ index); 

		// Remove the element 
		arr = removeTheElement(arr, index); 

		// Print the resultant array 
		System.out.println("Resultant Array: "
						+ Arrays.toString(arr)); 
	} 
} 