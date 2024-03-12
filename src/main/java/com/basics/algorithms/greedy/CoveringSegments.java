package com.basics.algorithms.greedy;

import java.util.*;

/*
You are responsible for collecting signatures from all tenants of a certain building.
 For each tenant, you know a period of time when he or she is at home.
 You would like to collect all signatures by visiting the building as few times as possible.

The mathematical model for this problem is the following.
 You are given a set of segments on a line and your goal is to mark as few points on a line as possible
  so that each segment contains at least one marked point.

  The first line of the input contains the number 𝑛 of segments.
  Each of the following 𝑛 lines contains:
  two integers 𝑎i and 𝑏i (separated by a space) defining the coordinates of endpoints of the 𝑖-th segment.
 */
public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        // sort the segments according to their right end
        Arrays.sort(segments, new Comparator<Segment>(){
            @Override
            public int compare(Segment segment1, Segment segment2) {
                return segment1.end - segment2.end;
            }
        });

        List<Integer> listOfPoints = new ArrayList<>();
        int i = 0, n = segments.length;
        while (i < n) {
            int right = segments[i].end;
            listOfPoints.add(right);
            while (i < n &&  segments[i].start <= right &&
                    right <= segments[i].end)
                i++;
        }

        int[] points = new int[listOfPoints.size()];
        for(int j = 0; j < listOfPoints.size(); j++)
            points[j] = listOfPoints.get(j);
        return points;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
