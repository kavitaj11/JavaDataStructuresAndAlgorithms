package com.algorithms.divideAndConquer;

import java.util.Random;

//The Euclidean algorithm is based on the principle that
// the greatest common divisor of two numbers does not change
// if the larger value of the two is replaced by the difference between both numbers.
/*
Let’s look at an example:

The GCD of 252252 and 105105 is 2121 (252252 = 2121 x 1212 and 105105 = 2121 x 55).
 Now, 2121 is also the GCD of 105105 and 147147 (252 - 105252−105).
 Replacing the number with the larger value (initially 252252) with the difference between them (initially 147147)
 results in the larger number being reduced to a smaller value.
 Repeatedly performing this reduction results in successively smaller pairs of numbers
  that eventually equate to each other.
When this occurs, the final number is the GCD of the two original numbers we started with.
 */
class EuclideanAlgorithm
{ 
   //Solution # 1- Basic Euclidean algorithm #
    /*
   The algorithm starts by checking if the first number (a , which was obtained by b \%ab%a in recursive calls) is 0.
   If that is the case, then return b.
   Otherwise, we make the next recursive call GCD(b % a, a).

   This solution also has an iterative version, using a while (a != 0) loop instead of recursively calling the function.
   Both versions of the solution yield similar time complexities.

Time complexity #
The time complexity of this solution is O(log min(a,b)).
     */
    public static int GCD(int a, int b)
    {
        if (a == 0)
            return b;
        return GCD(b % a, a);
    }

    // Driver Program 
    public static void main(String[] args) 
    { 
        Random rand = new Random(); // built-in funtion provided by the library java.util.Random in Java for Random Number Generation
        int a = rand.nextInt(50);   // use random inputs 
        int b = a * rand.nextInt(10) + rand.nextInt(35);  
        System.out.println("GCD(" + a +  " , " + b+ ") = " + GCD(a, b)); 

        a = (rand.nextInt(150)%50); b = (rand.nextInt(200)%5);   // you can play around with the range of random numbers to see different outputs
        System.out.println("GCD(" + a +  " , " + b+ ") = " + GCD(a, b)); 

        a = rand.nextInt(10); b = rand.nextInt(10); 
        System.out.println("GCD(" + a +  " , " + b+ ") = " + GCD(a, b)); 

    }
} 