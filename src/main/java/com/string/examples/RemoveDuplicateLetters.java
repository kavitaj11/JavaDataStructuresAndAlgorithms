package com.string.examples;

import java.util.Iterator;
import java.util.LinkedHashSet;


public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        String str = "AbraCadABraAlakAzam";
        String s1="*Fjgfvj&***";
        removeDupLetters(str);
        removeDupLetters(s1);
    }


    public static void removeDupLetters(String s) {
        //To remove special char: punctuations and symbols
        String s1=s.replaceAll("[^\\p{L}\\p{Z}]","");
        char a[] = s1.toCharArray();
        StringBuffer sb = new StringBuffer();
        //Using LinkedHashSet to preserve the order so that the set will keep only the first occurrence of a letter
        LinkedHashSet<Character> hs = new LinkedHashSet<>();
        for (int i = 0; i < a.length; i++) {
           if((!hs.contains(Character.toLowerCase(a[i])) && (!hs.contains(Character.toUpperCase(a[i]))))) {
               hs.add(a[i]);
           }
        }

        Iterator<Character> itr = hs.iterator();
        while (itr.hasNext()) {
            char o = itr.next();
            if (o != ' ')
            {
                sb.append(o);
            }
        }
        System.out.println(sb);
    }
}


