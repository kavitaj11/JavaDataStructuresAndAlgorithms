package com.string.examples;

public class ReverseLastWord {
    public static void main(String[] args) {
        String sentence = "Recognized by the management team at the client location for excellent performance and significant contribution in improving overall test automation speed and quality for 2000+ test scripts created as part of the Equity Edge 2.0 project.";

        // Splitting the sentence into words
        String[] words = sentence.split("\\s+");
        
        // Getting the last word
        String lastWord = words[words.length - 1];

        // Reversing the last word
        StringBuilder reversedLastWord = new StringBuilder(lastWord);
        reversedLastWord.reverse();

        // Concatenating "Java" to the reversed last word
        String result = reversedLastWord.toString() + "Java";

        System.out.println("Result: " + result);
    }
}