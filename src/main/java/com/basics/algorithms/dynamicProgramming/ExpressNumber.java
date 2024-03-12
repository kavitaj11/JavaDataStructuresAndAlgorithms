package com.basics.algorithms.dynamicProgramming;

class ExpressNumber {
  public int CountWays(int n) {
    if( n <= 2 ) return 1;
    if( n == 3 ) return 2;
    int dp[] = new int[n+1];
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 1;
    dp[3] = 2;
    for(int i=4; i<=n; i++)
      dp[i] = dp[i-1] + dp[i-3] + dp[i-4];
    return dp[n];
  }


  /*public int CountWays(int n) { //time and space complexity of O(n)
    int dp[] = new int[n+1];
    dp[0] = 1;  dp[1] = 1; dp[2] = 2;
    for(int i=3; i<=n; i++)
      dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
    return dp[n];
  }*/
  public int CountWaysOptimized(int n) { //space complexity of O(1)
    if (n < 2) return 1;
    if (n == 2) return 2;
    int n1=1, n2=1, n3=2, temp;
    for(int i=3; i<=n; i++) {
      temp = n1 + n2 + n3;
      n1 = n2;
      n2 = n3;
      n3 = temp;
    }
    return n3;
  }

  public static int scoringOptionsRec(int n, int[] result) {
    if(n < 0) return 0;
    if(result[n] > 0)  return result[n];
    //Memoize
    result[n] = scoringOptionsRec(n - 1, result) +
            scoringOptionsRec(n - 2, result) +
            scoringOptionsRec(n - 4, result);
    return result[n];
  }
  public static int scoringOptions(int n) {
    if(n <= 0)   return 0;
    int[] result = new int[n + 1];
    for (int i = 0; i < n + 1; i++) {
      result[i] = -1;
    }
    result[0] = 1;
    scoringOptionsRec(n, result);
    return result[n];
  }



  // Scoring options are 1, 2, and 4
  public static int scoringOptionsDynamic(int n) {
    if(n <= 0) return 0;
    // Max score is 4. Hence we need to save last 4 ways to calculate the number of ways
    // for a given n
    int[] result = new int[4];
    //save the base case on last index of the vector
    result[3] = 1;
    for(int i = 1; i <= n; i++) {
      int sum = result[3] + result[2] + result[0];
      //slide left all the results in each iteration
      //result for current i will be saved at last index
      result[0] = result[1];
      result[1] = result[2];
      result[2] = result[3];
      result[3] = sum;
    }
    return result[3];
  }
  public static void main(String[] args) {
    ExpressNumber en = new ExpressNumber();
    System.out.println(en.CountWays(4));
    System.out.println(en.CountWays(5));
    System.out.println(en.CountWays(6));
  }
}