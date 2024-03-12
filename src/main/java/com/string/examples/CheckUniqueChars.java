package com.string.examples;

public class CheckUniqueChars{

public static boolean isUniqueChars(String str) {
    if(str.length()>128) return false;
    boolean[] char_set=new boolean[128]; //Assuming the string is an ASCII string
    for(int i=0; i<=str.length()-1; ++i) {
        int val = str.charAt(i);
        if (char_set[val]) {
            return false;
        }
        char_set[val]=true;
    }
    return true;
}

public static void main(String[] args){
    System.out.println(isUniqueChars("palindrome"));
    System.out.println(isUniqueChars("umbrella"));
}
//Time complexity= O(n) where n is the length of the string
    //The space comlexity is O(1)
    //actual complexity: O(c) space and O(min(c,n),
        // where c is the size of the char set which is 128 for ASCII.

}
