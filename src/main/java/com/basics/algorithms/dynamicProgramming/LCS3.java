package com.basics.algorithms.dynamicProgramming;

import java.util.*;

public class LCS3 {

    private static int lcs3(int[] a, int[] b, int[] c) {
        int n = a.length, m = b.length, l = c.length;
        int[][][] table = new int[n + 1][m + 1][l + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for(int k = 0; k <= l; k++) {
                    if(i == 0 || j == 0 || k == 0){
                        table[i][j][k] = 0;
                        continue;
                    }
                    if(a[i - 1] == b [j - 1] && b[j - 1] == c[k - 1]) {
                        table[i][j][k] = table[i - 1][j - 1][k - 1] + 1;
                        continue;
                    }
                    // take max from either of the 3 cases
                    int x = table[i - 1][j][k],
                            y = table[i][j - 1][k],
                            z = table[i][j][k - 1];
                    table[i][j][k] = Math.max(x, Math.max(y, z));
                }
            }
        }
        return table[n][m][l];
        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

