package com.algorithms.recursion;// Java code to find the number of
// possible subset with given sum
import java.util.*;
import java.lang.*;
import java.io.*;


/*
For each element in the array, first decide to take it or not in the subset.
 Define a function which will take care of all this.
 The function is called once in the main function.
 The static class fields are declared which will be operated by our function.
 At each call, the function checks for the condition of the fields.
 In our case, it checks if the current sum is equal to the given sum
 and accordingly increment the respective class field.
 If not, it makes function calls by taking all the case.
 So the number of function calls will be equal to the number of cases.
 So here, two calls are made –
one by taking the element in the subset and increment the current sum
and another by not taking the element.
 */
public class SubSetSumRec {
     
    static int count;
    static int sum;
    static int n;

    void countSubsets(int[] arr, int targetsum){
       n=arr.length-1;
       sum=targetsum;
       count=0;
       countSubsetsRec(arr, 0, 0);
    }

    // Function to select or not the array element 
    // to form a subset with given sum
    void countSubsetsRec(int[] arr, int i, int currSum) {
        if (currSum == sum) {
            count++;
            return;
        }
        if (currSum < sum && i < n) {
            countSubsetsRec(arr, i+1, currSum + arr[i]);
            countSubsetsRec(arr, i+1, currSum);
        }
    }

    // Driver code
    public static void main (String[] args) {
        //count = 0;
        //n = 5;
       // sum = 10;
        int[] arr = {2, 3, 5, 6, 8, 10};
       // f(arr, 0, 0);
        SubSetSumRec subSetSumRec=new SubSetSumRec();
        subSetSumRec.countSubsets(arr, 11);

        System.out.println(count);
    }

}