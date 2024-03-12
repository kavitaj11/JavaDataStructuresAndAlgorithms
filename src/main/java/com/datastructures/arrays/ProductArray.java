package com.datastructures.arrays;

import java.util.Arrays;

//Array of Products of All Elements Except Itself
class ProductArray
{  
  public static int[] findProduct(int arr[])  
  { 
    int n = arr.length;
    int i, temp = 1; 

    // Allocation of result array
    int result[] = new int[n]; 

    // Product of elements on left side excluding arr[i]
    for (i = 0; i < n; i++) {
      result[i] = temp; 
      temp *= arr[i]; 
    }
    System.out.println("Array after left side products: " + (Arrays.toString(result)));
    // Initializing temp to 1 for product on right side
    temp = 1; 

    // Product of elements on right side excluding arr[i] 
    for (i = n - 1; i >= 0; i--) {
      result[i] *= temp; 
      temp *= arr[i]; 
    }
    System.out.println("Array after right side products: " + (Arrays.toString(result)));
    return result; 
  } 

  public static void main(String args[]) {
    int[] arr = {-1, 2, -3, 4, -5};
    System.out.println("Array before product: " + (Arrays.toString(arr)));
    int[] prodArray = findProduct(arr);
    System.out.println("Array after product: " + (Arrays.toString(prodArray)));
  }
} 