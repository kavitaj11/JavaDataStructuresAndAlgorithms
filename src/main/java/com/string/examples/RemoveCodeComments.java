package com.string.examples;

import java.util.ArrayList;
import java.util.Arrays;

//Time complexity #
//The time complexity is O(n).
// n is the total length of the source code because we process it character by character.
//
//Space complexity #
//The space complexity is O(n), which is the space used for storing the source code in the output list.

class RemoveCodeComments {
    public static String[] removeComments(String[] source){
        ArrayList<String> output = new ArrayList<>();
        String buffer = "";
        boolean block = false;
        for(String line: source){
            int i = 0;
            while(i < line.length()){
                char c = line.charAt(i);
                // "//" -> inline comment.
                if(c == '/' && (i + 1) < line.length() && line.charAt(i + 1) == '/' && !block){
                    i = line.length(); // Advance pointer to end of current line.
                }
                // "/*" -> start of block comment.
                else if( c == '/' && (i + 1) < line.length() && line.charAt(i + 1) == '*' && !block){
                    block = true;
                    i++;
                }
                // "*/" -> end of block comment.
                else if( c == '*' && (i + 1) < line.length() && line.charAt(i + 1) == '/' && block){
                    block = false;
                    i++;
                }
                // normal cacter -> Append to buffer if not in block comment.
                else if(!block){
                    buffer += c;
                }
                i++;
            }
            if(buffer!= "" && !block){
                output.add(buffer);
                buffer = "";
            }
        }
        return output.toArray(new String[output.size()]); 
    }
    public static void main( String args[] ) {
        String[] source = {"/* Example code for feature */", 
            "int main() {", 
            "  /*", 
            "  This is a", 
            "  block comment", 
            "  */", 
            "  int value = 10;  // This is an inline comment", 
            "  int sum = value + /* this is // also a block */ value;", 
            "  return 0;", 
            "}"};
        String[] output = removeComments(source);
        System.out.println(Arrays.toString(output));
        
    }
}