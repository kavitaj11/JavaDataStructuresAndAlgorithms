/*
package com.algorithms.miscellaneous.graph;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.util.*;


*/
/**
 * Implementation of Dijkstra's algorithm with a {@link PriorityQueue} and a data structure holding
 * the actual node, its total distance and its predecessor ({@link NodeWrapper}).
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 *//*

public class DijkstraWithPriorityQueue {

  // Puts only the first into the queue

  */
/**
   * Finds the shortest path from {@code source} to {@code target}.
   *
   * @param graph the graph
   * @param source the source node
   * @param target the target node
   * @param <N> the node type
   * @return the shortest path; or {@code null} if no path was found
   *//*

  public static <N> List<N> findShortestPath(ValueGraph<N, Integer> graph, N source, N target) {
    Map<N, NodeWrapper<N>> nodeWrappers = new HashMap<>();
    PriorityQueue<NodeWrapper<N>> queue = new PriorityQueue<>();
    Set<N> shortestPathFound = new HashSet<>();

    // Add source to queue
    NodeWrapper<N> sourceWrapper = new NodeWrapper<>(source, 0, null);
    nodeWrappers.put(source, sourceWrapper);
    queue.add(sourceWrapper);

    while (!queue.isEmpty()) {
      NodeWrapper<N> nodeWrapper = queue.poll();
      N node = nodeWrapper.getNode();
      shortestPathFound.add(node);

      // Have we reached the target? --> Build and return the path
      if (node.equals(target)) {
        return buildPath(nodeWrapper);
      }

      // Iterate over all neighbors
      Set<N> neighbors = graph.adjacentNodes(node);
      for (N neighbor : neighbors) {
        // Ignore neighbor if shortest path already found
        if (shortestPathFound.contains(neighbor)) {
          continue;
        }

        // Calculate total distance from start to neighbor via current node
        int distance = Objects.requireNonNull(graph.edgeValueOrDefault(node, neighbor, 0));
        int totalDistance = nodeWrapper.getTotalDistance() + distance;

        // Neighbor not yet discovered?
        NodeWrapper<N> neighborWrapper = nodeWrappers.get(neighbor);
        if (neighborWrapper == null) {
          neighborWrapper = new NodeWrapper<>(neighbor, totalDistance, nodeWrapper);
          nodeWrappers.put(neighbor, neighborWrapper);
          queue.add(neighborWrapper);
        }

        // Neighbor discovered, but total distance via current node is shorter?
        // --> Update total distance and predecessor
        else if (totalDistance < neighborWrapper.getTotalDistance()) {
          neighborWrapper.setTotalDistance(totalDistance);
          neighborWrapper.setPredecessor(nodeWrapper);

          // The position in the PriorityQueue won't change automatically;
          // we have to remove and reinsert the node
          queue.remove(neighborWrapper);
          queue.add(neighborWrapper);
        }
      }
    }

    // All reachable nodes were visited but the target was not found
    return null;
  }

  private static <N> List<N> buildPath(NodeWrapper<N> nodeWrapper) {
    List<N> path = new ArrayList<>();
    while (nodeWrapper != null) {
      path.add(nodeWrapper.getNode());
      nodeWrapper = nodeWrapper.getPredecessor();
    }
    Collections.reverse(path);
    return path;
  }
}


//**
// * Data structure containing a node, it's total distance from the start and its predecessor.
// *
class NodeWrapper<N> implements Comparable<NodeWrapper<N>> {
  private final N node;
  private int totalDistance;
  private NodeWrapper<N> predecessor;

  NodeWrapper(N node, int totalDistance, NodeWrapper<N> predecessor) {
    this.node = node;
    this.totalDistance = totalDistance;
    this.predecessor = predecessor;
  }

  N getNode() {
    return node;
  }

  void setTotalDistance(int totalDistance) {
    this.totalDistance = totalDistance;
  }

  public int getTotalDistance() {
    return totalDistance;
  }

  public void setPredecessor(NodeWrapper<N> predecessor) {
    this.predecessor = predecessor;
  }

  public NodeWrapper<N> getPredecessor() {
    return predecessor;
  }

  @Override
  public int compareTo(NodeWrapper<N> o) {
    return Integer.compare(this.totalDistance, o.totalDistance);
  }

  // Using identity for equals and hashcode here, which is much faster.
  // It's sufficient as within the algorithm, we have only one NodeWrapper instance per node.

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
 * Tests the implementation of Dijkstra's algorithm using the following sample graph:
 *
 * <pre>
 *       A
 *      / \
 *    2/   \3
 *    /     \
 *   / 3   1 \    5
 *  C-----D---E-------B
 *  |      \  |       |
 *  |      4\ |6      |
 *  |        \|       |
 * 2|         F       |15
 *  |         |       |
 *  |         |7      |
 *  |         |       |
 *  G---------H-------I
 *       4        3
 * </pre>
 *//*


 class TestWithSampleGraph {
  public static void main(String[] args) {
    ValueGraph<String, Integer> graph = createSampleGraph();

    System.out.println("graph = " + graph);

    findAndPrintShortestPath(graph, "D", "H");
    findAndPrintShortestPath(graph, "A", "F");
    findAndPrintShortestPath(graph, "E", "H");
    findAndPrintShortestPath(graph, "B", "H");
    findAndPrintShortestPath(graph, "B", "I");
    findAndPrintShortestPath(graph, "E", "H");
  }

  private static void findAndPrintShortestPath(
          ValueGraph<String, Integer> graph, String source, String target) {
    List<String> shortestPath = DijkstraWithPriorityQueue.findShortestPath(graph, source, target);
    System.out.printf("shortestPath from %s to %s = %s%n", source, target, shortestPath);
  }

  private static ValueGraph<String, Integer> createSampleGraph() {
    MutableValueGraph<String, Integer> graph = ValueGraphBuilder.undirected().build();
    graph.putEdgeValue("A", "C", 2);
    graph.putEdgeValue("A", "E", 3);
    graph.putEdgeValue("B", "E", 5);
    graph.putEdgeValue("B", "I", 15);
    graph.putEdgeValue("C", "D", 3);
    graph.putEdgeValue("C", "G", 2);
    graph.putEdgeValue("D", "E", 1);
    graph.putEdgeValue("D", "F", 4);
    graph.putEdgeValue("E", "F", 6);
    graph.putEdgeValue("F", "H", 7);
    graph.putEdgeValue("G", "H", 4);
    graph.putEdgeValue("H", "I", 3);
    return graph;
  }
}
*/
