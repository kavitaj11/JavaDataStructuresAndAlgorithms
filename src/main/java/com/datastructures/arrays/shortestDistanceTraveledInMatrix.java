package com.datastructures.arrays;/*
Given N people on an MxM grid,
 find the point that requires the least total distance covered by all people to meet at that point.

We need to find the meeting point(x,y) for these people where the total distance covered by all three is the minimum.
They can travel in all directions: horizontally, vertically, and diagonally.
The minimum distance point, in this case, is (3,3).

Consider a 5x5 grid with 3 people; one at X(1,2), Y(4,2), and Z(3,3).
Runtime complexity #
The runtime complexity of this solution is cubic, O(m^2n)

If the number of people, n, is equal to the size of the grid, m, the time complexity becomes O(m^2n). 
In the worst case, if there is a person on each point of the grid, the complexity would become O(m^4)
considering there is no repetition in the points.

Memory complexity #
The memory complexity of this solution is linear, O(n) where n is the number of people on the grid.
The first solution is very straightforward but has greater time complexity.
We can calculate the distance between two points using the following Euclidean distance formula.
d=sqrt{(x2 - x1)^2 + (y2 - y1)^2}

We are using Euclidean distance here because the people on the grid can move diagonally as well. 
We cannot use Manhattan distance (d=∣x2−x1∣+∣y2−y1∣) here
because it is a measure of the horizontal and vertical distance
and does not cover the diagonal distance. 
For each point on the grid, we check if the sum of distances traveled by all people is the minimum or not. 
We can take the minimum distance as the sum of distances of people from the point (1,1) 
and then compare it with all the other points on the grid to find the optimum meeting point. 
*/


import java.util.ArrayList;

class Point {
  private int x;
  private int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  int getX() {
    return x;
  }

  void setX(int x) {
    this.x = x;
  }

  int getY() {
    return y;
  }

  void setY(int y) {
    this.y = y;
  }

  double calculateDistance(Point p) {
    double distance;
    distance = Math.sqrt((p.x - this.x) * (p.x - this.x) 
    + (p.y - this.y) * (p.y - this.y));
    return distance;
  }

  double calculateSumOfDistances(ArrayList<Point> points) {
    double distanceSum;
    distanceSum = 0;
    for (int i = 0; i < points.size(); i++) {
      distanceSum += this.calculateDistance(points.get(i));
    }
    return distanceSum;
  }
};

class Distance {
  public static Point shortestDistanceTraveled(int m, ArrayList<Point> points) {
    Point pt = new Point(1, 1);
    double minDistance = pt.calculateSumOfDistances(points);
    Point minPt = new Point(pt.getX(), pt.getY());

    double distance;
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= m; j++) {
        pt.setX(i);
        pt.setY(j);
        distance = pt.calculateSumOfDistances(points);

        if (distance < minDistance) {
          minDistance = distance;
          minPt.setX(pt.getX());
          minPt.setY(pt.getY());
        }
      }
    }
    return minPt;
  }


  public static void main(String[] args) {

    int [] arr = {5, 10, 8};  
    ArrayList<Point> points = new ArrayList<Point>();
    points.add(new Point(1, 2));
    points.add(new Point(3, 3));
    points.add(new Point(4, 2));

    System.out.println("Grid 5x5 and values ((1, 2), (3, 3), (4, 2))");
    Distance d = new Distance();
    Point pt = d.shortestDistanceTraveled(arr[0], points);
    System.out.println("Shortest Distance Point = p("
        + pt.getX() + ", " + pt.getY() + ")"); 

    ArrayList<Point> points2 = new ArrayList<Point>();
    points2.add(new Point(1, 1));
    points2.add(new Point(3, 5));
    points2.add(new Point(6, 2));
    points2.add(new Point(7, 7));
    points2.add(new Point(8, 4));

    System.out.println("\nGrid 10x10 and values ((1, 1), (3, 5), 24, 2), (7, 7), (8,4))");
    pt = d.shortestDistanceTraveled(arr[1], points2);
    System.out.println("Shortest Distance Point = p("
        + pt.getX() + ", " + pt.getY() + ")");
  
    ArrayList<Point> points3 = new ArrayList<Point>();
    points3.add(new Point(4, 3));
    points3.add(new Point(5, 5));
    points3.add(new Point(2, 7));

    System.out.println("\nGrid 8x8 and values ((4, 3), (5, 5), (2, 7))");
    pt = d.shortestDistanceTraveled(arr[2], points3);
    System.out.println("Shortest Distance Point = p("
        + pt.getX() + ", " + pt.getY() + ")");
  }
}
