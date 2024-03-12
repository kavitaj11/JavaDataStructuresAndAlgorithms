package com.string.examples;

public class StringPermutations {

    public static void main(String[] args) {
        permutation("123");
    }

    public static void permutation(String input) {
        permutation(" ", input);
    }

    public static void permutation(String perm, String word) {
        if (word.isEmpty()) {
            System.err.println(perm + word);
        } else {
            for (int i = 0; i < word.length(); i++) {
                permutation(perm + word.charAt(i), word.substring(0, i) + word.substring(i + 1));
            }
        }
    }



}
