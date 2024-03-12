package com.math.examples;

import java.util.Scanner;

public class RecArmstrong {

    public static long arm(long n){
        long temp, sum, digits = 0;
        long remainder;
        temp = n;
        sum = 0;

        if (temp == 0)
            return 0; //base case
        else{
            while (temp != 0){
                digits++; //number of digits for exponent
                temp = temp / 10;
            }
            temp = n; //set temp back to original number
            while (temp != 0){
                remainder = temp % 10;
                sum += Math.pow(remainder, digits);
                temp = temp / 10;
            }
            return sum + arm(temp);
        }
    }



    public static long armstrong(int n){
        return armstrong(n, Integer.toString(n).length());
    }
    public static long armstrong(long n, int num_digits){

        if(n==0) //recursion finished
            return 0;
        // n%10 gives last digit
        return (long) (Math.pow(n%10,num_digits) + armstrong(n/10, num_digits));
    }

    public static boolean isArmstrongNumber(String number) {
        int exponent = number.length();
        if (Integer.parseInt(number) == number.chars()
                .map(n -> n - '0')
                .map(n ->(int) Math.pow(Integer.parseInt("" + n),exponent))
                .sum())
            return true;
        else return false;
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = keyboard.nextInt();

        //Error checking
        while(number < 0 || number > 100000){
            System.out.print("Enter a number: ");
            number = keyboard.nextInt();
        }

        if(arm(number) == number)
            System.out.println(number + " is an armstrong number");
        else
            System.out.println(number + " is not an armstrong number.");

        if(armstrong(number) == number)
            System.out.println(number + " is an armstrong number");
        else
            System.out.println(number + " is not an armstrong number. The digit calculation is: "+armstrong(number));


        if(isArmstrongNumber(Integer.toString(number)))
            System.out.println(number + " is an armstrong number");
        else
            System.out.println(number + " is not an armstrong number");

    }

}