package com.string.examples;

public class RemoveCharacters {

    //Using Recursion
    public static String removeCharRecursive( String word, char ch){

        int index= word.indexOf(ch);
        if(index==-1){
            return word;
        } return removeCharRecursive(word.substring(0, index)
                + word.substring(index+1), ch);
    }


    //Iterative Method
    public static String removeChars( String word, String remove ) {
        StringBuilder str= new StringBuilder(word);
        boolean[] flags = new boolean[128]; // assumes ASCII
        int src, dst = 0;

        // Set flags for characters to be removed
        for (char c: remove.toCharArray()) {
            flags[c] = true;
        }

        // Now loop through all the characters,
        // copying only if they aren't flagged
        for ( src = 0; src < str.length(); ++src ) {
            char c = str.charAt(src);
            if ( !flags[ c ] ) {
                str.setCharAt( dst++, c );
            }
        }

        str.setLength(dst);
        return str.toString();
    }

}
