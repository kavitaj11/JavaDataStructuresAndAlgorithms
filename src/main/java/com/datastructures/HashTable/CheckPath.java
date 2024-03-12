package com.datastructures.HashTable;


import java.util.HashMap;
import java.util.Map;
//Given a HashMap with all the source and destination points in the journey,
// can you find the starting point and print out the complete path from start to end?
/*
Problem Statement #
In this problem, you have to implement the tracePath() function
to find the starting point of a journey and print out the complete path from start to end.
 */


//Solution: A Hash Table to Deduce The Starting Point #

/*
The first thing we need to do is find the starting point of the journey.
A reverseMap is created to switch the sources and destinations in the original map.

The key with does not appear in reverseMap has never been a destination in map.
Hence, it is the starting city.

From here, we simply traverse from city to city based on the previous destination.

Time Complexity #
Although a hash table is created and traversed, both take the same amount of time.
The complexity for this algorithm is O(n)
where n is the number of source-destination pairs.
 */
class CheckPath {

  public static String tracePath(Map< String, String > map) {

    String result = "";

    //Create a reverse Map of given map i.e if given map has (N,C)
    // then reverse map will have (C,N) as key value pair
    //Traverse original map and see if corresponding key exist in reverse Map
    //If it doesn't exist then we found our starting point.
    //After starting point is found, simply trace the complete path from original map.

    HashMap< String,String > reverseMap = new HashMap < >();

    //To fill reverse map, iterate through the given map
    for (Map.Entry < String, String > entry : map.entrySet())		
      reverseMap.put(entry.getValue(), entry.getKey());

    //Find the starting point of itinerary
    String from = "";

    //Check if graph is disconnected
    int count = 0;

    for (Map.Entry < String, String > entry: map.entrySet()) {
      if (!reverseMap.containsKey(entry.getKey())) {
        count++;
        from = entry.getKey();
        //break;
      }
    }

    if(count > 1){
      return "null"; // Disconnected graph
    }

    //Trace complete path
    String to = map.get(from);

    while (to != null) {
      result += from + "->" + to + ", ";
      from = to;
      to = map.get(to);
    }
    //System.out.println(result);

    return result;
  }

  public static void main(String[] args) {
    HashMap<String,String> hMap = new HashMap<>();  

    hMap.put("NewYork","Chicago");
    hMap.put("Boston","Texas");
    hMap.put("Missouri","NewYork");
    hMap.put("Texas","Missouri");

    String actual_output = CheckPath.tracePath(hMap);

    System.out.println(actual_output);
  }
}