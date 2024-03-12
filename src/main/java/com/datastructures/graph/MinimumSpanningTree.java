package com.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Find the minimum spanning tree of a connected, undirected graph with weighted edges.
//Hints #
//Keep track of edges with minimum weight.
/*
Runtime complexity #
The runtime complexity of this solution is quadratic, O(n^2)
Here, n is the number of vertices.
Memory complexity #
The memory complexity of this solution is linear, O(n+e).
Here, n is the number of vertices and ee is the number of edges.

A spanning-tree of a connected, undirected graph is a subgraph that is a tree
and connects all the vertices.
One graph can have many different spanning trees.
A graph with n vertices has a spanning tree with n-1 edges.

A weight can be assigned to each edge of the graph.
The weight of a spanning tree is the sum of weights of all the edges of the spanning tree.
A minimum spanning tree(MST) for a weighted, connected and undirected graph
is a spanning tree with a weight less than or equal to the weight of every other spanning tree.

We’ll find the minimum spanning tree of a graph using Prim’s algorithm.
This algorithm builds the tree one vertex at a time, starting from any arbitrary vertex.
It adds the minimum weight edge from the tree being constructed to a vertex
from the remaining vertices at each step.

The algorithm is as follows:
1. Initialize the MST with an arbitrary vertex from the graph
2. Find the minimum weight edge from the constructed graph to the vertices not yet added in the graph
3. Add that edge and vertex to the MST
4. Repeat steps 2-3 until all the vertices have been added to the MST
 */

public class MinimumSpanningTree {
  public static void testGraph1() {
    graph g = new graph(new ArrayList<vertex>(),
        new ArrayList<edge>());
    int v = 5;

    // each edge contains the following: weight, src, dest
    ArrayList<Integer> e1 = new ArrayList<Integer>(
        Arrays.asList(1, 1, 2));
    ArrayList<Integer> e2 = new ArrayList<Integer>(
        Arrays.asList(1, 1, 3));
    ArrayList<Integer> e3 = new ArrayList<Integer>(
        Arrays.asList(2, 2, 3));
    ArrayList<Integer> e4 = new ArrayList<Integer>(
        Arrays.asList(3, 2, 4));
    ArrayList<Integer> e5 = new ArrayList<Integer>(
        Arrays.asList(3, 3, 5));
    ArrayList<Integer> e6 = new ArrayList<Integer>(
        Arrays.asList(2, 4, 5));

    List<ArrayList<Integer>> e = new ArrayList<ArrayList<Integer>>();
    e.add(e1);
    e.add(e2);
    e.add(e3);
    e.add(e4);
    e.add(e5);
    e.add(e6);

    g.generateGraph(v, e);
    System.out.println("Testing graph 1...");
    // g.printGraph();
    int w = g.findMinSpanningTree();
    g.printMst(w);
  }

  public static void testGraph2() {
    graph g = new graph(new ArrayList<vertex>(),
        new ArrayList<edge>());
    int v = 7;

    // each edge contains the following: weight, src, dest
    ArrayList<Integer> e1 = new ArrayList<Integer>(
        Arrays.asList(2, 1, 4));
    ArrayList<Integer> e2 = new ArrayList<Integer>(
        Arrays.asList(1, 1, 3));
    ArrayList<Integer> e3 = new ArrayList<Integer>(
        Arrays.asList(2, 1, 2));
    ArrayList<Integer> e4 = new ArrayList<Integer>(
        Arrays.asList(1, 3, 4));
    ArrayList<Integer> e5 = new ArrayList<Integer>(
        Arrays.asList(3, 2, 4));
    ArrayList<Integer> e6 = new ArrayList<Integer>(
        Arrays.asList(2, 3, 5));
    ArrayList<Integer> e7 = new ArrayList<Integer>(
        Arrays.asList(2, 4, 7));
    ArrayList<Integer> e8 = new ArrayList<Integer>(
        Arrays.asList(1, 5, 6));
    ArrayList<Integer> e9 = new ArrayList<Integer>(
        Arrays.asList(2, 5, 7));

    List<ArrayList<Integer>> e = new ArrayList<ArrayList<Integer>>();
    e.add(e1);
    e.add(e2);
    e.add(e3);
    e.add(e4);
    e.add(e5);
    e.add(e6);
    e.add(e7);
    e.add(e8);
    e.add(e9);

    g.generateGraph(v, e);
    System.out.println("Testing graph 2...");
    // g.printGraph();
    int w = g.findMinSpanningTree();
    g.printMst(w);
  }

  public static void main(String[] args) {
    testGraph1();
    testGraph2();
  }
}


class vertex {
  private int id;
  private boolean visited;

  public vertex(int id, boolean visited) {
    super();
    this.id = id;
    this.visited = visited;
  }

  int getId() {
    return id;
  }

  void setId(int id) {
    this.id = id;
  }

  boolean isVisited() {
    return visited;
  }

  void setVisited(boolean visited) {
    this.visited = visited;
  }
}

class edge {
  private int weight;
  private boolean visited;
  private vertex src;
  private vertex dest;

  public edge(int weight, boolean visited, vertex src,
              vertex dest) {
    this.weight = weight;
    this.visited = visited;
    this.src = src;
    this.dest = dest;
  }

  int getWeight() {
    return weight;
  }

  void setWeight(int weight) {
    this.weight = weight;
  }

  boolean isVisited() {
    return visited;
  }

  void setVisited(boolean visited) {
    this.visited = visited;
  }

  vertex getSrc() {
    return src;
  }

  void setSrc(vertex src) {
    this.src = src;
  }

  vertex getDest() {
    return dest;
  }

  void setDest(vertex dest) {
    this.dest = dest;
  }
}

class graph {
  private List<vertex> g;   //vertices
  private List<edge> e;     //edges

  public graph(List<vertex> g, List<edge> e) {
    super();
    this.g = g;
    this.e = e;
  }

  public List<vertex> getG() {
    return g;
  }

  public void setG(List<vertex> g) {
    this.g = g;
  }

  public List<edge> getE() {
    return e;
  }

  public void setE(List<edge> e) {
    this.e = e;
  }

  // This method returns the vertex with a given id if it
  // already exists in the graph, returns NULL otherwise
  vertex vertexExists(int id) {
    for (int i = 0; i < g.size(); i++) {
      if (g.get(i).getId() == id) {
        return g.get(i);
      }
    }
    return null;
  }

  // This method generates the graph with v vertices
  // and e edges
  void generateGraph(int vertices,
                     List<ArrayList<Integer>> edges) {
    // create vertices
    for (int i = 0; i < vertices; i++) {
      vertex v = new vertex(i + 1, false);
      g.add(v);
    }

    // create edges
    for (int i = 0; i < edges.size(); i++) {
      vertex src = vertexExists(edges.get(i).get(1));
      vertex dest = vertexExists(edges.get(i).get(2));
      edge e = new edge(edges.get(i).get(0), false, src,
              dest);
      getE().add(e);
    }
  }

  // This method finds the MST of a graph using
  // Prim's Algorithm
  // returns the weight of the MST
  int findMinSpanningTree() {
    int vertex_count = 0;
    int weight = 0;

    // Add first vertex to the MST
    vertex current = g.get(0);
    current.setVisited(true);
    vertex_count++;

    // Construct the remaining MST using the
    // smallest weight edge
    while (vertex_count < g.size()) {
      edge smallest = null;
      for (int i = 0; i < e.size(); i++) {
        if (e.get(i).isVisited() == false) {
          if (e.get(i).getSrc().isVisited() == true && e.get(i).getDest().isVisited() == false) {
            if (smallest == null || e.get(i).getWeight() < smallest.getWeight()) {
              smallest = e.get(i);
            }
          }
        }
      }

      smallest.setVisited(true);
      smallest.getDest().setVisited(true);
      weight += smallest.getWeight();
      vertex_count++;
    }
    return weight;
  }

  String printGraph() {
    String result = "";

    for (int i = 0; i < e.size(); i++) {
      result += e.get(i).getSrc().getId() + "->"
              + e.get(i).getDest().getId() + "["
              + e.get(i).getWeight() + ", "
              + e.get(i).isVisited() + "]  ";
    }
    return result;
  }

  void printMst(int w) {
    System.out.println("MST");
    for (int i = 0; i < e.size(); i++) {
      if (e.get(i).isVisited() == true) {
        System.out.println(e.get(i).getSrc().getId() + "->"
                + e.get(i).getDest().getId());
      }
    }
    System.out.println("weight: " + w);
    System.out.println();
  }
};


