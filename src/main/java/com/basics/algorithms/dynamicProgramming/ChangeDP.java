package com.basics.algorithms.dynamicProgramming;
/*
Problem: As we already know, a natural greedy strategy for the change problem does not work correctly
for any set of denominations.
 For example, if the available denominations are 1, 3, and 4,
 the greedy algorithm will change 6 cents using three coins (4 + 1 + 1)
 while it can be changed using just two coins (3 + 3).
 Your goal now is to apply dynamic programming for solving the Money Change Problem for denominations 1, 3, and 4.
*/

import java.util.HashMap;
import java.util.Scanner;

// * Coin changing minimum number of coins problem by Dynamic programming approach
// * Top down memoization approach
// *
// * To find the minimum number of coins to form the given total
// *
// * Time complexity is O(total*number of coins)
// * Space complexity is O(total)
// */

public class ChangeDP {
    private static int getChange(int m) {
        //write your code here
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] coins = {1, 3, 4};
        return getMinimumCoinsTopDown(m,coins,map);
        //return getMinimumCoinsBottomUp(coins,m);
        
    }

    public static int getMinimumCoinsTopDown(int total, int[] coins, HashMap<Integer, Integer> map) {

        //if total is 0 then there is nothing to do so return 0
        if (total == 0) {
            return 0;
        }

        //if the current total is already a total pre-calculated then return the value associated with it
        if (map.containsKey(total)) {
            return map.get(total);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            //if coin's value is greater than total then skip this iteration
            if (coins[i] > total) {
                continue;
            }
            //go into recursion and calculate minimum for new total
            int value = getMinimumCoinsTopDown(total - coins[i], coins, map);
            //update the new minimum if needed
            if (value < min) {
                min = value;
            }
        }
        //if min was updated then that meaans the coin was selected so +1
        min = (min == Integer.MAX_VALUE ? min : min + 1);
        //add to the map to prevent re-calculated or going into same recursion again
        map.put(total, min);

        return min;
    }

    static int getMinimumCoinsBottomUp(int[] coins, int number) {
        int size = coins.length;
        int[][] matrixArray = new int[size][number + 1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= number; j++) {
                if (j == 0) // total is zero then no coin needed
                    matrixArray[i][j] = 0;
                else if (j < coins[i]) { // total is less than the current coin
                    // denomination
                    if (i == 0)
                        matrixArray[i][j] = 0;
                    else
                        matrixArray[i][j] = matrixArray[i - 1][j];
                } else { // total is greater than or equal to the current coin
                    // denomination
                    if (i == 0) {
                        if (j == coins[i]) // total is same as the current coin
                            matrixArray[i][j] = 1;
                        // else //total is greater than the current coin
                        // matrixArray[i][j] = j ;
                    } else
                        matrixArray[i][j] = Math.min(matrixArray[i - 1][j], matrixArray[i][j - coins[i]] + 1);
                }
            }
        }
        return matrixArray[size - 1][number];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

