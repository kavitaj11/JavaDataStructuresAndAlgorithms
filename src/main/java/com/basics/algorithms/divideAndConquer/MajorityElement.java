package com.basics.algorithms.divideAndConquer;

import java.util.*;
import java.io.*;

/*
Majority rule is a decision rule that selects the alternative which has a majority,
that is, more than half the votes.
Given a sequence of elements 𝑎 1 , 𝑎 2 , . . . , 𝑎 𝑛 ,
you would like to check whether it contains an element that appears more than 𝑛/2 times.
 A naive way to do this is the following.

MajorityElement(𝑎 1 , 𝑎 2 , . . . , 𝑎 𝑛 ): for 𝑖 from 1 to 𝑛:
currentElement ← 𝑎𝑖
count ← 0
for 𝑗 from 1 to 𝑛:
if 𝑎 𝑗 = currentElement:
count ← count + 1 if count > 𝑛/2:
return 𝑎𝑖  return “no majority element”

The running time of this algorithm is quadratic.

 Your goal is to use the divide-and-conquer technique to design an 𝑂(𝑛 log 𝑛) algorithm.
 */
public class MajorityElement {

    //this problem can also be solved in 𝑂(𝑛) time by an algorithm
    // that just scans the given sequence twice.
    private static int getMajorityElementNonRecursive(int[] a) {
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        int ans = -1;

        for (int item : a) {
            frequency.put(item, frequency.getOrDefault(item, 0) + 1);
        }
        for (int key : frequency.keySet()) {
            int value = frequency.get(key);
            if (value > a.length / 2) {
                ans = key;
                break;
            }
        }
        return ans;
    }

    //this problem can be solved by the divide-and-conquer algorithm in time 𝑂(𝑛 log 𝑛)
    private static int getMajorityElement(int[] a, int lo, int hi) {
        if (hi <= lo)
            return a[lo];
        int mid = lo + (hi - lo) / 2;
        int lElement = getMajorityElement(a, lo, mid);
        int rElement = getMajorityElement(a, mid + 1, hi);

        if (lElement == rElement)
            return lElement;

        int lcount = frequency(a, lElement, lo, hi);
        int rcount = frequency(a, rElement, lo, hi);

        if (lcount > Math.ceil((hi - lo + 1) / 2))
            return lElement;
        if (rcount > Math.ceil((hi - lo + 1) / 2))
            return rElement;

        return -1;	// no majority element
    }

    private static int frequency(int[] a, int element, int lo, int hi) {
        int cnt = 0;
        for (int i = lo; i <= hi; i++) {
            if (a[i] == element)
                cnt++;
        }
        return cnt;
    }

    private static int getMajorityElement(int[] a) {
        int ans = -1;
        ans = getMajorityElement(a, 0, a.length - 1);
        return ans;
    }



    public static void main(String[] args) {
        int arr[] = {-5,-3,0,3,3,3,3,3,3,5};
        System.out.printf("Correct Answer: %d\n", getMajorityElementNonRecursive(arr));
        System.out.printf("Correct Answer: %d\n", getMajorityElement(arr));
    }
}

