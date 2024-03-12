package com.string.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePermutation {

    //Time complexity: O(n), where n is the length of the string
    static int NO_OF_CHARS = 256;
    /* function to check whether characters
    of a string can form a palindrome */
    static boolean canFormPalindrome1(String str) {
        // Create a count array and initialize all values as 0
        int count[] = new int[NO_OF_CHARS];
        Arrays.fill(count, 0);

        // For each character in input strings, increment count in the corresponding count array
        for (int i = 0; i < str.length(); i++)
            count[(int)(str.charAt(i))]++;

        // Count odd occurring characters
        int odd = 0;
        for (int i = 0; i < NO_OF_CHARS; i++) {
            if ((count[i] & 1) == 1)
                odd++;

            if (odd > 1)
                return false;
        }

        // Return true if odd count is 0 or 1,
        return true;
    }

    //Time complexity: O(n), where n is the length of the string
    static boolean canFormPalindrome2(String str) {
        // Create a list
        List<Character> list = new ArrayList<Character>();
        // For each character in input strings,
        // remove character if list contains
        // else add character to list
        for (int i = 0; i < str.length(); i++)
        {
            if (list.contains(str.charAt(i)))
                list.remove((Character)str.charAt(i));
            else
                list.add(str.charAt(i));
        }

        // if character length is even list is expected to be empty or
        // if character length is odd list size is expected to be 1
        if (str.length() % 2 == 0 && list.isEmpty()
                || (str.length() % 2 == 1 && list.size() == 1))
            return true;

            // if string length is odd
        else
            return false;
    }


    // Driver code
    public static void main(String args[])
    {
        if (canFormPalindrome1("aabbbaa"))
            System.out.println("Yes");
        else
            System.out.println("No");
        if (canFormPalindrome2("aabbbaac"))
            System.out.println("Yes");
        else
            System.out.println("No");
    }



}



