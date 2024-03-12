package com.datastructures.HashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//Given an array, find the first integer which is unique in the array using hashing technique.
//The time complexity of this program is O(n)
class CheckFirstUnique {


    /*
    Firstly, we store all the elements from the array into a HashMap.
    The element is stored as key and the count of multiple occurrences is stored as value in the HashMap.
    Initially, the count is 0. But if the same element is encountered again,
    the count is increased by 1 each time.

    Afterward, we traverse the array again from the beginning a
    nd return the first element which has count equal to 0 in the HashMap.
     */
    public static int findFirstUniqueUsingHashMap(int[] arr) {

        Map<Integer, Integer> countElements = new HashMap<>();
        //If the element does not exist in the HashMap
        //Add that element with value = 0
        //or else update the number of occurrences of that element by adding 1
        for (int i = 0; i < arr.length; i++) {
            if(countElements.containsKey(arr[i])){
                int occurence = countElements.get(arr[i]) + 1;
                countElements.put(arr[i], occurence);
            }
            else
                countElements.put(arr[i], 0); 
        }
        //Traverse the array and check the number of occurrences
        //Return the first element with 0 occurences
        for (int i = 0; i < arr.length; i++) {
            if (countElements.get(arr[i]) == 0) {
                return arr[i];
            }
        }
        //If no such element is found, return -1
        return -1;
    }

    public static int findFirstUniqueUsingTreeMap(int[] arr) {

            Map<Integer, Integer> countElements = new TreeMap<>(); //TreeMap is sorted
            //If the element does not exist in the TreeMap
            //Add that element with value = 0
            //or else update the number of occurrences of that element by adding 1
            for (int i = 0; i < arr.length; i++) {
                if (countElements.containsKey(arr[i])) {
                    int occurence = countElements.get(arr[i]) + 1;
                    countElements.put(arr[i], occurence);
                } else
                    countElements.put(arr[i], 0);
            }
            //Traverse the array and check the number of occurrences
            //Return the first element with 0 occurences
            for (int i = 0; i < arr.length; i++) {
                if (countElements.get(arr[i]) == 0) {
                    return arr[i];
                }
            }
            //If no such element is found, return -1
            return -1;
        }



    public static void main(String args[]) {

        int[] arr = {2, 54, 7, 2, 6, 54};

        System.out.println("Array: " + Arrays.toString(arr));

        int unique = findFirstUniqueUsingHashMap(arr);
        System.out.print("First Unique in an Array: " + unique);

    }
}