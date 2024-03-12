package com.math.examples;

import java.util.Arrays;

public class MatricsTranspose {

    public static double[][] transposeMatrix(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }


    public static void main(String[] args) {
        double[][] m1 = {{4, 8}, {0, 2}, {1, 6}};

        System.out.println(Arrays.deepToString(transposeMatrix(m1)));


    }
}
