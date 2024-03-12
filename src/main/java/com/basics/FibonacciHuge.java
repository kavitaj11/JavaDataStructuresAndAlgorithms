package com.basics;

import java.util.*;

/*to compute Fn modulo m, where n may be really huge*/
public class FibonacciHuge {

    /**
    * We know that for any integer m ≥ 2, the sequence Fn mod m is periodic. The period always
    * starts with 01 and is known as Pisano period.
    * Find the length of that period by performing Fi mod m for all i<=n.
    * n can be reduced to n mod the length of Pisano period
    */
	private static long getFibonacciHuge(long n, long m) {
		if (n <= 1)
			return n;
    
		n = n % getPeriodicLength(m);
		if(n==0) {
			return 0;
		}
    
		long previous = 0;
		long current = 1;

    //For newly computed n, find the equivalent Fn mod m
		for (long i = 2; i <= n; i++) {
			long tmp_previous = previous;
			previous = current;
			current = (tmp_previous + current) % m;
		}

		return current % m;
	}

    /**
    * Find the length of Pisano period by performing Fi mod m for all i<=n.
    * If 01 is encountered for 2nd time, stop the computation of length since its periodic and we need only the length of period.
    */
	private static long getPeriodicLength(long m) {
		long modEq1 = 0, modEq2 = 1, fib3 = modEq1 + modEq2;
		for (long i = 0; i < m * m; i++) {
			fib3 = (modEq1 + modEq2) % m;
			modEq1 = modEq2;
			modEq2 = fib3;
			if (modEq1 == 0 && modEq2 == 1)
				return i + 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long n = scanner.nextLong();
		long m = scanner.nextLong();
		System.out.println(getFibonacciHuge(n, m));
	}
}