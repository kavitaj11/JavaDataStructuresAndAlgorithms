package com.algorithms.dynamicProgramming;

/*
Given an unlimited supply of coins of given denominations,
 find the total number of distinct ways to get the desired change.

Suppose we have coin denominations of [1, 2, 5]and the total amount is 7.
We can make changes in the following 6 ways:
Denominations: 1, 2, 5
Amount:7
No. of ways to make the change: (1, 1, 1, 1, 1, 1, 1),(1, 1, 1, 1, 1, 2),(1, 1, 1, 2, 2),
 (1, 2, 2, 2),(1, 1, 5),(2, 5)
Total Methods:6

Solution: The idea is to use recursion to solve this problem.
We recur to see if the total can be reached by choosing the coin or not
for each coin of given denominations.
If choosing the current coin results in solution, update the total number of ways.
 1. Base Case: if the total amount becomes 0, return 1
 2. return 0 if total amount becomes negative (amount <0)
    initialize the total number of ways to 0
        int result = 0;
 3. foreach (coin: coinset)
    result + = countWaysToGetChange(coinset, amount-coin)
 However, this solution does not always result in distinct sets because
 it counts the sets which are permutations of each other.
 Example: For coin denominations of [1, 2, 3] and the total amount=4,
 it returns 7 ways as some ways are permutations of each other.
 {1, 1, 1, 1}
{1, 1, 2}, {2, 1, 1}, {1, 2, 1}
{2, 2}
{1, 3}, {3, 1}

How can we get distinct ways?
The idea is somewhat similar to knapsack problem.
We can recursively define the problem as:
countWaysToGetChange(coinset, coinIndex, amount) =
countWaysToGetChange(coinset, coinIndex, amount-coin)
+ countWaysToGetChange(coins, coinIndex-1, amount);

That is for each coin:
1. Include current coin coin[n] in solution
and recur with remaining change amount-coin[n] with the same coin
2. Excluse current coin coin[n] from solution and recur with remaining n-1 coins.
Finally return the total ways by including and excluding the current coin.
The recursion's base case is
 when solution is found (i. e. amount becomes 0)
 or the solution doesn't exists ( when no coins are left,  or amount becomes negative)

The time complexity of this solution is exponential and requires auxiliary space in call stack.
*/

import java.util.HashMap;
import java.util.Map;

class CoinChanging{


  // Function to find the total number of distinct ways to get
  // a change of `N` from an unlimited supply of coins in set `S`
  public static int getCoinChangeCombination(int[] coins, int coinIndex, int amount)
  {
    // if the total is 0, return 1 (solution found)
    if (amount == 0) return 1;

    // return 0 (solution does not exist) if total becomes negative,
    // no coins are left
    if (amount < 0 || coinIndex < 0) return 0;

    // Case 1. Include current coin `S[n]` in solution and recur
    // with remaining change `N-S[n]` with the same number of coins
    int incl = getCoinChangeCombination(coins, coinIndex, amount - coins[coinIndex]);

    // Case 2. Exclude current coin `S[n]` from solution and recur
    // for remaining coins `n-1`
    int excl = getCoinChangeCombination(coins, coinIndex - 1,amount );

    // return total ways by including or excluding current coin
    return incl + excl;
  }

  /*
  Top-down approach for solving this problem using dynamic programming:
  This problem has optimal substructure and overlapping subproblems,
  so it can be solved using dynamic programming
   where the subproblem solutions are memoized rather than computed again and again.

   The time coplexity of this top-down solution is O(number of coins * amount)
   The auxiliary space required by the program is also O(number of coins * amount)
   */
  // Function to find the total number of distinct w ays to get a change of given amount
  // from an unlimited supply of coins.
  public static int count(int[] coins, int coinIndex, int amount, Map<String, Integer> lookup)
  {
    // if the total is 0, return 1 (solution found)
    if (amount == 0) return 1;

    // return 0 (solution does not exist) if total becomes negative,
    // no elements are left
    if (amount < 0 || coinIndex < 0) return 0;

    // construct a unique map key from dynamic elements of the input
    String key = coinIndex + "|" + amount;

    // if the subproblem is seen for the first time, solve it and
    // store its result in a map
    if (!lookup.containsKey(key)) {
      // Case 1. Include current coin `S[n]` in solution and recur
      // with remaining change `N-S[n]` with the same number of coins
      int include = count(coins, coinIndex, amount - coins[coinIndex], lookup);

      // Case 2. Exclude current coin `S[n]` from solution and recur
      // for remaining coins `n-1`
      int exclude = count(coins, coinIndex - 1, amount, lookup);

      // assign total ways by including or excluding current coin
      lookup.put(key, include + exclude);
    }
    // return solution to the current subproblem
    return lookup.get(key);
  }


  //Bottom-up approach for solving this problem using dynamic programming:
  // Function to find the total number of distinct ways to get a change of `N`
  // from an unlimited supply of coins in set `S`
  // The time coplexity of this top-down solution is O(number of coins * amount)
  // The auxiliary space required by the program is also O(number of coins * amount)

  public static int count(int coins[], int amount) {
    int numberOfCoins = coins.length;
    int table[][] = new int[numberOfCoins + 1][amount + 1];

    for (int i = 0; i <= numberOfCoins; i++) {
      table[i][0] = 1;
    }
    for (int i = 1; i <= numberOfCoins; i++) {
      for (int j = 1; j <= amount; j++) {
        if (coins[i - 1] > j) {
          table[i][j] = table[i - 1][j];
        }
        else {
          table[i][j] = table[i - 1][j] + table[i][j - coins[i - 1]];
        }
      }
    }
    return table[numberOfCoins][amount];
  }

/*Simplified algorithm with space complexity reduced to O(n).

To solve this problem, we’ll keep an array of size amount + 1.
 One additional space is reserved because we also want to store the solution for the 0 amount.

There is only one way you can make a change of 0,
i.e., select no coin so we’ll initialize solution[0] = 1.
We’ll solve the problem for each amount, denomination to amount, using coins up to a denomination, den.

The results of different denominations should be stored in the array solution.

The solution for amount x using a denomination den will then be:

solution[x] = solution[x] + solution[x - den]
We’ll repeat this process for all the denominations,
and at the last element of the solution array, we will have the solution.

Suppose we have coin denominations of [1, 2, 5]
and the total amount is 77. We can make changes in the following 6 ways:
Considering the example mentioned above, the code works in the following way:

Initially solution[0] = 1 and solution[x] = 0 for all x > 0 and x <= amount.

We’ll start with first denomination which is 1 in our example, and will update
solution[x] = solution[x] + solution[x - 1] for all 1 <= x <= amount.

Now, we’ll use second denomination, i.e. 2, in our example.
We’ll compute solution[x] = solution[x] + solution[x - 2] for all 2 <= x <= amount.

At the end, we’ll use third denomination, i.e. 5, in our example.
We’ll compute solution[x] = solution[x] + solution[x - 5] for all 5 <= x <= amount.


Notice the order of the for loops: each time a new coin is considered,
 all of the subsums are updated with the new possibilities opened by it

Runtime complexity #
The runtime complexity of this solution is quadratic, O(m×n),
where m is the number of denominations and n is the total amount.

Memory complexity #
The memory complexity of this solution is linear, O(n),
where n is the total amount.
 */
  static int solveCoinChange(int[] denominations, int amount) {
    int[] solution = new int[amount + 1]; 
    solution[0] = 1;  // Initialize - only one way to make 0 (with no coins)
  
    for (int den: denominations) {   //fo each coin
      for (int i = den; i < (amount + 1); ++i) {  //for each subsum
        solution[i] += solution[i - den]; //add new ways to make amount i
      }
    }
    return solution[solution.length - 1];
  }


  /**
   * This method finds the number of combinations of getting change for
   * a given amount and change coins
   *
   * @param coins The list of coins
   * @param amount The amount for which we need to find the change
   * Finds the number of combinations of change
   **/
  public static int change(int[] coins, int amount) {

    int[] combinations = new int[amount+1];
    combinations[0] = 1;

    for (int coin : coins) {
      for (int i=coin; i<amount+1; i++) {
        combinations[i] += combinations[i-coin];
      }
      // Uncomment the below line to see the state of combinations for each coin
      // printAmount(combinations);
    }

    return combinations[amount];
  }

  public static void main(String[] args)
  {
    // `n` coins of given denominations
    int[] S1 = { 1, 2, 5};
    // total change required
    int N1 = 7;
    System.out.println("The total number of ways to get the desired change is "
            + getCoinChangeCombination(S1, S1.length - 1, N1));

    // `n` coins of given denominations
    int[] S2 = { 1, 2, 3 };
    // total change required
    int N2 = 4;
    // create a map to store solutions to subproblems
    Map<String, Integer> lookup = new HashMap<>();
    System.out.println("The total number of ways to get the desired change is "
            + count(S2, S2.length - 1, N2, lookup));

  }
}

/*
To apply a dynamic programming approach, the problem to be solved must have optimal substructure.
Optimal structure means that the optimal solution of the problem at hand could be achieved by
leveraging the optimal solutions of its subproblems and overlapping sub-problems.
One way to achieve this is by using pre-computed values for solved sub-problems
so that we don’t have to solve them again.

The coin changing problem has both optimal substructure,
meaning that it can be easily divided to simpler problems and they can be solved to find the final solution.
It also satisfies the property of overlapping sub problems,
meaning previously solved sub problem results can be used multiple times.


 */
