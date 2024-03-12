package com.math.examples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class SparseMatrixMultiplication {
    public static int[][] multiply(int[][] A, int[][] B){
        HashMap<Integer, Integer> mapA = new HashMap<>();
        HashMap<Integer, Integer> mapB = new HashMap<>();
        int n = A.length;
        int m = B.length;
        int p = B[0].length;

        int[][] C = new int[n][p];


        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(A[i][j] != 0){
                    mapA.put(i * m + j, A[i][j]);
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < p; j++){
                if(B[i][j] != 0){
                    mapB.put(i * p + j, B[i][j]);
                }
            }
        }
        
        for (Map.Entry<Integer, Integer> itA : mapA.entrySet()){
            for(int j = 0; j < p; ++j){
                if(mapB.containsKey(j + p * (itA.getKey() % m))){
                    int itB = mapB.get(j + p * (itA.getKey() % m));
                    C[itA.getKey() / m][j] += itA.getValue() * itB; 
                }
            }
        }
         
        return C;
    }
    
    public static void main( String args[] ) {
        // Driver Code
        int[][] A = {{1, 0, 0}, {0, 1, 3}};
        int[][] B = {{5, 0, 0}, {0, 0, 0}, {3, 0, 2}};
        System.out.println(Arrays.deepToString(multiply(A, B)));

        int[][] D = {{1, -5}};
        int[][] E = {{12}, {-1}};
        System.out.println(Arrays.deepToString(multiply(D, E)));
        
    }
}