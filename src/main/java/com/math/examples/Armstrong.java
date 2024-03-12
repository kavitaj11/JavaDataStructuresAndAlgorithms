package com.math.examples;

class Armstrong{
    
    boolean checkArmstrong(int x){ 
        int numberOfDigits = 0, temp = x;
        while(temp!=0){
            numberOfDigits++;
            temp = temp/10;
        }
        int sum=0;
        int num = x;
        while(num!=0){ 
            int digit = num%10; 
            sum += Math.pow(digit, numberOfDigits);
            num = num/10; 
        }
        return(sum == x); 
    } 
  
    public static void main(String[] args){ 
        Armstrong a = new Armstrong(); 
        int n = 153;
        if(a.checkArmstrong(n))
            System.out.println("Yes");
        else
            System.out.println("No");
    } 
}