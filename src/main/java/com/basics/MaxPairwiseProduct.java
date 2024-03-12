//package com.basics;

import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {

    //Solution#1: Naive Solution
    static int getMaxPairwiseProductSol1(int[] numbers) {
        int max_product = 0;
        int n = numbers.length;

        for (int first = 0; first < n; ++first) {
            for (int second = first + 1; second < n; ++second) {
                max_product = Math.max(max_product,
                        numbers[first] * numbers[second]);
            }
        }

        return max_product;
    }

    //Solution#1: Sorting array to get first and second max
    static long getMaxPairwiseProductSol2(int[] numbers) {
        long max_product = 0;
        int n = numbers.length;
        // Sort the array
        Arrays.sort(numbers);
        long num1, num2;

        // Calculate product of two smallest numbers
        long sum1 = numbers[0] * numbers[1];

        // Calculate product of two largest numbers
        long sum2 = numbers[n - 1] * numbers[n - 2];

        // print the pairs whose product is greater
        if (sum1 > sum2) {
            num1 = numbers[0];
            num2 = numbers[1];
        }
        else {
            num1 = numbers[n - 2];
            num2 = numbers[n - 1];
        }
        max_product= num1*num2;
        return max_product;
    }


    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProductSol2(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                        InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}

