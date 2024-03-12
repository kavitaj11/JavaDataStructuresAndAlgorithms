package com.datastructures.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
The first functionality we’ll be building is to locate the nearest available drivers within the vicinity of a user.
 There are numerous Uber drivers roaming around in a city.
 For simplicity, we’ll consider the city as a Cartesian plane and
 assign the coordinates (0, 0) to the user’s location.
 When a user wants to book a ride, we’ll simply find k drivers
 with the shortest distance on the Cartesian plane.
  Here, k is the minimum threshold for choosing the closest drivers.

We’ll be provided a list containing a set of points on the Cartesian plane and a number k.
Each set of points will represent the location of a driver.
 We need to find the k closest drivers from the user’s location.
 */
//Time complexity #
//The time complexity will be O(n×logk) because we are iterating all points and pushing them into the Heap.
// Here, n is the size of the locations array, and we are storing k points into the Heap.
//
//Space complexity #
//The memory complexity will be O(k) because we need to store k points in the Heap.

public class ClosestUberDrivers {

  public static List<Location> findClosestDrivers(Location[] locations, int k) {
    PriorityQueue<Location> maxHeap = new PriorityQueue<>((p1, p2) -> p2.distFromOrigin() - p1.distFromOrigin());
    // put first 'k' locations in the max heap
    for (int i = 0; i < k; i++)
      maxHeap.add(locations[i]);

    // go through the remaining locations of the input array, if a Location is closer to the origin than the top Location
    // of the max-heap, remove the top Location from heap and add the Location from the input array
    for (int i = k; i < locations.length; i++) {
      if (locations[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) {
        maxHeap.poll();
        maxHeap.add(locations[i]);
      }
    }

    // the heap has 'k' locations closest to the origin, return them in a list
    return new ArrayList<>(maxHeap);
  }

  public static void main(String[] args) {
    Location[] locations = new Location[] { new Location(1, 3), new Location(3, 4), new Location(2, -1) };
    List<Location> result = ClosestUberDrivers.findClosestDrivers(locations, 2);
    System.out.print("Here are the k drivers locations closest to the user in the Seattle area: ");
    for (Location p : result)
      System.out.print("[" + p.x + " , " + p.y + "] ");
  }


}

class Location {
  int x;
  int y;

  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int distFromOrigin() {
    // ignoring sqrt
    return (x * x) + (y * y);
  }
}


/*
Solution #
The Euclidean distance between a point P(x,y) and the origin can be calculated using the following formula:
sqrt{x^2 + y^2}

​
​​Now that you can calculate the distance between a user and all nearby drivers,
 how will you find the K nearest drivers?
 The best data structure that comes to mind to track the nearest K drivers is Heap.

We iterate through the array and calculate the distance between each driver’s current location and the user.
 We’ll insert the distances of the first K drivers into the Heap.
 Each time we find a distance smaller than the maximum distance in the Heap, we do two things:

Remove the maximum distance from the Heap
Insert the smaller distance into the Heap

This will ensure that we always have K minimum distances in the Heap.
The most efficient way to repeatedly find the maximum number among a set of numbers is to use a max-Heap.

 */