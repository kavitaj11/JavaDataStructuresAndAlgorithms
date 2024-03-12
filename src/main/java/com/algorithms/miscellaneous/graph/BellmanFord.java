/*
package com.algorithms.miscellaneous.graph;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.util.*;


*/
/**
 * Implementation of the Bellman Ford Algorithm.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 *//*

public class BellmanFord {

  */
/**
   * Finds the shortest path from {@code source} to {@code target}.
   *
   * @param graph the graph
   * @param source the source node
   * @param target the target node
   * @param <N> the node type
   * @return the shortest path; or {@code null} if no path was found
   * @throws IllegalArgumentException if a negative cycle was discovered
   *//*

  public static <N> List<N> findShortestPath(ValueGraph<N, Integer> graph, N source, N target) {
    Map<N, GNodeWrapper<N>> GNodeWrappers = new HashMap<>();

    // Add all node wrappers to the table
    for (N node : graph.nodes()) {
      int initialCostFromStart = node.equals(source) ? 0 : Integer.MAX_VALUE;
      GNodeWrapper<N> GNodeWrapper = new GNodeWrapper<>(node, initialCostFromStart, null);
      GNodeWrappers.put(node, GNodeWrapper);
    }

    // Iterate n-1 times + 1 time for the negative cycle detection
    int n = graph.nodes().size();
    for (int i = 0; i < n; i++) {
      // Last iteration for detecting negative cycles?
      boolean lastIteration = i == n - 1;

      boolean atLeastOneChange = false;

      // For all edges...
      for (EndpointPair<N> edge : graph.edges()) {
        GNodeWrapper<N> edgeSourceWrapper = GNodeWrappers.get(edge.source());
        int totalCostToEdgeSource = edgeSourceWrapper.getTotalCostFromStart();
        // Ignore edge if no path to edge source was found so far
        if (totalCostToEdgeSource == Integer.MAX_VALUE) continue;

        // Calculate total cost from start via edge source to edge target
        int cost = graph.edgeValueOrDefault(source,target,0);
        int totalCostToEdgeTarget = totalCostToEdgeSource + cost;

        // Cheaper path found?
        // a) regular iteration --> Update total cost and predecessor
        // b) negative cycle detection --> throw exception
        GNodeWrapper edgeTargetWrapper = GNodeWrappers.get(edge.target());
        if (totalCostToEdgeTarget < edgeTargetWrapper.getTotalCostFromStart()) {
          if (lastIteration) {
            throw new IllegalArgumentException("Negative cycle detected");
          }

          edgeTargetWrapper.setTotalCostFromStart(totalCostToEdgeTarget);
          edgeTargetWrapper.setPredecessor(edgeSourceWrapper);
          atLeastOneChange = true;
        }
      }

      // Optimization: terminate if nothing was changed
      if (!atLeastOneChange) break;
    }

    // Path found?
    GNodeWrapper<N> targetGNodeWrapper = GNodeWrappers.get(target);
    if (targetGNodeWrapper.getPredecessor() != null) {
      return buildPath(targetGNodeWrapper);
    } else {
      return null;
    }
  }

  private static <N> List<N> buildPath(GNodeWrapper<N> GNodeWrapper) {
    List<N> path = new ArrayList<>();
    while (GNodeWrapper != null) {
      path.add(GNodeWrapper.getNode());
      GNodeWrapper = GNodeWrapper.getPredecessor();
    }
    Collections.reverse(path);
    return path;
  }
}

*/
/**
 * Data structure containing a node, it's total cost from the start and its predecessor.
 *
 * <p>Used by {@link BellmanFord}.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 *//*

class GNodeWrapper<N> {
  private final N node;
  private int totalCostFromStart;
  private GNodeWrapper<N> predecessor;

  GNodeWrapper(N node, int totalCostFromStart, GNodeWrapper<N> predecessor) {
    this.node = node;
    this.totalCostFromStart = totalCostFromStart;
    this.predecessor = predecessor;
  }

  N getNode() {
    return node;
  }

  void setTotalCostFromStart(int totalCostFromStart) {
    this.totalCostFromStart = totalCostFromStart;
  }

  public int getTotalCostFromStart() {
    return totalCostFromStart;
  }

  public void setPredecessor(GNodeWrapper<N> predecessor) {
    this.predecessor = predecessor;
  }

  public GNodeWrapper<N> getPredecessor() {
    return predecessor;
  }

  // Using identity for equals and hashcode here, which is much faster.
  // It's sufficient as within the algorithm, we have only one GNodeWrapper instance per node.

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}


*/
/**
 * Tests the implementation of the Bellman Ford Algorithm using the following sample graph:
 *
 * <pre>
 *          4           5
 *  ( A )------>( B )<----->( C )
 *   ^ |         ^ |         ^ |
 *   | |         | |         | |
 *   | |         | |         | |
 *  4| |3      -3| |4       4| |-2
 *   | |         | |         | |
 *   | |         | |         | |
 *   | v         | v         | v
 *  ( D )<----->( E )------>( F )
 *          3           2
 * </pre>
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 *//*

class TestBFWithSampleGraph {
  public static void main(String[] args) {
    ValueGraph<String, Integer> graph = createSampleGraph();

    System.out.println("graph = " + graph);

    findAndPrintShortestPath(graph, "A", "F");
    findAndPrintShortestPath(graph, "C", "D");
  }

  private static void findAndPrintShortestPath(
          ValueGraph<String, Integer> graph, String source, String target) {
    List<String> shortestPath = BellmanFord.findShortestPath(graph, source, target);
    System.out.printf("shortestPath from %s to %s = %s%n", source, target, shortestPath);
  }

  private static ValueGraph<String, Integer> createSampleGraph() {
    MutableValueGraph<String, Integer> graph = ValueGraphBuilder.directed().build();
    graph.putEdgeValue("A", "B", 4);
    graph.putEdgeValue("A", "D", 3);
    graph.putEdgeValue("B", "C", 5);
    graph.putEdgeValue("B", "E", 4);
    graph.putEdgeValue("C", "B", 5);
    graph.putEdgeValue("C", "F", -2);
    graph.putEdgeValue("D", "A", 4);
    graph.putEdgeValue("D", "E", 3);
    graph.putEdgeValue("E", "B", -3);
    graph.putEdgeValue("E", "D", 3);
    graph.putEdgeValue("E", "F", 2);
    graph.putEdgeValue("F", "C", 4);
    return graph;
  }
}*/
