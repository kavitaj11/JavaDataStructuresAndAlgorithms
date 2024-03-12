package com.datastructures.arrays;

import java.util.Arrays;
import java.util.List;

public class LargestInList {
    public static void main(String[] args) {
        // Example list of arrays
        List<int[]> arraysList = Arrays.asList(
                new int[]{1, 2, 3, 4, 5},
                new int[]{10, 20, 30, 40, 50},
                new int[]{100, 200, 300, 400, 500}
        );

        // Initialize variable to hold the largest element
        int largest = Integer.MIN_VALUE;

        // Iterate through each array in the list
        for (int[] array : arraysList) {
            // Iterate through each element in the array
            for (int element : array) {
                // Check if the current element is larger than the current largest element
                if (element > largest) {
                    largest = element; // Update the largest element
                }
            }
        }

        // Print the largest element
        System.out.println("Largest element in the list of arrays: " + largest);
    }
}
