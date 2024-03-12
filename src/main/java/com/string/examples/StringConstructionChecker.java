package com.string.examples;

import java.util.HashMap;
import java.util.Map;

public class StringConstructionChecker {
    
    public static boolean canConstruct(String str1, String str2) {
        if (str1.length() > str2.length())
            return false;

        Map<Character, Integer> charFrequency = new HashMap<>();
        
        // Count the frequency of characters in str2
        for (char c : str2.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }
        
        // Check if str1 can be constructed from str2
        for (char c : str1.toCharArray()) {
            if (!charFrequency.containsKey(c) || charFrequency.get(c) == 0)
                return false;
            charFrequency.put(c, charFrequency.get(c) - 1);
        }
        
        return true;
    }

    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "worldhello";
        System.out.println("Can \"" + str1 + "\" be constructed from \"" + str2 + "\"? " + canConstruct(str1, str2)); // Output: true
        
      }
}