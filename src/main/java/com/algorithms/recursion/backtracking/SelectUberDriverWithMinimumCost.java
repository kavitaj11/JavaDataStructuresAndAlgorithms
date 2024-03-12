package com.algorithms.recursion.backtracking;

import java.util.*;

//After obtaining the closest drivers and calculating the cost of traveling on different roads,
// we need to build a functionality to select a path from the driver’s location to the user’s location.
// All the drivers have to pass through multiple checkpoints to reach the user’s location.
// Each road between checkpoints will have a cost.
// It is possible that some of the k chosen drivers might not have a path to the user due to unavailability.
// Unavailability can occur due to a driver already being in a ride that has ended but not reached its location.
// In some cases, the driver can also get booked by another user and become unavailable.
// The driver that has the path to the user’s location with the minimum accumulated cost will be selected.

class SelectUberDriverWithMinimumCost {
  
  public static double[] getTotalCost(List<List<String>> GMap, double[] pathCosts, List<String> drivers, String user) {
    HashMap<String, HashMap<String, Double>> city = new HashMap<>();

    // Step 1). build the city from the GMap
    for (int i = 0; i < GMap.size(); i++) {
      List<String> checkPoints = GMap.get(i);
      String sourceNode = checkPoints.get(0), destNode = checkPoints.get(1);
      double pathCost = pathCosts[i];

      if (!city.containsKey(sourceNode))
          city.put(sourceNode, new HashMap<String, Double>());
      if (!city.containsKey(destNode))
          city.put(destNode, new HashMap<String, Double>());

      city.get(sourceNode).put(destNode, pathCost);
      city.get(destNode).put(sourceNode, pathCost);
    }

    // Step 2). Evaluate each driver via bactracking (DFS)
    // by verifying if there exists a path from driver to user
    double[] results = new double[drivers.size()];
    for (int i = 0; i < drivers.size(); i++) {
      String driver = drivers.get(i);

      if (!city.containsKey(driver) || !city.containsKey(user))
          results[i] = -1.0;
      else {
          HashSet<String> visited = new HashSet<>();
          results[i] = backtrackEvaluate(city, driver, user, 0, visited);
      }
    }

    return results;
  }

  private static double backtrackEvaluate(HashMap<String, HashMap<String, Double>> city, String currNode, String targetNode, double accSum, Set<String> visited) {

      // mark the visit
      visited.add(currNode);
      double ret = -1.0;

      Map<String, Double> neighbors = city.get(currNode);
      if (neighbors.containsKey(targetNode))
          ret = accSum + neighbors.get(targetNode);
      else {
          for (Map.Entry<String, Double> pair : neighbors.entrySet()) {
              String nextNode = pair.getKey();
              if (visited.contains(nextNode))
                  continue;
              ret = backtrackEvaluate(city, nextNode, targetNode,
                      accSum + pair.getValue(), visited);
              if (ret != -1.0)
                  break;
          }
      }

      // unmark the visit, for the next backtracking
      visited.remove(currNode);
      return ret;
  }

  public static void main(String[] args) {

    // Driver code
    List<List<String>> GMap = Arrays.asList(Arrays.asList("a","b"), Arrays.asList("b","c"), Arrays.asList("a","e"), Arrays.asList("d","e"));
    double[] pathCosts = {12.0,23.0,26.0,18.0};
    List<String> drivers = Arrays.asList("c", "d", "e", "f");
    String user = "a";
    double[] allPathCosts = getTotalCost(GMap, pathCosts, drivers, user);

    System.out.print("Total cost of all paths " + Arrays.toString(allPathCosts));
  }
}

/*The main problem comes down to finding a path between two nodes, if it exists.
If the path exists, return the cumulative sums along the path as the result.
Given the problem, it seems that we need to track the nodes where we come from.
DFS (Depth-First Search), also known as the backtracking algorithm, will be applicable in this case.

Here is how the implementation will take place:

Build the graph using the city map list G_map.

Assign the cost to each edge while building the graph.

Once the graph is built, evaluate each driver’s path in the drivers list by searching for a path between the driver node and the user node.

Return the accumulated sum if the path exists. Otherwise, return -1.

Let’s look at the code for the solution:

 */

/*Time complexity #
We need to compute the running time of the following to obtain the total time complexity:

Building graph: Traversing the G_map to build our graph and processing each checkpoint will take O(1) time.
 So, building the complete graph through G_map will take O(n) time.

Finding path: For every driver in drivers, we will traverse the complete graph.
In the worst case, this can take a total of O(n) time.
 Processing all the paths will require a total of O(m×n) time.

As a result, we get an overall time complexity of O(m×n).

Space complexity #
Building graph: In the worst case, a graph will always have n edges and 2n nodes. Since we store the complete graph, the total space required will be O(n + 2n)O(n+2n) == O(n)O(n).

Finding path: We use recursion to keep track of the path, so in the worst case, the memory stack will require O(n)O(n) space.

visited: In the worst case, it will use O(n) space.

Finally, we need O(m) space to store the results generated from the paths.

As a result, we get an overall time complexity of O(m + n)

 */