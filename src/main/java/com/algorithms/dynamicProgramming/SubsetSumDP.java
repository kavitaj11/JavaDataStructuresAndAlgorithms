package com.algorithms.dynamicProgramming;

public class SubsetSumDP {
 /*
Check if there is a subset of given set with sum equal to given sum
Base Cases:
If no elements in the set then we can’t make any subset except for 0.
If sum needed is 0 then by returning the empty subset we can make the subset with sum 0.
Given – Set = arrA[], Size = n, sum = S
Now for every element in he set we have 2 options, either we include it or exclude it.
for any ith element-
If include it => S = S-arrA[i], n=n-1
If exclude it => S, n=n-1.
Recursive Equation:
Base Cases:
SubsetSumDynamic(arrA, n, S)= false, if sum > 0 and n == 0 SubsetSumDynamic(arrA, n, S)= true, if sum == 0 (return empty set)
Rest Cases
SubsetSumDynamic(arrA, n, S) = SubsetSumDynamic(arrA, n-1, S)|| SubsetSumDynamic(arrA, n-1, S-arrA[n-1])

Time Complexity: O(sum*n), where sum is the ‘target sum’ and ‘n’ is the size of array.
Auxiliary Space: O(sum*n), as the size of 2-D array is sum*n.
     */
    public static boolean subSetDP(int[] A, int sum) {
        boolean[][] solution = new boolean[A.length + 1][sum + 1];
        // if sum is not zero and subset is 0, we can't make it
        for(int i=1;i<=sum;i++){
            solution[0][i]=false;
        }
        // if sum is 0 the we can make the empty subset to make sum 0
        for(int i=0;i<=A.length;i++){
            solution[i][0]=true;
        }
        //
        for(int i=1;i<=A.length;i++){
            for(int j=1;j<=sum;j++){
                //first copy the data from the top
                solution[i][j] = solution[i-1][j];

                //If solution[i][j]==false check if can be made
                if(!solution[i][j] && j>=A[i-1])
                    solution[i][j] = solution[i][j] || solution[i-1][j-A[i-1]];
            }
        }
        return solution[A.length][sum];
    }


    /*
    Count number of subsets that add upto target sum in the given array
    time and space complexity of O(N*S),
    where ‘N’ represents total numbers and ‘S’ is the desired sum.
     */
    public static int countSubsets(int[] num, int sum) {
        int n = num.length;
        int[][] dp = new int[n][sum + 1];
        // populate the sum=0 columns, as we will always have an empty set for zero sum
        for(int i=0; i < n; i++)
            dp[i][0] = 1;
        // with only one number, we can form a subset only when the required sum is equal to its value
        for(int s=1; s <= sum ; s++) {
            dp[0][s] = (num[0] == s ? 1 : 0);
        }
        // process all subsets for all sums
        for(int i=1; i < num.length; i++) {
            for(int s=1; s <= sum; s++) {
                // exclude the number
                dp[i][s] = dp[i-1][s];
                // include the number, if it does not exceed the sum
                if(s >= num[i])
                    dp[i][s] += dp[i-1][s-num[i]];
            }
        }
        // the bottom-right corner will have our answer.
        return dp[num.length-1][sum];
    }

    public static void main(String[] args) {
        int[] A = { 3, 2, 7, 1,8, 6};
        System.out.println("\nFrom DP: " + subSetDP(A, 6) );
        System.out.println("\nFrom DP: " + countSubsets(A, 9) );
        System.out.println("\nFrom DP: " + countSubsets(A, 5) );
    }
}