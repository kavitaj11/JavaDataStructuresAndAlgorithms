package com.basics;

import java.util.*;
/**To  find the last digit of partial sum of Fibonacci numbers, i.e, given m and n, find last digit of
 * sum of m to n th Fibonacci numbers (F(m)+F(m+1)+F(m+2)+...F(n))
 * We can use the PISANO Number method (PISANO Number=60) to reduce the complexity of computations as the
 * last digit repeats every 60 numbers.
 */
public class FibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next  = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
            }

            long new_current = next;
            next = next + current;
            current = new_current;
        }

        return sum % 10;
    }

    private static long getFibonacciPartialSum(long from, long to) {
        long sum = 0;

        //Reduces very large numbers to manageable form
        from = from % 60;
        to = to % 60;
        //While this reduction if "to" is less than "from" it introduces errors, hence add 60 to it to make to>from for
        //proper calculation of partial sum
        if (to < from)
            to += 60;

        long current = 0;
        long next = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
            }

            long new_current = next;
            next = next + current;
            current = new_current;
        }

        return sum % 10;
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));
    }
}

