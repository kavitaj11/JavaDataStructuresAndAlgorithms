package com.algorithms.divideAndConquer;

//Given a number N, find its cube root upto a precision P where N>=1.
public class cuberoot {

    //Solution 1: Brute Force
    //The brute force approach is to iterate over the natural numbers from 1 to N and check if their cubes are equal to N. This approach will suffice if N is a perfect cube.
    // But if N is not a perfect cube, the brute force approach is carried out in two steps,
    //The time complexity of the above approach is the sum of the complexity of the integral part
    // and the fractional part i.e
    //O(N)+O(10P)

    double cuberootNaive(double N, int P){
        int close = 1;

        for (int i = 1; i <= N; i++)
        { // break if i*i*i is greater than N
            if(i * i * i > N)
            {
                close = i - 1;
                break;
            }
        }
        // Check if N is a perfect cube. If it is, then return close.
        if (close * close * close == N) return close;

        double increment = 0.1;
        for (int i = 0; i < P; i++)
        { // increment close until “close” is greater than N.
            while (close * close * close <= N)
            {
                close += increment;
            }
            // when close*close*close is greater than N, remove the latest increment.
            close -= increment;

            // Go for further precision
            increment /= 10;
        }
        return close;
    }


    //Solution 2: Using Binary Search
    //The time complexity of the binary search approach is
    //O(log N+ log 10P)
    double cubeRoot(double N, int P) {
        double low = 1;
        double high = N;
        double prec = Math.pow(10, -P);
        double cuberoot = 1;
        while (low <= high) {
            double mid = (low + high) / 2;
            cuberoot = mid;

            // if the difference between cube of current mid and N is less than precision
            // value, then break.
            if (Math.abs(mid * mid * mid - N) <= prec)
                break;

                // decrementing high if answer lies between current low and mid
            else if (mid * mid * mid > N)
                high = mid;

                // incrementing low if answer lies between mid and current high.
            else
                low = mid;
        }
        return Math.round(cuberoot * Math.pow(10, P)) / Math.pow(10, P);
    }
}
