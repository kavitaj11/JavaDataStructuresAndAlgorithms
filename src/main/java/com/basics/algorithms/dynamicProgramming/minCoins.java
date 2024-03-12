package com.basics.algorithms.dynamicProgramming;

import java.util.Scanner;

public class minCoins {

    public static int getMinNumberOfCoins(int[] coins, int amount){
// amount:37
// coins[]: {1, 5, 10, 21, 25}

        int[] minCoins=new int[amount+1];  // combinations[38]?
        minCoins[0]=0;
        for(int i=1; i<=amount; i++){
            minCoins[i]=Integer.MAX_VALUE;
        }

        for(int i=1; i<=amount; i++){
            for(int coin: coins){
                if(coin<=i){
                    if(minCoins[i-coin]==Integer.MAX_VALUE){
                        continue;
                    }
                    minCoins[i]=Math.min(minCoins[i], minCoins[i - coin] + 1);
                 }
                }
            }
        if(minCoins[amount]==Integer.MAX_VALUE){
            return -1;
        }
        return minCoins[amount];
        }

    public static void main(String[] args) {
        int[] coins={5, 10, 15, 25, 35};
        System.out.println(getMinNumberOfCoins(coins, 49));
    }
    }
