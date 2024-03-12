package com.string.examples;

public class FindFirstNonRepeatingCharacter {

    public static void main(String[] args) {
        String input = "teeter";

        for (char i : input.toCharArray()) {
            if (input.indexOf(i) == input.lastIndexOf(i)) {
                System.out.println(i);
                break;
            }
        }
    }
}
