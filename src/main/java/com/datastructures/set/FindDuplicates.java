package com.datastructures.set;

import java.util.*;

public class FindDuplicates {

    public static void findDuplicates(String[] arr) {
        List<String> list =  Arrays.asList(arr);
        Set<String> uniqueSet = new HashSet<>(list);
        for (String temp : uniqueSet) {
            System.out.println(temp + ": " + Collections.frequency(list, temp));
        }
    }


    public static void main(String args[]) {
        String[] names = {"Java", "JavaScript", "Python", "C", "Ruby", "Java"};
        findDuplicates(names);

    }


    }
