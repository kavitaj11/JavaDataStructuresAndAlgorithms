//package com.basics.algorithms.dynamicProgramming;

import java.util.*;

public class LCS2 {

    private static int lcs2(int[] a, int[] b) {
        int n=a.length, m=b.length;
        return lcsRecursive(a, b, n, m);
    }

    private static int lcsRecursive(int[] a, int[] b, int n, int m){
        if(n == 0 || m == 0)
        {
            return 0;
        }
        else
        {
            if(a[n-1] == b[m-1])
            {
                return (1+ lcsRecursive(a,b,n-1,m-1));
            }
            else
            {
                return Math.max(lcsRecursive(a,b,n,m-1) , lcsRecursive(a,b,n-1,m));
            }
        }
    }

    static int lcsIterative(int[] a, int[] b){
        int m = a.length;
        int n = b.length;
        int[][] commonArray = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(j==0 || i==0)
                    commonArray[i][j] = 0;
                else if(a[i-1] == b[j-1])			//if both characters are same
                    commonArray[i][j] = commonArray[i-1][j-1] + 1;
                else												//if both characters are not same
                    commonArray[i][j] = Math.max(commonArray[i-1][j], commonArray[i][j-1]);
            }
        }
        return commonArray[m][n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcsIterative(a, b));
    }
}

