package com.basics.algorithms.divideAndConquer;

import java.util.*;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        int n = starts.length, m = points.length, index = 0;
        // Build array of pairs with value and mark 'l', 'r', 'p'.
        // N segments provide 2N pairs while M points have M pairs.
        Pair[] pairs = new Pair[2 * n + m];
        for (int end : ends)     pairs[index++] = new Pair(end,   'r');
        for (int point : points) pairs[index++] = new Pair(point, 'p');
        for (int start : starts) pairs[index++] = new Pair(start, 'l');
        // points are sorted by values, but cnt is still unordered
        // Map each point with index? duplicate points?
        Map<Integer, List<Integer>> mapPointsToIndex = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            if(! mapPointsToIndex.containsKey(points[i]))
                mapPointsToIndex.put(points[i], new ArrayList<>());
            mapPointsToIndex.get(points[i]).add(i);
        }
        // Sort all pairs by values
        Arrays.sort(pairs, new Comparator<Pair>(){
            @Override
            public int compare(Pair p1, Pair p2) {
                // what if p == l == r?
                // sort by l->p->r, i.e. segment still contains p.
                return p1.value == p2.value ? p1.mark - p2.mark :
                        p1.value - p2.value;
            }
        });
        // Count # of segments containing each point
        int count = 0;
        for (Pair pair : pairs) {
            if (pair.mark == 'l') count++;
            if (pair.mark == 'r') count--;
            if (pair.mark == 'p') {
                // duplicate points? -> have same counts,
                // but fill in all indices with point p
                for (int i : mapPointsToIndex.get(pair.value))
                    cnt[i] = count;
            }
        }
        return cnt;
    }
    private static class Pair {
        int value;
        char mark;
        public Pair (int v, char m) {
            value = v;
            mark  = m;
        }
    }
      

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

