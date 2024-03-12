package com.basics.datastructures;
import java.util.HashMap;
import java.util.Map;

// A class to represent a disjoint set
public class DisjointSet {
    private static Map<Integer, Integer> parent = new HashMap();

    // perform MakeSet operation
    public static void makeSet(int[] universe) {
        // create `n` disjoint sets (one for each item)
        for (int i: universe) {
            parent.put(i, i);
        }
    }
    public static void makeSet(int i) {
            parent.put(i, i);
    }


    // Find the root of the set in which element `k` belongs
    public static int Find(int k) {
        // if `k` is root
        if (parent.get(k) == k) {
            return k;
        }
        // recur for the parent until we find the root
        return Find(parent.get(k));
    }

    // Perform Union of two subsets
    public static void Union(int a, int b) {
        // find the root of the sets in which elements
        // `x` and `y` belongs
        int x = Find(a);
        int y = Find(b);
        parent.put(x, y);
    }


    // Disjoint–Set data structure (Union–Find algorithm)
    public static void main(String[] args) {
        // universe of items
        int[] test = new int[13];
        for (int i = 1; i <= 12; i++)
            makeSet(i);
        Union(2, 10);
        Union(7, 5);
        Union(6, 1);
        Union(3, 4);
        Union(5, 11);
        Union(7, 8);
        Union(7, 3);
        Union(12, 2);
        Union(9, 6);
        System.out.println(Find(6));
        System.out.println(Find(3));
        System.out.println(Find(11));
        System.out.println(Find(9));

        for (int i = 1; i <= 10; i++)
        makeSet(i);
        for (int i = 1; i <= 9; i++)
        Union(i, i+1);
        System.out.println(Find(9));
    }
}



