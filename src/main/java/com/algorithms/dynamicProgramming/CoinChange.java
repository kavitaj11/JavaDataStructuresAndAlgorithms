package com.algorithms.dynamicProgramming;


public class CoinChange {

    /**
     * Coin Change-Making Problem:
     * Given an unlimited coins of given denominations,
     * we have to find the minimum numbers of coins required to get the desired change.
     *
     * If the amount cannot be made up by any combination of the given coins, return -1.
     *
     * For example:
     * Given [2, 5, 10] and amount=6, the method should return -1.
     * Given [1, 2, 5] and amount=7, the method should return 2.
     *
     * Solution: The idea is to use recursion to solve this problem.
     * We recur to see if the total can be reached by choosing the coin or not
     * for each coin of given denominations.
     * If choosing the current coin results in solution, update the minimum number of coins needed.
     * Finally return the minimum value we get after exhausting all combinations.
     *
     * @param coins The list of coins
     * @param amount The amount for which we need to find the minimum number of coins.
     * Finds the the minimum number of coins that make a given value.
     * Time complexity of this recursive solution is exponential
     * as each recursive call is making 'n' recursive calls.
     **/
        public static int findMinCoins(int[] coins, int amount) {
            // if the total is 0, no coins are needed
            if (amount == 0) return 0;
            // return infinity if total becomes negative
            if (amount < 0) return Integer.MAX_VALUE;
            // initialize the minimum number of coins needed to infinity
            int mincoins = Integer.MAX_VALUE;
            // do for each coin
            for (int c: coins) {
                // recur to see if total can be reached by including current coin `S[i]`
                int result = findMinCoins(coins, amount - c);
                // update the minimum number of coins needed if choosing the current
                // coin resulted in a solution
                if (result != Integer.MAX_VALUE) {
                    mincoins = Integer.min(mincoins, result + 1);
                }
            }
            // return the minimum number of coins needed
            return mincoins;
        }


    //Bottom-up approach for solving this problem using dynamic programming:
    // Function to find the total number of distinct ways to get a change of `N`
    // from an unlimited supply of coins in set `S`
    //This method finds the minimum number of coins needed for a given amount.
    // The time coplexity of this top-down solution is O(number of coins * amount)
    // The auxiliary space required by the program is also O(number of coins * amount)

    public static int getMinNumberOfCoins(int[] coins, int sum){
        int totalCoins = coins.length;
        // Creating array which stores subproblems' solutions
        double[][] arr = new double[totalCoins + 1][sum + 1];

        // Initialising first row with +ve infinity
        for(int j = 0; j <= sum; j++){
            arr[0][j] = Double.POSITIVE_INFINITY;
        }

        // Initialising first column with 0
        for(int i = 1; i <= totalCoins; i++){
            arr[i][0] = 0;
        }

        // Implementing the recursive solution
        for(int i = 1; i <= totalCoins; i++){
            for(int j = 1; j <= sum; j++){
                if(coins[i - 1] <= j)
                    arr[i][j] = Math.min(1 + arr[i][j - coins[i - 1]], arr[i - 1][j]);
                else
                    arr[i][j] = arr[i - 1][j];
            }
        }

        return (int)arr[totalCoins][sum];
    }

    /*
        Simplified algorithm with space complexity reduced to O(n).

        To solve this problem, we’ll keep an array of size amount + 1.
        One additional space is reserved because we also want to store the solution for the 0 amount.

        There are no coins needed to make a change of 0,
        i.e., select no coin so we’ll initialize solution[0] = 0
        .
        We are looking for the minimum value,
        so we initialize the value to be the largest possible integer value, it can be infinity

        Then we compute minimumCoins[i] for each 1<=i<=amount,
        which stores the minimum number of coins needed to get a total amount i.
        It makes the use of smaller values of minimumCoins[i] and minimumCoins[i-c] already computed.
        if the index [i-c] become negative by including current coin `c, then skip the computation.

         */
        //Time complexity is O(amount * num_of_coins) and space complexity is O(amount).
        public static int minimumCoins(int[] coins, int amount) {
        //minimumCoins[i] will store the minimum coins needed for amount i
        int[] minimumCoins = new int[amount+1];
        //// if the total is 0, no coins are needed
        minimumCoins[0] = 0;
        for(int i=1;i<=amount;i++){
            // initialize the minimum number of coins needed to infinity
            minimumCoins[i]=Integer.MAX_VALUE;
            // do for each coin
            for (int coin :coins){
                // check if the index doesn't become negative by including current coin `c`
                if(i-coin >=0){
                    if(minimumCoins[i-coin]==Integer.MAX_VALUE){
                        continue;
                    }
                    // if total can be reached by including current coin `c`,
                    // update the minimum number of coins needed `minimumCoins[i]
                    minimumCoins[i]=Math.min(minimumCoins[i-coin]+1, minimumCoins[i]);
                    System.out.println(minimumCoins[i]);
                }
            }
        }
        return minimumCoins[amount]==Integer.MAX_VALUE? -1:minimumCoins[amount];
    }


   /*
    A Simplified algorithm where we we initialize the result value
    to be the largest value. It can be amount + 1 as a maximum
   */
    public static int coinChange(int[] coins, int amount) {
        int[] cache = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            // We are looking for the minimum value, we initialize
            // the value to be the largest value it can be + 1 as a maximum
            int minCoins = amount + 1;
            for (int coin : coins) {
                int remain = i - coin;
                // If the coin would give us negative change, we will skip it
                if (remain < 0) {
                    continue;
                }
                // We find the minimum number of coins used in
                // remain, otherwise it'll stay at the same value
                minCoins = Math.min(minCoins, cache[remain] + 1);
            }
            cache[i] = minCoins;
        }
       // Finally, if the answer to our problem is our max value, we know
        // that there must not have been an answer and return -1, otherwise
        // we return the minimum number of coins used
        return cache[amount] == amount + 1 ? -1 : cache[amount];
    }



    // Driver Program
    public static void main(String[] args) {
        int amount = 13;
        int[] coins = {2, 5, 10};
        System.out.println("Minimum number of coins required for amount :" + amount + " is: " + findMinCoins(coins, amount));
        int coins2[]= {1, 5, 10, 21, 25};
        System.out.println("Minimum number of coins required for amount :" + amount + " is: " + getMinNumberOfCoins(coins, amount));
        System.out.println("Minimum number of coins required for amount :" + amount + " is: " + minimumCoins(coins, amount));
        System.out.println("Minimum number of coins required for amount :" + amount + " is: " + coinChange(coins, amount));

    }

}