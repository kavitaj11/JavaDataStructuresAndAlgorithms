package com.string.examples;

import java.util.Arrays;

public class CheckPermutation {

   boolean permutation(String s, String t){
       if(s.length()!=t.length())
           return false;
       return sort(s).equals(sort(t));
   }

   String sort(String s){
       char[] content=s.toCharArray();
       Arrays.sort(content);
       return new String(content);
   }

}
