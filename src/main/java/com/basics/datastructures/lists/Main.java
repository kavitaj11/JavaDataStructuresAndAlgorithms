package com.basics.datastructures.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
class MobileKeypadWordCombinations
{
    // Top-down recursive function to find all possible combinations of words formed
    // from the mobile keypad

/*
Complexity Analysis:

Time Complexity: O(4n), where n is a number of digits in the input number.
Each digit of a number has 3 or 4 alphabets, so it can be said that each digit has 4 alphabets as options. If there are n digits then there are 4 options for the first digit and for each alphabet of the first digit there are 4 options in the second digit, i.e for every recursion 4 more recursions are called (if it does not match the base case). So the time complexity is O(4n).
Space Complexity:O(1).
As no extra space is needed.
 */
    public static void findCombinationsRecursive(List<List<Character>> keypad,
                                        int[] input, String result, int index)
    {
        // if we have processed every digit of the key, print the result
        if (index == -1)
        {
            System.out.print(result + " ");
            return;
        }
 
        // stores the current digit
        int digit = input[index];
 
        // one by one, replace the digit with each character in the corresponding
        // list and recur for the next digit
        for (char c: keypad.get(digit)) {
            findCombinationsRecursive(keypad, input, c + result, index - 1);
        }
    }

    // Iterative function to find all possible combinations of words
    // formed from the mobile keypad
    public static void findCombinations(List<List<Character>> keypad, int[] input)
    {
        // list to store combinations of all possible words
        List<String> outputList = new ArrayList<>();

        // push all characters associated with the first digit into the output list
        for (Character ch: keypad.get(input[0])) {
            outputList.add(String.valueOf(ch));
        }

        // start from the second digit
        for (int i = 1; i < input.length; i++)
        {
            // create a temporary list and clear the contents of the output list
            List<String> prevList = new ArrayList<>(outputList);
            outputList.clear();

            // for each character associated with the current digit in the keypad,
            for (Character ch: keypad.get(input[i]))
            {
                // append each word's current character in the output list
                for (String s: prevList) {
                    outputList.add(s + ch);
                }
            }

            // list now contains all possible combinations of words
            // until the current digit
        }

        // print output list containing all combinations of words possible
        System.out.println(outputList);
    }

    public static void main(String[] args)
    {
        List<List<Character>> keypad = Arrays.asList(
                // 0 and 1 digit doesn't have any characters associated
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList( 'A', 'B', 'C' ),
                Arrays.asList( 'D', 'E', 'F' ),
                Arrays.asList( 'G', 'H', 'I' ),
                Arrays.asList( 'J', 'K', 'L' ),
                Arrays.asList( 'M', 'N', 'O' ),
                Arrays.asList( 'P', 'Q', 'R', 'S'),
                Arrays.asList( 'T', 'U', 'V' ),
                Arrays.asList( 'W', 'X', 'Y', 'Z')
        );
 
        // input number in the form of an array (number cannot start from 0 or 1)
        int[] input = { 2, 3, 4 };
 
        // find all combinations
        findCombinationsRecursive(keypad, input, "", input.length - 1);
        findCombinations(keypad, input);
    }
}