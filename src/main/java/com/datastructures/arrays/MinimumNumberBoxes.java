package com.datastructures.arrays;

import java.util.*;

/*
Given N boxes and their size in an array.
You are allowed to keep a box inside another box only if the box in which it is held is empty
 and the size of the box is at least twice as large as the size of the box.
The task is to find minimum number of visible boxes.

The idea is to sort the array. Now, make a queue and insert first element of sorted array.
 Now traverse the array from first element and insert each element in the queue,
also check if front element of queue is less than or equal to half of current traversed element.
So, the number of visible box will be number of element in queue after traversing the sorted array.
Basically, we are trying to put a box of size in smallest box which is greater than or equal to 2*x.
For example, if arr[] = { 2, 3, 4, 6 }, t
hen we trying to put box of size 2 in box of size 4 instead of box of size 6
because if we put box of size 2 in box of size 6 then box of size 3 cannot be kept in any other box
 and we need to minimize the number of visible box.

 Time Complexity: O(nlogn)
 */
public class MinimumNumberBoxes {

    //return the minimum number of visible boxes
    static int minimumBox(int []arr) {
        // New Queue of integers.
        Queue<Integer> q = new LinkedList<>();
        // sorting the array
        Arrays.sort(arr);
        q.add(arr[0]);
        // traversing the array
        for (int i = 1; i < arr.length; i++) {
            int now = q.element();
            // checking if current element is greater than or equal to twice of front element
            if (arr[i] >= 2 * now)
                q.remove();
            // Pushing each element of array
            q.add(arr[i]);
        }
        return q.size();
    }

    public static void main(String[] args){
        int[] arr={1, 3, 4 , 5, 6};
        int n = arr.length;
        System.out.println(minimumBox(arr));
    }
}

