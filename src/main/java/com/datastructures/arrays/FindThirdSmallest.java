package com.datastructures.arrays;

//Java program to find the first, second and third minimum element in an array
/*
First approach : First we can use normal method that is
sort the array and then print first, second and third element of the array.
Time complexity of this solution is O(n Log n).

Second approach : Time complexity of this solution is O(n).
Algorithm-

First take an element
then if array[index] < Firstelement
        Thirdelement = Secondelement
        Secondelement = Firstelement
        Firstelement = array[index]
     else if array[index] < Secondelement
        Thirdelement = Secondelement
        Secondelement = array[index]
     else if array[index] < Thirdelement
        Thirdelement = array[index]

then print all the element
 */


import java.util.Arrays;

public class FindThirdSmallest
{
    static void print3Smallest(int arr[]) {
            int firstmin = Integer.MAX_VALUE;
            int secmin = Integer.MAX_VALUE;
            int thirdmin = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length; i++)
            {
                /* Check if current element is less than
                firstmin, then update first, second and
                third */
                if (arr[i] < firstmin) {
                    thirdmin = secmin;
                    secmin = firstmin;
                    firstmin = arr[i];
                }
          
                /* Check if current element is less than
                second min then update second and third */
                else if (arr[i] < secmin) {
                    thirdmin = secmin;
                    secmin = arr[i];
                }
          
                /* Check if current element is less than third min, then update third */
                else if (arr[i] < thirdmin)
                    thirdmin = arr[i];
            }

            System.out.println("First min = " + firstmin );
            System.out.println("Second min = " + secmin );
            System.out.println("Third min = " + thirdmin );
    }

    // Driver code
    public static void main(String[] args)
    {
            int array[] = {4, 67, 1, 32, 12, 29, 65, 6, 7, 34};
            print3Smallest(array);
    }
  }
      


  
