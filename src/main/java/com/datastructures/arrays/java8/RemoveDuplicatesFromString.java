package com.datastructures.arrays.java8;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RemoveDuplicatesFromString {

public static void main(String[] args){
String str= "The second second was tough";
str= Arrays.stream(str.split("\\s+")).distinct().collect(Collectors.joining(" "));

System.out.println(str);
}}