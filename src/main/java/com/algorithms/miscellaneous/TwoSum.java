package com.algorithms.miscellaneous;

import java.util.*;

class TwoSum {

    /*
Use a HashMap (Most efficient)
You can use a HashMap to solve the problem in O(n) time complexity. Here are the steps:

Initialize an empty HashMap.
Iterate over the elements of the array.
For every element in the array -If the element exists in the Map,
then check if it’s complement (target - element) also exists in the Map or not.
 If the complement exists then return the indices of the current element and the complement.
Otherwise, put the element in the Map, and move to the next iteration.
Time complexity: O(n)

*/

    private static int[] findTwoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), complement };
            } else {
                numMap.put(nums[i], i);
            }
        }
        return new int[] {};
    }

   /*
   Use Sorting along with the two-pointer sliding window approach
There is another approach which works when you need to return the numbers instead of their indexes. Here is how it works:

Sort the array.
Initialize two variables, one pointing to the beginning of the array (left) and another pointing to the end of the array (right).
Loop until left < right, and for each iteration
if arr[left] + arr[right] == target, then return the indices.
if arr[left] + arr[right] < target, increment the left index.
else, decrement the right index.
This approach is called the two-pointer sliding window approach. It is a very common pattern for solving array related problems.
   Time complexity: O(n*log(n))

    */
    private static int[] findTwoSum_Sorting(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;

        while(left < right) {
            if(nums[left] + nums[right] == target) {
                return new int[] {nums[left], nums[right]};
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {};
    }

    //We iterate only once through the array --> O(n) time
    //Insertion and lookup time in Hash is O(1).
    private static Map<Integer, Integer> numberPairsForSum(int[] array, int sum) {
        HashSet<Integer> set = new HashSet<Integer>();
        Map<Integer, Integer> numMap=new HashMap<>();
        for (int num : array) {
            if (set.contains(sum - num)) {
                numMap.put(num, sum-num);
            }
            set.add(num);
        }
        if(numMap.size()==0){
            System.out.println("No Matching Pairs For Given Sum Foundin Array");
        }
        return numMap;
    }

    public static void main(String []args){
        int[] arr = {3, 7, 1, 2, 4, 4, 5};
        System.out.println("Original Array: " + Arrays.toString(arr));
        System.out.println("Sum 20 exists " + Arrays.toString(findTwoSum(arr, 9)));
        System.out.println("Sum exists " + Arrays.toString(findTwoSum(arr, 12)));
        System.out.println("Sum exists " + Arrays.toString(findTwoSum_Sorting(arr, 9)));
        System.out.println("Sum exists " + numberPairsForSum(arr, 8).toString());
    }
}