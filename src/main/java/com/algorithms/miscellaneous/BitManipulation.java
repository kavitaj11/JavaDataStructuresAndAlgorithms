package com.algorithms.miscellaneous;
//Topics: Hash Table, Bit Manipulation


// Java
class  BitManipulation {
    /*
    Given a non-empty array of integers, every element appears twice except for one. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
Example 1:
Input: [2,2,1]
Output: 1
Example 2:
Input: [4,1,2,1,2]
Output: 4
*/
    public int singleNumber(int[] nums) {
        int xor =0;
        int n = nums.length;
        for(int i=0;i<n;i++){
            xor ^= nums[i];
        }
        return xor;
    }

    //Write a function that takes an unsigned integer
    // and return the number of '1' bits it has (also known as the Hamming weight).
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ans=0;

        //The key idea here is to realize that for any number n,
        // doing a bit-wise AND of n and n - 1 flips the least-significant 1-bit in n to 0
        while(n!=0){
            ans++;
            n&=(n-1);
        }
        return ans;
    }
}

