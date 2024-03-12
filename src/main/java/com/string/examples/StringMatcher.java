package com.string.examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatcher {

    public static int findPattern(String seq, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(seq);
        int count = 0;
        while( m.find() ) count++;
        return count;
    }


    public static void main(String[] args){
        String seq = "CGTATCCCACAGCACCACACCCAACAACCCA";
        String regex= "A{1}C{2}";
        int count= findPattern(seq, regex );
        System.out.println("there are " + count + " ACC");
    }
}
